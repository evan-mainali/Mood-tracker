package com.moodtracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Weather {
    public  double[] numbers = new double[6];

    Weather(){}


    private String getWeather(){ // returns the result of the weather API thing I'm using.
        String api="";

        try {

            String apiUrl = "https://api.weatherapi.com/v1/current.json?key=5e888cebd7084d2bbe5142331252503&q=London&aqi=no";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // stores the API response in the variable api
            api = response.toString();


        } catch (IOException e) {
            e.printStackTrace();

        }
        return api; // returns the value of the API response.

    }

    public double[] returnFunctions(){

        String weatherData = getWeather();

        // Parsing the extracted weather data
        try {

            JSONObject jsonResponse = new JSONObject(weatherData);
            JSONObject current = jsonResponse.getJSONObject("current");
            double tempCelsius = current.getDouble("temp_c");          // Temperature in °C
            double feelsLikeCelsius = current.getDouble("feelslike_c"); // Feels-like temperature
            String condition = current.getJSONObject("condition").getString("text");  // Extract "Partly cloudy"
            double humidity = current.getInt("humidity");
            double windSpeed = current.getDouble("wind_kph");
            double pressure = current.getDouble("pressure_mb");
            double uvIndex = current.getDouble("uv");
            numbers[0]=humidity;
            numbers[1]=windSpeed;
            numbers[2]=pressure;
            numbers[3]=uvIndex;
            numbers[4]=tempCelsius;
            numbers[5]=feelsLikeCelsius;
        }


        catch (JSONException e) {
            e.printStackTrace();
        }



    return numbers;


    }

}
