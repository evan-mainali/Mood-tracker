package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class ColorSelect extends JFrame {

    private JLabel labelHappy;

    private static Color colorHappy;
    private static Color colorSad;
    private static Color colorRegular;

    private  JLabel labelSad;

    private JPanel panel;

    private  JLabel labelRegular;

    private Color[] backgroundColor = {Color.RED,Color.GREEN,Color.YELLOW,Color.BLACK,
            Color.WHITE,Color.ORANGE,Color.MAGENTA,Color.CYAN      } ;

    private String[] moods = {"Happy", "Sad", "Regular"};

    private String[] colors = {"RED","GREEN","YELLLOW","BLACK","WHITE",
            "ORANGE","MAGENTA","CYAN"};

    private JButton buttonSubmit;

    private JButton resetButton;

    private JButton randomButton;

    private JComboBox<String> box = new JComboBox<>(colors);

    private JComboBox<String> box2 = new JComboBox<>(colors);

    private JComboBox<String> box3 = new JComboBox<String>(colors);



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


        box.setBounds(20, 50, 300, 25);
        box.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        box.setBackground(Color.WHITE);
        box.setForeground(Color.BLACK);

        panel.add(box);

        // Sad mood label and combo box
        labelSad = new JLabel("Select colour for negative moods");
        labelSad.setBounds(20, 90, 300, 30);
        panel.add(labelSad);


        box2.setBounds(20, 120, 300, 25);
        box2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        box2.setBackground(Color.WHITE);
        box2.setForeground(Color.BLACK);
        panel.add(box2);

        labelRegular = new JLabel("Select colour for neutral moods");
        labelRegular.setBounds(20,160,300,25);
        panel.add(labelRegular);


        box3.setBounds(20,190,300,25);
        box3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        box3.setBackground(Color.WHITE);
        box3.setForeground(Color.BLACK);
        panel.add(box3);


        // Action listeners
        box.addActionListener(e -> { // happy mood select;
            String action = (String) box.getSelectedItem();
            changeBackground(action);
            colorHappy= StringToColor(action);
        });

        box2.addActionListener(e -> { // sad mood select
            String action = (String) box2.getSelectedItem();
            changeBackground(action);
            colorSad=StringToColor(action);
        });

        box3.addActionListener( e ->{ // regular mood select
            String action = (String) box3.getSelectedItem();
            changeBackground(action);
            colorRegular=StringToColor(action);


        });

        panel.setBackground(new Color(173, 216, 230));




        buttonSubmit = new JButton("Submit");
        buttonSubmit.setBackground(new Color(66, 135, 245));
        buttonSubmit.setForeground(Color.WHITE);
        buttonSubmit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonSubmit.setFocusPainted(false);
        buttonSubmit.setBorderPainted(false);
        buttonSubmit.setOpaque(true);
        buttonSubmit.setBounds(50, 250, 200, 40);

        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(66, 135, 245));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resetButton.setFocusPainted(false);
        resetButton.setBorderPainted(false);
        resetButton.setOpaque(true);
        resetButton.setBounds(50,300,200,40);

        randomButton = new JButton("Random");
        randomButton.setBackground(new Color(66, 135, 245));
        randomButton.setForeground(Color.WHITE);
        randomButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        randomButton.setFocusPainted(false);
        randomButton.setBorderPainted(false);
        randomButton.setOpaque(true);
        randomButton.setBounds(50,350,200,40);


        panel.add(buttonSubmit);
        panel.add(resetButton);
        panel.add(randomButton);

        resetButton.addActionListener(e->{
            panel.setBackground(new Color(173, 216, 230));
        });

        randomButton.addActionListener(e->{


            setRandomButton();
        });


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


    private void validateColors() {
        if (colorHappy == null || colorSad == null || colorRegular == null) {
            JOptionPane.showMessageDialog(this, "Please select a color for all moods.", "Missing Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (colorHappy.equals(colorSad) || colorHappy.equals(colorRegular) || colorSad.equals(colorRegular)) {
            JOptionPane.showMessageDialog(this, "Each mood must have a different color. Please adjust your selections.", "Duplicate Colors", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Save colors to file
        saveColorsToFile();

        JOptionPane.showMessageDialog(this, "OK", "Success", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);
        new MoodSelect(); // Go to next screen
    }

    private void saveColorsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("colors.txt"))) {
            writer.write(ColorToString(colorHappy));
            writer.newLine();
            writer.write(ColorToString(colorSad));
            writer.newLine();
            writer.write(ColorToString(colorRegular));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ColorToString(Color c) {
        if (c.equals(Color.RED)) return "RED";
        if (c.equals(Color.GREEN)) return "GREEN";
        if (c.equals(Color.YELLOW)) return "YELLOW";
        if (c.equals(Color.BLACK)) return "BLACK";
        if (c.equals(Color.WHITE)) return "WHITE";
        if (c.equals(Color.ORANGE)) return "ORANGE";
        if (c.equals(Color.MAGENTA)) return "MAGENTA";
        if (c.equals(Color.CYAN)) return "CYAN";
        return "BLACK"; // Default if unknown
    }


    public static boolean loadColorsFromFile() {
        File file = new File("colors.txt");
        if (!file.exists()) {
            return false; // No file yet -> no colors saved
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String happy = reader.readLine();
            String sad = reader.readLine();
            String regular = reader.readLine();

            colorHappy = StringToColorStatic(happy);
            colorSad = StringToColorStatic(sad);
            colorRegular = StringToColorStatic(regular);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Because we need this without an object
    private static Color StringToColorStatic(String color) {
        switch (color) {
            case "RED": return Color.RED;
            case "GREEN": return Color.GREEN;
            case "YELLOW": return Color.YELLOW;
            case "BLACK": return Color.BLACK;
            case "WHITE": return Color.WHITE;
            case "ORANGE": return Color.ORANGE;
            case "MAGENTA": return Color.MAGENTA;
            case "CYAN": return Color.CYAN;
            default: return Color.BLACK;
        }
    }



    public static Color[] getColourArray(){
        Color[] array = {colorHappy,colorRegular,colorSad};
        return array;

    }

    private Color StringToColor(String color){

        for(int i =0;i<colors.length;i++){
            if(color.equals(colors[i])){
                return backgroundColor[i];
            }
        }
        return Color.BLACK;
    }

    public void setRandomButton() {
        Random random = new Random();
        int num1;
        int num2;
        int num3;

        do {
            num1 = random.nextInt(8);
            num2 = random.nextInt(8);
            num3 = random.nextInt(8);
        } while (num1 == num2 || num1 == num3 || num2 == num3);


         box.setSelectedItem(colors[num1]);
         box2.setSelectedItem(colors[num2]);
         box3.setSelectedItem(colors[num3]);


    }




}
