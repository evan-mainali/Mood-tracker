package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExerciseHours extends CurrentDate { // this class is for the exercise hours

    private int hours;

    private String name;

    private int age;

    private boolean checker;

    private boolean checkerNull=false;

    public ExerciseHours(){}

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




    public void validateHours(String hours){ // method to see if entered input is
        if(hours !=null) {
            checker = false;

            try {
                this.hours = Integer.parseInt(hours);
                checker = true;
            }
            catch (NumberFormatException e) {
                checker = false;

            }
        }

        else{

            checkerNull =true;
        }



    }
    public int getExerciseHours(){ // this is just a getter for the hours if required

        return hours;
    }

    public boolean getChecker(){
        return checker;
    } // returns checker

    public boolean isCheckerNull(){
        return  checkerNull;
    } // returns checker null
}
