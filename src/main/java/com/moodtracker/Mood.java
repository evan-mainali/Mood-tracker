package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mood extends CurrentDate {// this class, collects mood, sleep hours and exercise hours for later on and uses a API weather.



    private String mood;



    public Mood(String mood) {
        this.mood = mood;
    }





    public void fileMood() {// writes the mood in a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Mood.txt", true))) {
            writer.write(getCurrentday() + "-" + getCurrentMonth() + "-" + getCurrentYear() + " " + mood);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getMood() {
        return mood;
    }

}














