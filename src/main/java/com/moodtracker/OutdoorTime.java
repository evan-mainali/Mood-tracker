package com.moodtracker;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutdoorTime extends Weather{


    private double hours;
    private boolean checker=true;
    private boolean checkerNull=false;
    private boolean numberGreater;
    private CurrentDate date = new CurrentDate();
    private String fileName = "OutdoorTime.txt";

    public OutdoorTime() {



    }
    public double getHours(){

        return hours;
    }

    public int sunshineHours(){

        return (int)getWeatherArray()[6];
    }


    public void validateHours(String data) {
        if (data != null && !data.trim().isEmpty()) { // Corrected check
            try {
                double number = Double.parseDouble(data);
                hours = number;
                if (number > sunshineHours()) {
                    numberGreater = true;
                } else {
                    checker = true;
                    numberGreater = false;
                    checkerNull = false;
                }
            } catch (NumberFormatException e) {
                checker = false;
            }
        } else {
            checkerNull = true;
        }
    }

    public void fileOutdoorTime() {
        List<String> lines = readAllLines(fileName);
        String currentDate = date.getDate();
        boolean dateFound = false;

        // Iterate through the lines to find the current date
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            // Check if the line starts with the current date
            if (line.startsWith(currentDate)) {
                // If date is found, append the new hours to the existing line
                lines.set(i, line + " " + hours);
                dateFound = true;
                break;
            }
        }

        // If the date was not found, add a new line
        if (!dateFound) {
            lines.add(currentDate + " " + hours);
        }

        // Write all lines back to the file, overwriting the old content
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Helper method to read all lines from a file
    private List<String> readAllLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            // File might not exist yet, which is fine
        }
        return lines;
    }

    public boolean getChecker(){
        return checker;
    }

    public boolean isCheckerNull(){
        return checkerNull;
    }

    public boolean isNumberGreater(){ return numberGreater;}






}
