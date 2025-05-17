package com.moodtracker.frontEnd;

import javax.swing.*;
import com.moodtracker.Calendar;
import com.moodtracker.CurrentDate;
import  com.moodtracker.OutdoorTime;

import java.awt.*;
import java.util.Arrays;

public class NormalPage extends JFrame {
    JLabel labelCalendar;
    private JPanel panel;
    private JLabel labelDate;
    private JTextField textExer;
    private JLabel exerciseHours;
    private JLabel sleepHour;
    private JTextField textSleep;
    private JLabel outdoorTime;
    private JTextField textOut;
    private JTable table;

    public NormalPage(){

        OutdoorTime time = new OutdoorTime("evan",18);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Normal Page");
        setLayout(null);
        CurrentDate date = new CurrentDate();
        Calendar calendar = new Calendar(date.getCurrentYear(), date.getCurrentMonth());

        int x = 1000-500;

        labelDate = new JLabel(String.valueOf(calendar.getCurrentday()+"-"+ calendar.getMonthString()+ "-"+calendar.getCurrentYear())); // displays current
                                                                                                                                            // date on top right
        labelDate.setBounds(780,0,500,100);
        labelDate.setFont(new Font("Arial",Font.BOLD,30));

        panel = new JPanel();
        panel.setBounds(0,0,600,300);
        panel.setLayout(null);

        exerciseHours = new JLabel("Enter how long you were exercising for ");
        exerciseHours.setBounds(30,40,300,15);


        textExer = new JTextField();
        textExer.setBounds(30,55,250,20);

        sleepHour = new JLabel("Enter your sleep time");
        sleepHour.setBounds(30,85,300,15);

        textSleep = new JTextField();
        textSleep.setBounds(30,100,250,20);


        outdoorTime = new JLabel("<html>Enter how long you were outdoor in the sun for<br>Total Sunshine hours available is " + time.sunshineHours() +" hours" +"</html>");
        outdoorTime.setBounds(30,150,550,30);



        textOut = new JTextField();
        textOut.setBounds(30,190,250,20);

        String[][] calendarData = calendar.getMonthCalendar();
        String[] columnNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        table= new JTable(calendarData,columnNames);
        table.setEnabled(false); // allows no interaction
        table.setRowHeight(30);

        JScrollPane calendarScroll = new JScrollPane(table);
        calendarScroll.setBounds(100, 350, 700, 200);

        labelCalendar = new JLabel(calendar.getMonthString()+" Calendar "+calendar.getCurrentYear());
        labelCalendar.setBounds(100,310,350,30);
        labelCalendar.setFont(new Font("arial",Font.BOLD,15));







        panel.add(exerciseHours);
        panel.add(textExer);
        panel.add(sleepHour);
        panel.add(textSleep);
        panel.add(outdoorTime);
        panel.add(textOut);
        add(labelDate);
        add(panel);
        add(calendarScroll);
        add(labelCalendar);

        setVisible(true);

    }
}
