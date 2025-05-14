package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MoodSelect extends JFrame {

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



    public MoodSelect(){
        setTitle("Mood Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLayout(null);



        panel = new JPanel();
        panel.setBounds(0, 0, 1000, 1000); // FULL SIZE PANEL
        panel.setLayout(null);
        add(panel);

        labelHappy = new JLabel("Select colour for happy mood");
        labelHappy.setBounds(20, 20, 300, 30);
        panel.add(labelHappy);

        JComboBox<String> box = new JComboBox<>(colors);
        box.setBounds(20, 50, 300, 25);
        panel.add(box);

        // Sad mood label and combo box
        labelSad = new JLabel("Select colour for sad mood");
        labelSad.setBounds(20, 90, 300, 30);
        panel.add(labelSad);

        JComboBox<String> box2 = new JComboBox<>(colors);
        box2.setBounds(20, 120, 300, 25);
        panel.add(box2);

        labelRegular = new JLabel("Select colour for regular mood");
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

        buttonSubmit.addActionListener(e -> {

                    validateColors();

                });
        panel.add(buttonSubmit);
        setVisible(true);
    }


    private void changeBackground(String action){

        for(int i=0;i< colors.length;i++){
            if(action.equals(colors[i])){
                panel.setBackground(backgroundColor[i]);

            }

        }

    }


    private void validateColors(){

        if (colorHappy == null || colorSad == null || colorRegular == null) {
            JOptionPane.showMessageDialog(this, "Please select a color for all moods.", "Missing Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        while (colorHappy.equals(colorSad) || colorHappy.equals(colorRegular) || colorSad.equals(colorRegular)) {
            JOptionPane.showMessageDialog(this, "Each mood must have a different color. Please adjust your selections.", "Duplicate Colors", JOptionPane.ERROR_MESSAGE);
            return; // exits here and waits for user to change selection and re-click Submit
        }

        JOptionPane.showMessageDialog(this, "OK", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Proceed to next page
        NormalPage page = new NormalPage();
        setVisible(false);



    }




}
