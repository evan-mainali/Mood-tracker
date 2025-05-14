package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginGUI extends JFrame {


    private JLabel nameLabel;
    private JLabel ageLabel;
    private JTextField textUsername ;
    private JTextField textAge;
    private JButton submitButton;

    private String name;
    private int age;

    public LoginGUI(){
            setTitle("Login Page");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // quit the app when we close the window
            setSize(1000, 1000);
            setLayout(null);



            nameLabel = new JLabel("Enter name"); // creates label for name
            nameLabel.setBounds(50,55,200,30);

            textUsername = new JTextField(); //creates text field for name
            textUsername.setBounds(50, 90, 200, 30);

            ageLabel = new JLabel("Enter age"); //creates label for age
            ageLabel.setBounds(50,120,200,30);

            textAge = new JTextField(); // creates text field for age
            textAge.setBounds(50,150,200,30);

            submitButton = new JButton("Submit"); // creates a button
            submitButton.setBounds(50,200,200,30);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    validateInputs();
                }
            });

            System.out.println(name+" "+age);


            add(textUsername);
            add(textAge);
            add(submitButton);
            add(nameLabel);
            add(ageLabel);


            setVisible(true);

    }

    private void validateInputs() {
        name = textUsername.getText().trim(); // stores the value in textUsername in name;

        String ageText = textAge.getText().trim();


        // Validate name (non-empty and letters only)
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Invalid name. Please enter letters only.");
            return;
        }

        // Validate age (numeric and reasonable range)

        try {
            age = Integer.parseInt(ageText);
            if (age < 1 || age > 120) {
                JOptionPane.showMessageDialog(this, "Age must be between 1 and 120.");

                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid age. Please enter a number.");
            return;
        }

        // If both valid
        JOptionPane.showMessageDialog(this, "Welcome, " + name + "! Your age: " + age);

        System.out.println(name+" "+age);
        setVisible(false);

        MoodSelect display = new MoodSelect(); //next page will become true



    }





}
