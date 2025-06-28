package com.moodtracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadMoodFile {

    private int lineNumber=0;
    private static final String FILENAME = "Mood.txt";

    private static int lineCounter=1;


    public ReadMoodFile() {

    }

    public List<UserInfo> readMoodFile() { // reads data from the file stores the separate data in variables then in a list to user later;
        List<UserInfo> userInfos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] lines = line.split(" ");
                String date = lines[0];
                String mood = lines[1];
                UserInfo userInfo = new UserInfo(date,mood);
                userInfos.add(userInfo);
                lineNumber++;

            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return userInfos;
    }
    public int getLineNumber(){
        return lineNumber;
    }


}


