package com.moodtracker.frontEnd;

import javax.swing.*;

import com.moodtracker.*;

import java.awt.*;

public class NormalPage extends JFrame{
    JLabel labelCalendar;
    private JPanel panel; // variable for panel with JPanel class
    private JLabel labelDate; // variable for date with JLabel class
    private JTextField textExer; // variable for exercise wirh JTextField class
    private JLabel exerciseHours; //
    private JLabel sleepHour;
    private JTextField textSleep;
    private JLabel timeOut;
    private JTextField textOut;
    private JTable table;
    private JButton submitButton;
    private JLabel water;
    private JTextField waterText;

    private static int lines=0;

    public NormalPage() {


        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Normal Page");
        setLayout(null);
        CurrentDate date = new CurrentDate();
        Calendar calendar = new Calendar(date.getCurrentYear(), date.getCurrentMonth());
        OutdoorTime outDoortime = new OutdoorTime();
        ExerciseHours hoursExercised= new ExerciseHours();
        SleepHours sleptTime = new SleepHours();
        Water waterDrank=new Water();

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


        timeOut = new JLabel("<html>Enter how long you were outdoor in the sun for<br>Total Sunshine hours available today is " + outDoortime.sunshineHours() +" hours" +"</html>");
        timeOut.setBounds(30,130,550,30);

        textOut = new JTextField();
        textOut.setBounds(30,160,250,20);

        water = new JLabel("Enter water drunk in litres");
        water.setBounds(30,190,250,20);

        waterText = new JTextField();
        waterText.setBounds(30,210,250,20);


        String[][] calendarData = calendar.getMonthCalendar();
        String[] columnNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        table= new JTable(calendarData,columnNames);
        table.setEnabled(false); // allows no interaction
        table.setRowHeight(20);

        JScrollPane calendarScroll = new JScrollPane(table);
        calendarScroll.setBounds(100, 350, 700, 142);

        labelCalendar = new JLabel(calendar.getMonthString()+" Calendar "+calendar.getCurrentYear());
        labelCalendar.setBounds(100,310,350,30);
        labelCalendar.setFont(new Font("arial",Font.BOLD,15));

        submitButton = new JButton("Submit");
        submitButton.setBounds(30,250,150,30);

        submitButton.addActionListener(e-> {

           outDoortime.validateHours(textOut.getText());
           hoursExercised.validateHours(textExer.getText());
           sleptTime.validateHours(textSleep.getText());
           waterDrank.isWaterValid(waterText.getText());

            if((outDoortime.isCheckerNull() && hoursExercised.isCheckerNull()) && sleptTime.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"All fields Empty");
                return;
            }
            else if(outDoortime.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"Time spent in sun is empty");
                return;
            }
            else if(hoursExercised.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"Time for hours exercised is empty");
                return;
            }
            else if(sleptTime.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"Time slept is empty");
                return;
            }

            else if(!outDoortime.getChecker()){
                JOptionPane.showMessageDialog(this,"Invalid type for outdoor time");
                return;
            }

            else if(outDoortime.isNumberGreater()){
                JOptionPane.showMessageDialog(this,"number entered is greater");
                return;
            }
            else if(!sleptTime.getChecker()){
                JOptionPane.showMessageDialog(this,"Invalid type for sleeping time");
                return;
            }
            else if(!hoursExercised.getChecker()){
                JOptionPane.showMessageDialog(this,"Invalid type for exercise hours");
                return;
            }
            else if(!waterDrank.getChecker()){
                JOptionPane.showMessageDialog(this, "Invalid water drank in litres");
            }
            else{
                JOptionPane.showMessageDialog(this,"all valid inputs");
                sleptTime.fileSleepHours(); // files input
                hoursExercised.fileExerciseHours(); // files input
                outDoortime.fileOutdoorTime();

                PieChart chart = new PieChart();
                setVisible(false);
            }

        });

        panel.add(exerciseHours);
        panel.add(textExer);
        panel.add(sleepHour);
        panel.add(textSleep);
        panel.add(timeOut);
        panel.add(textOut);
        add(labelDate);
        add(panel);
        panel.add(submitButton);
        panel.add(water);
        panel.add(waterText);

       add(calendarScroll);

        setVisible(true);

    }



}
