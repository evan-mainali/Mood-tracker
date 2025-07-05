package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SleepHours extends CurrentDate {

    private double hours;

    private String name;

    private int age;
    private boolean checker;
    private boolean checkerNull=false;

    public SleepHours(){

    }


    public void fileSleepHours(){
        try { // this part of the program stores the sleep hours into a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("SleepHours.txt",true));
            writer.write(String.valueOf(hours));
            writer.newLine();
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
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
