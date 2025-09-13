package com.moodtracker;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FileMoodPointer {

    private long position = 0;
    private final String filePointer = "FileMoodPointer.txt";
    private List<UserInfo> userInfos = new ArrayList<>();

    public FileMoodPointer() {
        // --- LOAD PREVIOUS POINTER ---
        File pf = new File(filePointer);
        if (pf.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePointer))) {
                String savedPos = reader.readLine();
                if (savedPos != null && !savedPos.trim().isEmpty()) {
                    position = Long.parseLong(savedPos.trim());
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading pointer file or parsing position: " + e.getMessage());
            }
        }

        // --- READ NEW MOOD DATA ---
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("Mood.txt", "r");
            raf.seek(position);

            int lineCount = 0;
            String line;
            while ((line = raf.readLine()) != null && lineCount < 7) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // skip empty lines
                }

                String[] parts = line.split("\\s+", 2);
                if (parts.length < 2) {
                    continue;
                }

                String date = parts[0];
                String moodsForDayString = parts[1];

                String chosenMood = determineMoodForDay(moodsForDayString);

                // Only add a UserInfo object if a mood was determined
                if (!chosenMood.isEmpty()) {
                    userInfos.add(new UserInfo(date, chosenMood));
                    lineCount++;
                }
            }

            // --- Calculate percentages ---
            if (!userInfos.isEmpty()) {
                UserInfo.calculateMoodPercentage(userInfos);
            }

            // Saves pointer
            position = raf.getFilePointer();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePointer))) {
                writer.write(String.valueOf(position));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: ");
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (IOException ignored) {}
        }
    }

    private String determineMoodForDay(String moodsLine) {
        String[] moods = moodsLine.toLowerCase().split("\\s*,\\s*|\\s+");
        List<String> validMoods = new ArrayList<>();
        for (String mood : moods) {
            if (!mood.trim().isEmpty()) {
                validMoods.add(mood.trim());
            }
        }

        if (validMoods.isEmpty()) {
            return "";
        }
        if (validMoods.size() == 1) {
            return validMoods.get(0);
        }

        Map<String, Integer> moodCounts = new HashMap<>();
        for (String mood : validMoods) {
            moodCounts.put(mood, moodCounts.getOrDefault(mood, 0) + 1);
        }

        int maxCount = 0;
        String chosenMood = "";

        // Find the mode and check for ties
        for (Map.Entry<String, Integer> entry : moodCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                chosenMood = entry.getKey();
            }
        }

        int tieCount = 0;
        for (int count : moodCounts.values()) {
            if (count == maxCount) {
                tieCount++;
            }
        }

        if (tieCount > 1) {
            // If there's a tie, return the last mood entered
            return validMoods.get(validMoods.size() - 1);
        } else {
            return chosenMood;
        }
    }
    /* public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public List<Double> getPercentage() {
        return UserInfo.getPercentage();
    }

    public List<String> getMoods() {
        return UserInfo.getMoods();
    }

    public List<String> getFullMoods() {
        return UserInfo.getFullMoods();
    }

     */
}