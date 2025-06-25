package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;

public class ColorSelect extends JFrame {

    private JLabel labelHappy;

    private String colorHappy;
    private String colorSad;
    private String colorRegular;

    private JLabel labelSad;

    private JPanel panel;

    private JLabel labelRegular;

    private Color[] backgroundColor = {Color.RED,Color.GREEN,Color.YELLOW,Color.BLACK,
            Color.WHITE,Color.ORANGE,Color.MAGENTA,Color.CYAN      } ;

    private String[] moods = {"Happy", "Sad", "Regular"};

    private String[] colors = {"RED","GREEN","YELLLOW","BLACK","WHITE",
            "ORANGE","MAGENTA","CYAN"};

    private JButton buttonSubmit;



    public ColorSelect(){
        setTitle("Mood Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLayout(null);



        panel = new JPanel();
        panel.setBounds(0, 0, 1000, 1000); // FULL SIZE PANEL
        panel.setLayout(null);
        add(panel);

        labelHappy = new JLabel("Select colour for positive moods");
        labelHappy.setBounds(20, 20, 300, 30);
        panel.add(labelHappy);

        JComboBox<String> box = new JComboBox<>(colors);
        box.setBounds(20, 50, 300, 25);
        panel.add(box);

        // Sad mood label and combo box
        labelSad = new JLabel("Select colour for negative moods");
        labelSad.setBounds(20, 90, 300, 30);
        panel.add(labelSad);

        JComboBox<String> box2 = new JComboBox<>(colors);
        box2.setBounds(20, 120, 300, 25);
        panel.add(box2);

        labelRegular = new JLabel("Select colour for neutral moods");
        labelRegular.setBounds(20,160,300,25);
        panel.add(labelRegular);

        JComboBox<String> box3 = new JComboBox<String>(colors);
        box3.setBounds(20,190,300,25);
        panel.add(box3);


        // Action listeners
        box.addActionListener(e -> { // happy mood select;
            String action = (String) box.getSelectedItem();
            changeBackground(action);
            colorHappy=action;
        });

        box2.addActionListener(e -> { // sad mood select
            String action = (String) box2.getSelectedItem();
            changeBackground(action);
            colorSad=action;
        });

        box3.addActionListener( e ->{ // regular mood select
            String action = (String) box3.getSelectedItem();
            changeBackground(action);
            colorRegular=action;


        });




        buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(20,300,100,30);
        panel.add(buttonSubmit);

        buttonSubmit.addActionListener(e -> {

                    validateColors();


                });
        setVisible(true);


    }


    private void changeBackground(String action){ //method for changing backgroung colour

        for(int i=0;i< colors.length;i++){
            if(action.equals(colors[i])){
                panel.setBackground(backgroundColor[i]);

            }

        }

    }


    private void validateColors(){ // validates colours, will not work if two colours are the same for each mood select.

        if (colorHappy == null || colorSad == null || colorRegular == null) {
            JOptionPane.showMessageDialog(this, "Please select a color for all moods.", "Missing Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        while (colorHappy.equals(colorSad) || colorHappy.equals(colorRegular) || colorSad.equals(colorRegular)) {
            JOptionPane.showMessageDialog(this, "Each mood must have a different color. Please adjust your selections.", "Duplicate Colors", JOptionPane.ERROR_MESSAGE);
            return; // exits here and waits for user to change selection and re-click Submit
        }

        JOptionPane.showMessageDialog(this, "OK", "Success", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);
        MoodSelect moodSelect = new MoodSelect();
        // Proceed to next page




    }
}
