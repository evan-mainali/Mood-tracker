package com.moodtracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMood {


    private static final String FILENAME = "Mood.txt";
    private int counter=1;
    private String[] moodArray= new String[14];

   private String[] dates = new String[14];

    public ReadMood() {

        readFile();
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while((line= reader.readLine())!=null){
                System.out.println(line);
            }

        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }



}
