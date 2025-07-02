package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class BarChart extends JFrame {

    public BarChart(String title) {
        super(title);

        // Create chart
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                "Mood",
                "Exercise hours",
                createDataSet(),
                PlotOrientation.VERTICAL,
                true, true, false);

        // Puts chart into a panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        setContentPane(chartPanel);

        // Sets JFrame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Auto-resize to fit content
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private CategoryDataset createDataSet() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, "FIAT", "Speed");
        dataset.addValue(3.0, "FIAT", "User Rating");
        dataset.addValue(5.0, "FIAT", "Millage");
        dataset.addValue(5.0, "FIAT", "Safety");

        dataset.addValue(5.0, "AUDI", "Speed");
        dataset.addValue(6.0, "AUDI", "User Rating");
        dataset.addValue(10.0, "AUDI", "Millage");
        dataset.addValue(4.0, "AUDI", "Safety");

        dataset.addValue(4.0, "FORD", "Speed");
        dataset.addValue(2.0, "FORD", "User Rating");
        dataset.addValue(3.0, "FORD", "Millage");
        dataset.addValue(6.0, "FORD", "Safety");

        return dataset;
    }
}