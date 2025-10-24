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
    public  double[] numbers = new double[9];



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

            // Parse the weather data JSON response
            JSONObject jsonResponse = new JSONObject(weatherData);

            // Extracts the "current" weather details from the JSON response
            JSONObject current = jsonResponse.getJSONObject("current");

            // Extracts the "forecast" object, which contains weather predictions
            JSONObject forecast = jsonResponse.getJSONObject("forecast");

            // Get the first day's forecast from the "forecastday" array
            JSONObject forecastDay = forecast.getJSONArray("forecastday").getJSONObject(0);

            // Extracts astronomical data (e.g., sunrise, sunset times) from the forecast
            JSONObject astro = forecastDay.getJSONObject("astro");

            // Extracting data from JSON response
            double tempCelsius = current.getDouble("temp_c"); // gets temperature
            double feelsLikeCelsius = current.getDouble("feelslike_c"); //gets feels like temp
            double humidity = current.getDouble("humidity");// gets humidity
            double windSpeed = current.getDouble("wind_kph"); // gets wind Kph
            double pressure = current.getDouble("pressure_mb");// gets pressure
            double uvIndex = current.getDouble("uv");// gets UV index

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

            double averageCloudCover = totalCloudCover / count;//calculates average cloudCover
            double cloudCoverFraction = averageCloudCover/100.0; // converts it to a fraction
            // gets the amount of sunshine reaching the ground bt taking cloudCover ito account
            double effectiveSunShineHours = sunshineHours*(1-cloudCoverFraction);

            // Storing data in the numbers array (adding sunshine hours & cloud cover as well)
            numbers[0] = humidity;
            numbers[1] = windSpeed;
            numbers[2] = pressure;
            numbers[3] = sunshineHours;
            numbers[4] = tempCelsius;
            numbers[5] = feelsLikeCelsius;
            numbers[6] = effectiveSunShineHours;
            numbers[7] = uvIndex;
            numbers[8] = averageCloudCover;


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private double convertToDecimalHours(String time) {// method made for sunshine hours conversion
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


    public double[] getWeatherArray(){ // this is just a getter for the weather array
        return numbers;
    }



}
