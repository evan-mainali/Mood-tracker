package com.moodtracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    private final String EXERCISE_FILE = "Exercise.txt";
    private final String OUTDOOR_TIME_FILE = "OutdoorTime.txt";
    private final String SLEEP_HOURS_FILE = "SleepHours.txt";

    private final String EXERCISE_POINTER_FILE = "ExercisePointer.txt";
    private final String OUTDOOR_TIME_POINTER_FILE = "OutdoorTimePointer.txt";
    private final String SLEEP_HOURS_POINTER_FILE = "SleepHoursPointer.txt";

    private List<Double> exerciseAverages = new ArrayList<>();
    private List<String> exerciseDays = new ArrayList<>();
    private List<Double> outdoorTimeAverages = new ArrayList<>();
    private List<String> outdoorTimeDays = new ArrayList<>();
    private List<Double> sleepHoursAverages = new ArrayList<>();
    private List<String> sleepHoursDays = new ArrayList<>();

    public DataProcessor() {
        // Process each file with its own dedicated pointer file
        processFile(EXERCISE_FILE, EXERCISE_POINTER_FILE, exerciseAverages, exerciseDays);
        processFile(OUTDOOR_TIME_FILE, OUTDOOR_TIME_POINTER_FILE, outdoorTimeAverages, outdoorTimeDays);
        processFile(SLEEP_HOURS_FILE, SLEEP_HOURS_POINTER_FILE, sleepHoursAverages, sleepHoursDays);
    }

    private void processFile(String dataFileName, String pointerFileName, List<Double> averagesList, List<String> daysList) {
        long position = loadPointer(pointerFileName);
        long newPosition = position;

        try (RandomAccessFile raf = new RandomAccessFile(dataFileName, "r")) {
            raf.seek(position);
            String line;
            int lineCount = 0;
            while ((line = raf.readLine()) != null && lineCount < 7) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+", 2);
                String day = parts[0];
                double average = calculateAverage(line);

                if (average != -1) {
                    averagesList.add(average);
                    daysList.add(day); // Store the day
                    lineCount++;
                }
            }
            newPosition = raf.getFilePointer();
        } catch (IOException e) {
            System.err.println("Error processing file " + dataFileName + ": " + e.getMessage());
        } finally {
            savePointer(pointerFileName, newPosition);
        }
    }

    private long loadPointer(String pointerFileName) {
        File pointerFile = new File(pointerFileName);
        if (pointerFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(pointerFile))) {
                String savedPos = reader.readLine();
                if (savedPos != null && !savedPos.trim().isEmpty()) {
                    return Long.parseLong(savedPos.trim());
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error loading pointer from " + pointerFileName + ": " + e.getMessage());
            }
        }
        return 0;
    }

    private void savePointer(String pointerFileName, long newPosition) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pointerFileName))) {
            writer.write(String.valueOf(newPosition));
        } catch (IOException e) {
            System.err.println("Error saving pointer to " + pointerFileName + ": " + e.getMessage());
        }
    }

    private double calculateAverage(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length < 2) {
            return -1;
        }
        double sum = 0;
        int count = 0;
        for (int i = 1; i < parts.length; i++) {
            try {
                sum += Double.parseDouble(parts[i]);
                count++;
            } catch (NumberFormatException e) {
                // Ignore invalid numbers
            }
        }
        return count > 0 ? sum / count : -1;
    }

    // Getters for the processed data
    public List<Double> getExerciseAverages() {
        return exerciseAverages;
    }
    public List<String> getExerciseDays() {
        return exerciseDays;
    }

    public List<Double> getOutdoorTimeAverages() {
        return outdoorTimeAverages;
    }
    public List<String> getOutdoorTimeDays() {
        return outdoorTimeDays;
    }

    public List<Double> getSleepHoursAverages() {
        return sleepHoursAverages;
    }
    public List<String> getSleepHoursDays() {
        return sleepHoursDays;
    }
}