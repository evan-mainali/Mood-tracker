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

    // Renamed lists to reflect storing totals instead of averages
    private List<Double> exerciseTotals = new ArrayList<>();
    private List<String> exerciseDays = new ArrayList<>();
    private List<Double> outdoorTimeTotals = new ArrayList<>();
    private List<String> outdoorTimeDays = new ArrayList<>();
    private List<Double> sleepHoursTotals = new ArrayList<>();
    private List<String> sleepHoursDays = new ArrayList<>();

    public DataProcessor() {
        // Process each file with its own dedicated pointer file, passing the new total lists
        processFile(EXERCISE_FILE, EXERCISE_POINTER_FILE, exerciseTotals, exerciseDays);
        processFile(OUTDOOR_TIME_FILE, OUTDOOR_TIME_POINTER_FILE, outdoorTimeTotals, outdoorTimeDays);
        processFile(SLEEP_HOURS_FILE, SLEEP_HOURS_POINTER_FILE, sleepHoursTotals, sleepHoursDays);
    }


    private void processFile(String dataFileName, String pointerFileName, List<Double> totalsList, List<String> daysList) {
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
                // Called the new calculateTotal method
                double total = calculateTotal(line);

                if (total != -1) {
                    totalsList.add(total); // Add the total
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


    private double calculateTotal(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length < 2) {
            return -1;
        }
        double sum = 0;
        int count = 0; // Keeping count to check if any valid number was processed
        for (int i = 1; i < parts.length; i++) {
            try {
                sum += Double.parseDouble(parts[i]);
                count++;
            } catch (NumberFormatException e) {
                // Ignore invalid numbers
            }
        }
        // Return the sum if at least one valid number was found, otherwise return -1mmmmm
        return count > 0 ? sum : -1;
    }

    // Getters updated to return Totals
    public List<Double> getExerciseTotals() {
        return exerciseTotals;
    }
    public List<String> getExerciseDays() {
        return exerciseDays;
    }

    public List<Double> getOutdoorTimeTotals() {
        return outdoorTimeTotals;
    }
    public List<String> getOutdoorTimeDays() {
        return outdoorTimeDays;
    }

    public List<Double> getSleepHoursTotals() {
        return sleepHoursTotals;
    }
    public List<String> getSleepHoursDays() {
        return sleepHoursDays;
    }

    public void saveAveragesInFile(){

        double exercise = calculateAverage(exerciseTotals);
        double sleep = calculateAverage(sleepHoursTotals);
        double outdoor = calculateAverage(outdoorTimeTotals);

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("ExerciseAverages.txt"));
            writer.write(String.format(exercise+" "));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("OutdoorAverages.txt"));
            writer.write(String.format(outdoor+" "));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("SleepAverages.txt"));
            writer.write(String.format(sleep+" "));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    private double calculateAverage(List<Double> values){

        if (values == null || values.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double v : values) {
            sum += v;
        }
        double avg = sum / values.size();
        return Math.round(avg * 100.0) / 100.0;
    }


}