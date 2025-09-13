package com.moodtracker;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SleepHours extends CurrentDate {

    private double hours;
    private String name;
    private int age;
    private boolean checker;
    private boolean checkerNull=false;
    private String filename = "SleepHours.txt";

    public SleepHours(){

    }

    public void fileSleepHours() {
        List<String> lines = readAllLines(filename);
        String currentDate = new CurrentDate().getDate();
        boolean dateFound = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(currentDate)) {
                lines.set(i, line + " " + hours);
                dateFound = true;
                break;
            }
        }

        if (!dateFound) {
            lines.add(currentDate + " " + hours);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

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



    public double getHours(){
        return hours;
    }

    public void validateHours(String data){
        if (data == null || data.trim().isEmpty()) {
            checkerNull = true;

        }

        try {
            double number = Double.parseDouble(data);
            this.hours = number;


            checker = true;
            checkerNull=false;


        } catch (NumberFormatException e) {
            checker = false;

        }

    }

    public boolean getChecker(){

        return checker;
    }

    public boolean isCheckerNull(){
        return checkerNull;
    }




}
