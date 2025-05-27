package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.moodtracker.CurrentDate;

public class OutdoorTime extends Weather{


    private String name;
    private int age;
    private int hours;
    private boolean checker;
    private boolean checkerNull=false;
    private boolean greaterTimeEntered=false;
    CurrentDate date=new CurrentDate();


    public OutdoorTime() {

    }
    public int getHours(){

        return hours;
    }

    public int sunshineHours(){

        return (int)getWeatherArray()[6];
    }


    public void validateHours(String data){
        if(data!=null) {
            try {
                int number = Integer.parseInt(data);
                hours = number;
                if(number>sunshineHours()){
                    checker=false;
                    greaterTimeEntered=true;

                }
                checker = true;

            } catch (NumberFormatException e) {
                checker = false;
            }
        }
        else{
            checkerNull=true;

        }
    }

    public boolean getChecker(){
        return checker;
    } // returns checker

    public boolean isCheckerNull(){return checkerNull;} // retunrs checker Null

    public boolean getGreaterTimeEntered(){ return greaterTimeEntered;} // returns time

    public void fileOutdoorTime(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("OutdoorTime.txt", true));
            writer.write(date.getCurrentday()+"-"+ date.getCurrentMonth()+"-"+ date.getCurrentYear()+" "+getHours());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
