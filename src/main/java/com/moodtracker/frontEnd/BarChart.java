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
        setTitle("Daily times");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Create a DataProcessor instance to get the data ---
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.saveAveragesInFile();

        // --- Main panel to hold charts ---
        JPanel chartsPanel = new JPanel();
        chartsPanel.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column

        // --- Exercise Hours Chart ---
        JFreeChart exerciseChart = ChartFactory.createBarChart(
                "Exercise Hours",
                "Day",
                "Hours",
                createDataset(dataProcessor.getExerciseTotals(), dataProcessor.getExerciseDays()),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        chartsPanel.add(new ChartPanel(exerciseChart));

        // --- Outdoor Time Chart ---
        JFreeChart outdoorChart = ChartFactory.createBarChart(
                "Outdoor Time",
                "Day",
                "Hours",
                createDataset(dataProcessor.getOutdoorTimeTotals(), dataProcessor.getOutdoorTimeDays()),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        chartsPanel.add(new ChartPanel(outdoorChart));

        // --- Sleep Hours Chart ---
        JFreeChart sleepChart = ChartFactory.createBarChart(
                "Sleep Hours",
                "Day",
                "Hours",
                createDataset(dataProcessor.getSleepHoursTotals(), dataProcessor.getSleepHoursDays()),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        chartsPanel.add(new ChartPanel(sleepChart));

        // --- Add charts panel to the center ---
        add(chartsPanel, BorderLayout.CENTER);

        // --- Create a button to open TableOfAverages ---
        JButton tableButton = new JButton("Show Table of Averages");
        tableButton.addActionListener(
                e -> new TableOfAverages()
        );
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(tableButton);
        add(buttonPanel, BorderLayout.SOUTH);

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
            dataset.addValue(averages.get(i), "Time", days.get(i));
        }
        return dataset;
    }
}
