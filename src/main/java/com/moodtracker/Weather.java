package com.moodtracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Weather {
    public  double[] numbers = new double[7];
    private final Scanner input = new Scanner(System.in);



    public Weather(){
        weatherFunctions();
    }


    private String getWeather() { // returns the result of the weather API request.
        StringBuilder api = new StringBuilder();

        try {
            String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=5e888cebd7084d2bbe5142331252503&q=London&days=1&aqi=no&alerts=no";;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    api.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return api.toString(); // returns the value of the API response.
    }

    private void weatherFunctions() {
        String weatherData = getWeather();

        try {
            JSONObject jsonResponse = new JSONObject(weatherData);
            JSONObject current = jsonResponse.getJSONObject("current");
            JSONObject forecast = jsonResponse.getJSONObject("forecast");
            JSONObject forecastDay = forecast.getJSONArray("forecastday").getJSONObject(0);
            JSONObject astro = forecastDay.getJSONObject("astro");

            // Extracting data from JSON response
            double tempCelsius = current.getDouble("temp_c");
            double feelsLikeCelsius = current.getDouble("feelslike_c");
            double humidity = current.getDouble("humidity");
            double windSpeed = current.getDouble("wind_kph");
            double pressure = current.getDouble("pressure_mb");
            double uvIndex = current.getDouble("uv");

            // Adding new data extraction for sunshine hours
            String sunrise = astro.getString("sunrise");
            String sunset = astro.getString("sunset");

            double sunriseTime = convertToDecimalHours(sunrise);
            double sunsetTime = convertToDecimalHours(sunset);
            int sunshineHours = (int) ((int)sunsetTime - sunriseTime);

            // Calculating average cloud cover for the day
            JSONArray hourlyData = forecastDay.getJSONArray("hour");
            double totalCloudCover = 0;
            int count = 0;

            for (int i = 0; i < hourlyData.length(); i++) {
                JSONObject hourData = hourlyData.getJSONObject(i);
                double cloudCover = hourData.getDouble("cloud");
                totalCloudCover += cloudCover;
                count++;
            }

            double averageCloudCover = totalCloudCover / count;

            // Storing data in the numbers array (adding sunshine hours & cloud cover as well)
            numbers[0] = humidity;
            numbers[1] = windSpeed;
            numbers[2] = pressure;
            numbers[3] = sunshineHours;
            numbers[4] = tempCelsius;
            numbers[5] = feelsLikeCelsius;
            numbers[6] = averageCloudCover;


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private double convertToDecimalHours(String time) {
        String[] parts = time.split(" ");
        String[] hourMin = parts[0].split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int minute = Integer.parseInt(hourMin[1]);

        if (parts[1].equalsIgnoreCase("PM") && hour != 12) {
            hour += 12;
        } else if (parts[1].equalsIgnoreCase("AM") && hour == 12) {
            hour = 0;
        }

        return hour + (minute / 60.0);

    }

    public int timeSpentOutside() {
        int time = -1;

        while (true) {
            try {
                System.out.print("How long you spent outside today (in minutes): ");
                time = Integer.parseInt(input.nextLine());

                if (time >= 0) break; // Valid input, break the loop
                else System.out.println("Please enter a valid non-negative number.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return time;
    }
    public double[] getWeatherArray(){ // this is just a getter for the weather array
        return numbers;
    }



}
