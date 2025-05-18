package com.moodtracker;

import java.util.Scanner;

public class OutdoorTime extends Weather{


    private String name;
    private int age;
    private int hours;
    private boolean checker;

    public OutdoorTime(String name, int age) {
        this.name = name;
        this.age = age;


    }
    public int getHours(){

        return hours;
    }

    public int sunshineHours(){

        return (int)getWeatherArray()[6];
    }


    public void validateHours(String data){
        checker= false;
        try{
            int number = Integer.parseInt(data);
            hours = number;
            System.out.println("correct");
            checker=true;

        }

        catch(NumberFormatException e){
            System.out.println("wrong");
        }
    }

    public boolean getChecker(){
        return checker;
    }




}
