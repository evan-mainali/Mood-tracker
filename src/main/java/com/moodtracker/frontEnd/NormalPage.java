package com.moodtracker.frontEnd;

import javax.swing.*;

import com.moodtracker.*;

import java.awt.*;

public class NormalPage extends JFrame{
    JLabel labelCalendar;
    private JPanel panel;
    private JLabel labelDate;
    private JTextField textExer;
    private JLabel exerciseHours;
    private JLabel sleepHour;
    private JTextField textSleep;
    private JLabel timeOut;
    private JTextField textOut;
    private JTable table;
    private JButton submitButton;

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
        timeOut.setBounds(30,150,550,30);



        textOut = new JTextField();
        textOut.setBounds(30,190,250,20);

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

            if(outDoortime.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"enter something for time spent in sun");
                return;
            }
            else if(hoursExercised.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"enter something for how long you exercised");
                return;
            }
            else if(sleptTime.isCheckerNull()){
                JOptionPane.showMessageDialog(this,"enter something for how long you slept");
                return;
            }
            else if(!outDoortime.getChecker()){

                JOptionPane.showMessageDialog(this,"invalid time in sun");
                return;
            }
            else if(outDoortime.getGreaterTimeEntered()){
                JOptionPane.showMessageDialog(this,"enter less time than number shown for sunshine hours");
                return;
            }
            else if(!hoursExercised.getChecker()){
                JOptionPane.showMessageDialog(this,"invalid input for exercised hours");
                return;
            }
            else if(!sleptTime.getChecker()){
                JOptionPane.showMessageDialog(this,"invalid input for slept time");
                return;
            }
            else{
                JOptionPane.showMessageDialog(this,"Valid inputs");
            }


            outDoortime.fileOutdoorTime(); // files input
            sleptTime.fileSleepHours(); // files input
            hoursExercised.fileExerciseHours(); // files input
            lines++;

            setVisible(false);
            PieChart chart = new PieChart();






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

       add(calendarScroll);

        setVisible(true);

    }




}
