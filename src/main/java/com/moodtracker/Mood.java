package com.moodtracker;

import java.io.*;
import java.util.Scanner;

public class Mood extends CurrentDate {// this class, collects mood, sleep hours and exercise hours for later on and uses a API weather.



    private String mood;
    private String date;


    public Mood(String mood) {
        this.mood = mood;
    }


    public void fileMood() {
        String today = getCurrentday() + "-" + getCurrentMonth() + "-" + getCurrentYear();
        File file = new File("Mood.txt");


        try {
            String lastLine = null;

            // read only the last line
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (lastLine != null && lastLine.startsWith(today)) {
                    // same date → append mood on same line
                    writer.write(", " + mood);
                } else {
                    // different date → new line
                    writer.newLine();
                    writer.write(today + " " + mood);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getMood() {
        return mood;
    }


}














