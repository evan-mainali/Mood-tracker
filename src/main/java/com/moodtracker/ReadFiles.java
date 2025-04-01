package com.moodtracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {


    private static final String FILENAME = "Mood.txt";

    private static int lineCounter=1;


    public ReadFiles() {

    }

    public List<User> readMoodFile() { // reads data from the file stroes the seperate data in variables then in a list to user later;
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] lines = line.split(" ");
                String name = lines[0];
                String age = lines[1];
                String date = lines[2];
                String mood = lines[3];
                User user = new User(name,age,date,mood);
                users.add(user);

            }


        }
        catch (IOException e) {

            e.printStackTrace();
        }
        return users;
    }



}


