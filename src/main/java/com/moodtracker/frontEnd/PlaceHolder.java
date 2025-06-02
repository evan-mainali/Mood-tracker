package com.moodtracker.frontEnd;

import javax.swing.*;

public class PlaceHolder extends JFrame {

    private int outdoorTime; // stores outdoor time
    private int exerciseTime; // stores exercising hours time
    private int sleepingTime;

    private JTextArea area;

    public PlaceHolder(int outdoor, int exercise, int sleeping){
        outdoorTime = outdoor;
        exerciseTime = exercise;
        sleepingTime= sleeping;



        setTitle("PlaceHolder");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        area = new JTextArea(" "+outdoorTime+" "+exerciseTime+" "+sleepingTime);
        area.setBounds(0,0,1000,1000);
        setVisible(true);
        add(area);

    }


}
