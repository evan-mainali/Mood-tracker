package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SleepHours extends CurrentDate {

    private int hours;

    private String name;

    private int age;

    public SleepHours(String name,int age){
        this.name=name;
        this.hours=hours;
        this.age=age;
        fileSleepHours();
    }


    private void fileSleepHours(){
        try { // this part of the program stores the sleep hours into a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("SleepHours.txt",true));
            writer.write(name+" "+age+" "+getCurrentday()+"-"+getCurrentMonth()+"-"+getCurrentYear()+" "+String.valueOf(hours));
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

    public void validateHours(){




    }


}
