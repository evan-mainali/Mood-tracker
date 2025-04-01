package com.moodtracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String date;
    private String name;
    private String age;
    private String mood;


    public User(String name, String age, String date, String mood){ // this class will print out the user info links related to their mood.
        this.name=name;
        this.age=age;
        this.date=date;
        this.mood=mood;
    }


    public String getName(){ // these are getters
        return name;
    }
    public String getAge(){
        return age;
    }
    public String getMood(){
        return mood;
    }

    // Manual Mood Percentage Calculation (No Shortcuts)
    public static void calculateMoodPercentage(List<User> users) {
        // Step 1: Count the total number of users
        int totalUsers = users.size();


        // Step 2: Manually count the number of each mood
        List<String> moods = new ArrayList<>();
        List<Integer> moodCounts = new ArrayList<>();

        for (User user : users) {
            String userMood = user.getMood();
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
        System.out.println("Data so far is");
        for (int i = 0; i < moods.size(); i++) {
            double percentage = (moodCounts.get(i) * 100.0) / totalUsers;
            System.out.println(moods.get(i) + ": " + percentage + "%");
        }
    }

    public double[] weatherArrayTypes(){

        Weather weather = new Weather();
        return weather.getWeatherArray();
    }
}
