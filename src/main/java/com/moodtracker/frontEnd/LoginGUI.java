package com.moodtracker.frontEnd;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
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
    ;

    public LoginGUI(){
            setTitle("Login Page");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // quit the app when we close the window
            setSize(1000, 1000);
            setLayout(null);



            nameLabel = new JLabel("Enter name"); // creates label for name
            nameLabel.setBounds(50,55,200,30);
            nameLabel.setForeground(Color.BLACK);
            nameLabel.setFont(new Font("arial",Font.BOLD,18)); // creates font size and type

            textUsername = new JTextField();
            textUsername.setBounds(50, 90, 200, 30);
            textUsername.setForeground(Color.BLACK);
            textUsername.setBackground(new Color(245, 245, 245)); // background
            textUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textUsername.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));

            ageLabel = new JLabel("Enter age"); //creates label for age
            ageLabel.setBounds(50,120,200,30);
            ageLabel.setForeground(Color.BLACK);
            ageLabel.setFont(new Font("arial",Font.BOLD,18)); // creates font size and type

            textAge = new JTextField();
            textAge.setBounds(50, 150, 200, 30);
            textAge.setForeground(Color.BLACK);
            textAge.setBackground(new Color(245, 245, 245));
            textAge.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textAge.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));

            submitButton = new JButton("Login");
            submitButton.setBackground(new Color(66, 135, 245));
            submitButton.setForeground(Color.WHITE);
            submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            submitButton.setFocusPainted(false);
            submitButton.setBorderPainted(false);
            submitButton.setOpaque(true);
            submitButton.setBounds(50, 200, 200, 40);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    validateInputs();
                }
            });




            add(textUsername);
            add(textAge);
            add(submitButton);
            add(nameLabel);
            add(ageLabel);
            add(submitButton);
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


        setVisible(false);

        ColorSelect display = new ColorSelect(); //next page will become true



    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }









}
