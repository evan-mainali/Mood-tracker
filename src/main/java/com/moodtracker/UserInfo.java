package com.moodtracker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String date;
    private String name;
    private int age;
    private String mood;

    private static List<Double> percentage = new ArrayList<>();
    private static List<String> moods = new ArrayList<>(7);
    private static List<String> fullMoods=new ArrayList<>();

    public UserInfo(String date, String mood){ // this class will print out the user info links related to their mood.
        this.date=date;
        this.mood=mood;
    }
    public UserInfo(String name,int age){ // different constructor fot UserInfo to store name and age
        this.name=name;
        this.age=age;
    }


    public String getName(){ // these are getters
        return name;
    } // getter for name
    public int getAge(){
        return age;
    } // getter for age
    public String getMood(){
        return mood;
    } // getter for mood

    // Manual Mood Percentage Calculation (No Shortcuts)
    public static void calculateMoodPercentage(List<UserInfo>userInfos) { // method to calculate mood as percentages from Mood.txt file
        // Step 1: Count the total number of users
        int totalUsers = userInfos.size();


        // Step 2: Manually count the number of each mood

        List<Integer> moodCounts = new ArrayList<>();

        for (UserInfo userInfo : userInfos) {
            String userMood = userInfo.getMood();
            boolean found = false;

            // Check if the mood is already in our list
            for (int i = 0; i < moods.size(); i++) {
                if (moods.get(i).equals(userMood)) {
                    moodCounts.set(i, moodCounts.get(i) + 1); // Increase the count manually
                    found = true;
                    break;
                }
            }

            // If it's a new mood, add it to the list
            if (!found) {
                moods.add(userMood);
                moodCounts.add(1);
            }
        }

        // Step 3: Calculate and print percentages manually
        for (int i = 0; i < moods.size(); i++) {
            double percentages = (moodCounts.get(i) * 100.0) / totalUsers;
            double rounded = Math.round(percentages*100.0)/100.0;
            percentage.add(rounded);



        }
        int i =0;

        for(UserInfo userInfo:userInfos){
            String mood = userInfo.getMood();
            fullMoods.add(mood);
        }

    }

    public static List<String> getMoods(){

        return moods;
    }

    public static List<Double> getPercentage(){
        return percentage;

    }
    public static List<String> getFullMoods(){
        return fullMoods;
    }


}
