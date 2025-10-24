package com.moodtracker.frontEnd;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class TableOfAverages extends JFrame {

    private JTable table;
    private JScrollPane pane;
    private String[] columnNames = {"Week","Average Exercise hours",
            "Average Sleep hours","Average Outdoor hours","Mode of Mood during week"};
    private String[][] data = new String[4][5]; // 4 rows, 5 columns
    private String[] positiveMoods = MoodSelect.getPositiveMoods();
    private String[] negativeMoods = MoodSelect.getNegativeMoods();
    private String[] neutralMoods = MoodSelect.getNeutralMoods();

    private int weekCounter = 0; // track current week (0–3)

    public TableOfAverages() {
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Averages of Weeks");

        // Load previous table data and week counter
        loadTableData();

        // Read last averages
        String exerciseAverage = readLastLine("ExerciseAverages.txt");
        String sleepAverage = readLastLine("SleepAverages.txt");
        String outdoorAverage = readLastLine("OutDoorAverages.txt");

        // Fill current week row
        data[weekCounter][0] = "Week " + (weekCounter + 1);
        data[weekCounter][1] = exerciseAverage;
        data[weekCounter][2] = sleepAverage;
        data[weekCounter][3] = outdoorAverage;
        data[weekCounter][4] = determineMood(); // implement your mood mode logic

        // Increment weekCounter
        weekCounter++;

        // Reset table and counter after 4 weeks
        if (weekCounter >= 4) {
            weekCounter = 0;
            data = new String[4][5]; // clear table
        }

        // Save week counter and table
        saveWeekCounter(weekCounter);

        // Create table
        table = new JTable(data, columnNames);
        table.setRowHeight(24);
        table.setEnabled(false);

        pane = new JScrollPane(table);
        pane.setBounds(50, 50, 900, 400);
        add(pane);

        setVisible(true);
    }

    // Read last line of a file
    private String readLastLine(String filename) {
        String lastLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
        return lastLine;
    }

    // Load saved table data and week counter
    private void loadTableData() {
        File file = new File("TableData.ser");
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            data = (String[][]) ois.readObject();
            weekCounter = readWeekCounter(); // load week counter
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading table data: " + e.getMessage());
        }
    }

    // Save week counter and table
    private void saveWeekCounter(int counter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("WeekCounter.txt"))) {
            writer.write(String.valueOf(counter));
        } catch (IOException e) {
            System.out.println("Error saving week counter: " + e.getMessage());
        }

        // Save table data
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TableData.ser"))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error saving table data: " + e.getMessage());
        }
    }

    // Read week counter from file
    private int readWeekCounter() {
        File file = new File("WeekCounter.txt");
        if (!file.exists()) return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading week counter: " + e.getMessage());
        }
        return 0;
    }

    // Determine mode of mood for the week
    private String determineMood() {
        List<String> moodList = PieChart.getMoods(); // moods from last 7 days
        if (moodList.isEmpty()) return "";

        int positiveCount = 0;
        int negativeCount = 0;
        int neutralCount = 0;

        // Count each type
        for (String m : moodList) {
            if (Arrays.asList(positiveMoods).contains(m)) {
                positiveCount++;
            } else if (Arrays.asList(negativeMoods).contains(m)) {
                negativeCount++;
            } else if (Arrays.asList(neutralMoods).contains(m)) {
                neutralCount++;
            }
        }

        // Find maximum
        int maxCount = Math.max(positiveCount, Math.max(negativeCount, neutralCount));

        // If tie, use last entered mood
        int tieTypes = 0;
        if (positiveCount == maxCount) tieTypes++;
        if (negativeCount == maxCount) tieTypes++;
        if (neutralCount == maxCount) tieTypes++;

        if (tieTypes > 1) {
            String lastMood = moodList.get(moodList.size() - 1);
            if (Arrays.asList(positiveMoods).contains(lastMood)) return "Positive";
            if (Arrays.asList(negativeMoods).contains(lastMood)) return "Negative";
            if (Arrays.asList(neutralMoods).contains(lastMood)) return "Neutral";
        }

        // No tie, return the one with max count
        if (positiveCount == maxCount) return "Positive";
        if (negativeCount == maxCount) return "Negative";
        return "Neutral";
    }
}
