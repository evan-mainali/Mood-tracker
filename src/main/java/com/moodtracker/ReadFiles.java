package com.moodtracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {


    private static final String FILENAME = "Mood.txt";

    private static int lineCounter=1;


    public ReadFiles() {

    }

    public List<UserInfo> readMoodFile() { // reads data from the file stores the separate data in variables then in a list to user later;
        List<UserInfo> userInfos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] lines = line.split(" ");
                String name = lines[0];
                String age = lines[1];
                String date = lines[2];
                String mood = lines[3];
                UserInfo userInfo = new UserInfo(name,age,date,mood);
                userInfos.add(userInfo);

            }


        }
        catch (IOException e) {

            e.printStackTrace();
        }
        return userInfos;
    }



}


