package com.moodtracker;

import java.util.Scanner;

public class OutdoorTime extends Weather{


    private String name;
    private int age;
    private int hours;
    private boolean checker;
    private boolean checkerNull=false;

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
                System.out.println("correct");
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
    }

    public boolean isCheckerNull(){
        return checkerNull;
    }




}
