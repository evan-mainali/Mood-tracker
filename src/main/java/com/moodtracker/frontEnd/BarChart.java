package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;

import com.moodtracker.FileDataPointer;
import com.moodtracker.FileMoodPointer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class BarChart extends JFrame {

    private JPanel panel;

    public BarChart(){



        // Create dataset
        CategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Mood Frequency",       // Chart title
                "Mood",                 // X-axis label
                "ExerciseHours",                // Y-axis label
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Get the plot
        CategoryPlot plot = chart.getCategoryPlot();

        // Customize bar renderer (optional)
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer.setSeriesPaint(0, new Color(0,0,255)); // blue bars

        // Add a line renderer to the same dataset
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, Color.BLACK); // red line
        lineRenderer.setSeriesShapesVisible(0, true);

        // Add the line renderer as an additional renderer
        plot.setRenderer(1, lineRenderer);

        // Map dataset to both renderers
        plot.setDataset(1, dataset);

        // Customize chart
        chart.setBackgroundPaint(Color.white);

        // Add to panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

        // JFrame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // Adjust size to fit content
        setLocationRelativeTo(null);
        setVisible(true);



    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        FileDataPointer pointer = new FileDataPointer();
        FileMoodPointer moodPointer = new FileMoodPointer();
        for(int i=0;i< pointer.getExerciseHours().size();i++){
            String mood = moodPointer.getFullMoods().get(i);
            String uniqueCategory = mood + " #" + (i+1);  // e.g., "Calm #1", "Calm #2"
            dataset.addValue(Double.parseDouble(pointer.getExerciseHours().get(i)), "Frequency", uniqueCategory);
        }
        return dataset;
    }




}