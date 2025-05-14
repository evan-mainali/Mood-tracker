package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MoodSelect extends JFrame {

    private JPanel panel;


    public MoodSelect(){
        setTitle("Mood Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLayout(new GridLayout(3,1));

        String[] moods = {"Happy", "Sad", "Regular"};

        HashMap<String,Color> colorMap = new HashMap<>(); //creates a hash map for color

        //places colours in hashmaps
        colorMap.put("Red", Color.RED);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Blue", Color.BLUE);
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Black", Color.DARK_GRAY);
        colorMap.put("White", Color.PINK);
        colorMap.put("Orange", Color.ORANGE);
        colorMap.put("Cyan", Color.CYAN);
        colorMap.put("Magenta", Color.MAGENTA);

        String[] colorNames = colorMap.keySet().toArray(new String[0]);

        // Create three layers
        for (int i = 1; i <= 3; i++) {
            JPanel layerPanel = new JPanel(new BorderLayout());
            layerPanel.setBorder(BorderFactory.createTitledBorder("Layer " + i));

            JComboBox<String> colorComboBox = new JComboBox<>(colorNames);
            JPanel colorDisplayPanel = new JPanel();
            colorDisplayPanel.setPreferredSize(new Dimension(100, 100));
            colorDisplayPanel.setBackground(Color.WHITE);  // default color

            // Listener to change color
            colorComboBox.addActionListener(e -> {
                String selectedColor = (String) colorComboBox.getSelectedItem();
                Color chosenColor = colorMap.get(selectedColor);
                colorDisplayPanel.setBackground(chosenColor);
            });

            // Layout for each layer
            layerPanel.add(colorComboBox, BorderLayout.NORTH);
            layerPanel.add(colorDisplayPanel, BorderLayout.CENTER);

            add(layerPanel);
        }


        setVisible(true);










    }




}
