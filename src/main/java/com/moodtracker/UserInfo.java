package com.moodtracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfo {
    private String date;
    private String mood;

    // Static fields to store results
    private static final List<Double> percentage = new ArrayList<>();
    private static final List<String> moods = new ArrayList<>();
    private static final List<String> fullMoods = new ArrayList<>();

    public UserInfo(String date, String mood) {
        this.date = date;
        this.mood = mood;
    }

    public String getMood() {
        return mood;
    }

    public static void calculateMoodPercentage(List<UserInfo> userInfos) {
        moods.clear();
        percentage.clear();
        fullMoods.clear();

        Map<String, Integer> moodCounts = new HashMap<>();
        int totalMoods = 0;

        for (UserInfo userInfo : userInfos) {
            String mood = userInfo.getMood();
            if (mood != null && !mood.isEmpty()) {
                fullMoods.add(mood);
                totalMoods++;
                moodCounts.put(mood, moodCounts.getOrDefault(mood, 0) + 1);
            }
        }

        // Calculate and store percentages
        for (Map.Entry<String, Integer> entry : moodCounts.entrySet()) {
            String moodKey = entry.getKey();
            int count = entry.getValue();

            moods.add(moodKey);
            double rawPercentage = (double) count * 100.0 / totalMoods;

            // Round to 2 decimal places
            double rounded = Math.round(rawPercentage * 100.0) / 100.0;
            percentage.add(rounded);
        }
    }

    // Getters for the static results
    public static List<String> getFullMoods() {
        return fullMoods;
    }

    public static List<Double> getPercentage() {
        return percentage;
    }

    public static List<String> getMoods() {
        return moods;
    }
}