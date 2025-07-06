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

public class PieChart extends JFrame {
    private static double[] percentages;

    private Color[] colors;

    private static String[] moods;

    private static String[] fullMoods= new String[7];



    public PieChart(){
        super("Mood Pie Chart");
        setFunctions();
        // Create dataset
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for(int i =0;i<percentages.length;i++){
            dataset.setValue(UserInfo.getMoods().get(i),percentages[i]);

        }

        // Create chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Mood Distribution for past 7 days",
                dataset,
                true,  // legend
                true,  // tooltips
                false  // URLs
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({2})"));

        for(String i:moods){
            for(int j=0;j<MoodSelect.getNeutralMoods().length;j++){
                if(i.equals(MoodSelect.getNeutralMoods()[j])){
                    plot.setSectionPaint(i,ColorSelect.getColourArray()[1]);
                }
                else if(i.equals(MoodSelect.getPositiveMoods()[j])){
                    plot.setSectionPaint(i,ColorSelect.getColourArray()[0]);
                }
                else if(i.equals(MoodSelect.getNegativeMoods()[j])){
                    plot.setSectionPaint(i,ColorSelect.getColourArray()[2]);
                }
            }
            Color babyBlue = new Color(137, 207, 240);
            plot.setBackgroundPaint(babyBlue);

        }

        // Set chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        setContentPane(chartPanel);
        chartPanel.setSize(1000,500);

        // Configure frame
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);






    }

    private void setFunctions(){
        FileMoodPointer file = new FileMoodPointer();
        int size = file.getPercentage().size();
        percentages = new double[size];
        for(int i =0;i<file.getPercentage().size();i++){
            percentages[i]=file.getPercentage().get(i);
        }

        int size2=file.getMoods().size();
        moods=new String[size2];
        for(int i =0;i<file.getMoods().size();i++){
            moods[i]=file.getMoods().get(i);
        }

        for(int i =0;i<file.getFullMoods().size();i++){
            fullMoods[i]=file.getFullMoods().get(i);

        }
    }

    private void getColors(){
        int size =ColorSelect.getColourArray().length;
        colors = new Color[size];

        for(int i=0;i< ColorSelect.getColourArray().length;i++){
            colors[i]=ColorSelect.getColourArray()[i];
        }

    }

    public static String[] getMoods(){
        return moods;
    }
    public static String[] getFullMoods(){
        return fullMoods;
    }



}