package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExerciseHours extends CurrentDate { // this class is for the exercise hours

    private int hours;

    private String name;

    private int age;

    private boolean checker=true;

    private boolean checkerNull=false;

    public ExerciseHours(){


    }

    public void fileExerciseHours(){ // writes the hours of exercise done to a file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Exercise.txt", true)); // stores excercise hour in a file
            writer.write(getCurrentday()+"-"+getCurrentMonth()+"-"+getCurrentYear()+" "+hours);
            writer.newLine();
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void validateHours(String data){
        if (data == null || data.trim().isEmpty()) {
            checkerNull = true;
        }

        try {
            int number = Integer.parseInt(data);
            hours = number;

            // Reset flags
            checkerNull = false;
            checker = true;


        } catch (NumberFormatException e) {
            checker = false;
             // it's not null, just badly formatted
        }

    }
    public int getExerciseHours(){ // this is just a getter for the hours if required

        return hours;
    }

    public boolean getChecker(){
        return checker;
    }

    public boolean isCheckerNull(){
        return  checkerNull;
    }
}
