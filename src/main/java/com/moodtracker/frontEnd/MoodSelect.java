package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MoodSelect extends JFrame {

    private JPanel panel;
    Color[] backgroundColor = {Color.RED,Color.GREEN,Color.YELLOW,Color.BLACK,
            Color.WHITE,Color.ORANGE,Color.MAGENTA,Color.CYAN      } ;

    String[] moods = {"Happy", "Sad", "Regular"};

    String[] colors = {"RED","GREEN","YELLLOW","BLACK","WHITE",
            "ORANGE","MAGENTA","CYAN"};


    public MoodSelect(){
        setTitle("Mood Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLayout(null);


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

        JComboBox box = new JComboBox<String>(colors); // creates display menu
        box.setBounds(0,0,1000,20);

        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = (String) box.getSelectedItem();
                changeBackground(action);


            }
        });



        add(box);


        setVisible(true);

    }


    private void changeBackground(String action){

        for(int i=0;i< colors.length;i++){
            if(action.equals(colors[i])){
                setBackground(backgroundColor[i]);

            }

        }

    }




}
