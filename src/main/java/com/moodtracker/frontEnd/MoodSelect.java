package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;

public class MoodSelect extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JButton submitButton;

    private String[] mood = {"HAPPY","SAD","ANGRY","THRILLED","E","5","7","F"};
    private String moodPicked="";


    public MoodSelect() {
        setTitle("Select your Mood");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Set up label
        label = new JLabel("Select your mood from the emojis below:");
        label.setBounds(50, 30, 400, 25);
        add(label);

        // Set up panel
        panel = new JPanel();
        panel.setLayout(null); // Needed if using setBounds inside panel
        panel.setBounds(50, 80, 500, 500);
        add(panel);

        emojiSelector();

        submitButton = new JButton("Submit");
        submitButton.setBounds(50,350,150,100);
        panel.add(submitButton);

        submitButton.addActionListener(e-> {


            setVisible(false);

        });

        setVisible(true);
    }

    private void emojiSelector() { // asks user to select emoji from pop up box
        String[] emojis = { "😀", "😂", "😍", "😎", "😢", "😡", "👍", "🎉" };

        JComboBox<String> moodbox = new JComboBox<>(emojis);
        moodbox.setBounds(0, 0, 300, 30); // Position inside panel
        moodbox.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24)); //

        moodbox.addActionListener(e-> {



            String action = (String) moodbox.getSelectedItem();

            for(int i=0;i< emojis.length;i++){

                if(emojis[i].equals(action)){
                    moodPicked=mood[i];

                }

            }
                });

        panel.add(moodbox);

    }


}
