package com.moodtracker.frontEnd;

import com.moodtracker.Mood;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MoodSelect extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JButton submitButton;
    private JLabel labelNumbers;
    private JTextField text;


    private String emojiMood;
    private int numberMood;

    private String[] mood = {
            "Joyful",      // Positive
            "Motivated",   // Positive
            "Calm",        // Neutral
            "Bored",       // Neutral
            "Tired",       // Neutral
            "Frustrated",  // Negative
            "Anxious",     // Negative
            "Sad" ,         // Negative
            "Happy"         // positive
    };

    private static String[] neutralMoods = {"Calm","Bored","Tired"};
    private static String[] positiveMoods = {"Happy","Joyful","Motivated"};
    private static String[] negativeMoods = {"Sad","Anxious","Frustrated"};
    private static String moodPicked="";

    private Integer[] numbers = {1,2,3,4,5,6,7,8,9};
    Random random;
    int randomNumber;


    public MoodSelect() {

        random = new Random(); // ca;; Random class
        randomNumber=random.nextInt(2)+1; // make a random number appear between 1 and 2
        // of the mood methods to display.


        setTitle("Select your Mood"); // set title
        setSize(1000, 1000); // sets size of Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        panel = new JPanel(); // creates panel
        panel.setLayout(null); // sets layout null
        panel.setBounds(50, 80, 500, 500); // sets bounds
        add(panel); // adds it to the frame


        if(randomNumber==1){
            emojiSelector(); // calls emoji selector method
        }

        else{

            numberSelector(); // calls numberSelector method
        }




        submitButton = new JButton("Submit"); // makes a submit button
        submitButton.setBounds(50,350,150,100);
        panel.add(submitButton);
        setVisible(true);

        submitButton.addActionListener(e-> {

            setVisible(false);
            Mood mood = new Mood(moodPicked);
            mood.fileMood();

            NormalPage page = new NormalPage();
        });


    }

    private void emojiSelector() { // asks user to select emoji from pop up box
        String[] emojis = {
                "😊",  // Joyful
                "😀",  // Happy
                "💪",  // Motivated
                "🧘",  // Calm
                "😐",  // Bored
                "😴",  // Tired
                "😤",  // Frustrated
                "😰",  // Anxious
                "😢"   // Sad
        };


        JComboBox<String> moodbox = new JComboBox<>(emojis);
        moodbox.setBounds(0, 30, 300, 30); // Position inside panel
        moodbox.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24)); // // helps make the font better for emojis

        label = new JLabel("Select from the emojis below how you feel ");// adds text for emoji selction
        label.setBounds(0, 0, 400, 25);

        panel.add(label);

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


    private void numberSelector(){ //method for choosing number for mood


        JComboBox<Integer> box = new JComboBox<>(numbers);

        labelNumbers = new JLabel("Select a number for how you feel"); // creates a JLabel for selecting numbers
        labelNumbers.setBounds(50,0,400,25);
        box.setBounds(0,30,300,30);

        panel.add(box);
        panel.add(labelNumbers);

        box.addActionListener(e -> {
            numberMood = (Integer) box.getSelectedItem();
            if (numberMood >= 1 && numberMood <= mood.length) {
                moodPicked = mood[numberMood - 1]; // Adjust for 0-based index
            }
        });

    }




    public static String getMood(){
        return moodPicked;
    }

    public static String[] getPositiveMoods(){
        return positiveMoods;
    }
    public static String[] getNeutralMoods(){
        return neutralMoods;
    }

    public static String[] getNegativeMoods(){
        return negativeMoods;
    }



}
