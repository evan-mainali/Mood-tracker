package com.moodtracker.frontEnd;

import com.moodtracker.FileMoodPointer;
import com.moodtracker.UserInfo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PieChart extends JFrame {

    public static List<String> moods = new ArrayList<String>();

    private JButton button;

    public PieChart() {
        super("Mood Pie Chart");

        // Ensure FileMoodPointer loads the slice and calculates percentages
        FileMoodPointer file = new FileMoodPointer();
        moods = UserInfo.getFullMoods();

        // Build dataset with unique moods + percentages
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (int i = 0; i < UserInfo.getMoods().size(); i++) {
            dataset.setValue(UserInfo.getMoods().get(i), UserInfo.getPercentage().get(i));
        }

        // Create chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Mood Distribution for Recent Slice",
                dataset,
                true,   // legend
                true,   // tooltips
                false   // URLs
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        // Assign colors based on mood type
        for (String mood : UserInfo.getMoods()) {
            if (Arrays.asList(MoodSelect.getNeutralMoods()).contains(mood)) {
                plot.setSectionPaint(mood, ColorSelect.getColourArray()[1]);
            } else if (Arrays.asList(MoodSelect.getPositiveMoods()).contains(mood)) {
                plot.setSectionPaint(mood, ColorSelect.getColourArray()[0]);
            } else if (Arrays.asList(MoodSelect.getNegativeMoods()).contains(mood)) {
                plot.setSectionPaint(mood, ColorSelect.getColourArray()[2]);
            } else {
                plot.setSectionPaint(mood, Color.GRAY); // fallback
            }
        }

        plot.setBackgroundPaint(new Color(137, 207, 240));

        // Set chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(600, 600));

        button = new JButton("Switch to Bar Chart");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(button);

        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Switch to bar chart
        button.addActionListener(e -> {
            setVisible(false);
            new BarChart();
        });
    }


}
