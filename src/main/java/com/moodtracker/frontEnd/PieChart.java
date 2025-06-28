package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;

public class PieChart extends JFrame {

    private PieChartPanel pieChartPanel;

    public PieChart(){
        setTitle("PieChart for moods");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLocationRelativeTo(null);

        pieChartPanel = new PieChartPanel();
        pieChartPanel.setBackground(Color.white);
        add(pieChartPanel);
        setVisible(true);

    }






}