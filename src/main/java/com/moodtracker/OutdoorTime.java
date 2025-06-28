package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OutdoorTime extends Weather{


    private String name;
    private int age;
    private int hours;
    private boolean checker=true;
    private boolean checkerNull=false;
    private boolean numberGreater;


    public OutdoorTime() {



    }
    public int getHours(){

        return hours;
    }

    public int sunshineHours(){

        return (int)getWeatherArray()[6];
    }


    public void validateHours(String data){
        if(data!=null || data.trim().isEmpty()) {
            try {
                int number = Integer.parseInt(data);
                hours = number;
                if(number>sunshineHours()){
                    numberGreater=true;

                }
                else {
                    checker = true;
                    numberGreater = false;
                    checkerNull=false;
                }
            } catch (NumberFormatException e) {
                checker = false;

            }
        }
        else{
            checkerNull=true;

        }
    }

    public void fileOutdoorTime(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("OutdoorTime.txt",true));
            writer.write(String.valueOf(hours));
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public boolean getChecker(){
        return checker;
    }

    public boolean isCheckerNull(){
        return checkerNull;
    }

    public boolean isNumberGreater(){ return numberGreater;}






}
