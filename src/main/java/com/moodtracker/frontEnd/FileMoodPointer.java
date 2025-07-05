package com.moodtracker.frontEnd;

import com.moodtracker.UserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMoodPointer {

    private long position=0;
    private String line="";
    private final String filePointer="FilePointer.txt";
    private List<UserInfo> userInfos = new ArrayList<>();


    public FileMoodPointer() {
        File pf = new File(filePointer);

        if (pf.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("FilePointer.txt"));
                position = Long.parseLong(reader.readLine());

            } catch (IOException e) {
                System.out.println("Error");
            }
        }


        try {
            RandomAccessFile raf = new RandomAccessFile("Mood.txt", "r");
            raf.seek(position);

            int lineCount = 0;
            while ((line = raf.readLine()) != null) {


                String[] lines = line.split(" ");
                String date = lines[0];
                String mood = lines[1];
                UserInfo userInfo = new UserInfo(date,mood);
                userInfos.add(userInfo);
                System.out.println(line);
                lineCount++;

                if (lineCount == 7) {
                    break;


                }
            }
            UserInfo.calculateMoodPercentage(userInfos);

            // Save current file pointer for next run
            position = raf.getFilePointer();
            BufferedWriter writer = new BufferedWriter(new FileWriter("FilePointer.txt"));
            writer.write(String.valueOf(position));
            writer.close();




        } catch (IOException e) {
            System.out.println("Error reading file");
        }



    }

    public List<Double> getPercentage(){
        userInfos.clear();
        return UserInfo.getPercentage();

    }
    public List<String> getMoods(){
        return UserInfo.getMoods();

    }

    public List<String> getFullMoods(){
        return UserInfo.getFullMoods();
    }


}
