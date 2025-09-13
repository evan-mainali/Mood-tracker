package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;
import com.moodtracker.DataProcessor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.List;

public class BarChart extends JFrame {

    public BarChart() {
        setTitle("Daily Averages");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Create a DataProcessor instance to get the data ---
        DataProcessor dataProcessor = new DataProcessor();

        // --- Create a main panel with a GridLayout to hold the charts ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column

        // --- Create the first chart (Exercise Hours) ---
        JFreeChart exerciseChart = ChartFactory.createBarChart(
                "Exercise Hours",       // Chart title
                "Day",                  // X-axis label
                "Hours",                // Y-axis label
                createDataset(dataProcessor.getExerciseAverages(), dataProcessor.getExerciseDays()),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ChartPanel exercisePanel = new ChartPanel(exerciseChart);
        mainPanel.add(exercisePanel);

        // --- Create the second chart (Outdoor Time) ---
        JFreeChart outdoorTimeChart = ChartFactory.createBarChart(
                "Outdoor Time",         // Chart title
                "Day",                  // X-axis label
                "Hours",                // Y-axis label
                createDataset(dataProcessor.getOutdoorTimeAverages(), dataProcessor.getOutdoorTimeDays()),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ChartPanel outdoorTimePanel = new ChartPanel(outdoorTimeChart);
        mainPanel.add(outdoorTimePanel);

        // --- Create the third chart (Sleep Hours) ---
        JFreeChart sleepHoursChart = ChartFactory.createBarChart(
                "Sleep Hours",          // Chart title
                "Day",                  // X-axis label
                "Hours",                // Y-axis label
                createDataset(dataProcessor.getSleepHoursAverages(), dataProcessor.getSleepHoursDays()),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ChartPanel sleepHoursPanel = new ChartPanel(sleepHoursChart);
        mainPanel.add(sleepHoursPanel);

        // Set the main panel as the content pane for the JFrame
        setContentPane(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private CategoryDataset createDataset(List<Double> averages, List<String> days) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (averages.size() != days.size()) {
            System.err.println("Data error: Averages and days lists do not match in size.");
            return dataset;
        }

        for (int i = 0; i < averages.size(); i++) {
            dataset.addValue(averages.get(i), "Average", days.get(i));
        }
        return dataset;
    }
}