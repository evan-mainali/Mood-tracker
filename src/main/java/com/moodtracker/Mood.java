package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mood extends CurrentDate {// this class, collects mood, sleep hours and exercise hours for later on and uses a API weather.



    private String mood;

    private String name;

    private int age;

    public Mood(String name,int age,String mood) {
        this.mood = mood;
        fileMood();
        this.name=name;
        this.age=age;
    }





    private void fileMood() {// writes the private methods in a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Mood.txt", true))) {
            writer.write(name+" "+age+" "+getCurrentday() + "-" + getCurrentMonth() + "-" + getCurrentYear() + " " + mood);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getMood() {
        return mood;
    }

}














