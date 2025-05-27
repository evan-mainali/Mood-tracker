package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SleepHours extends CurrentDate {

    private int hours;

    private String name;

    private int age;
    private boolean checker;
    private boolean checkerNull=false;

    public SleepHours(){ }


    public void fileSleepHours(){
        try { // this part of the program stores the sleep hours into a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("SleepHours.txt",true));
            writer.write(getCurrentday()+"-"+getCurrentMonth()+"-"+getCurrentYear()+" "+String.valueOf(hours));
            writer.newLine();
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public int getHours(){
        return hours;
    }

    public void validateHours(String hours){
        if(hours!=null) {
            try {
                this.hours = Integer.parseInt(hours);
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
