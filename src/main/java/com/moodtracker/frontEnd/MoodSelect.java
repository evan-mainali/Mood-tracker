package com.moodtracker.frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoodSelect extends JFrame {

    private JPanel panel;
    private JButton[] colorButtons;  // Array to hold color selection buttons
    private Color selectedColor;

    public MoodSelect(){
        setTitle("Mood Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLayout(null);

        colorButtons = new JButton[5];
        String[] colorNames = {"Red", "Green", "Blue", "Yellow", "Orange"};
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};

        // Create the color buttons and add them to the window
        for (int i = 0; i < colorButtons.length; i++) {
            colorButtons[i] = new JButton(colorNames[i]);
            colorButtons[i].setBackground(colors[i]);
            colorButtons[i].setBounds(50, 300 + (i * 50), 200, 30);
            colorButtons[i].setOpaque(true);
            colorButtons[i].setBorderPainted(false);  // Remove button borders
            colorButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectColor((JButton) e.getSource());
                }
            });
            add(colorButtons[i]);
        }








        setVisible(true);;

    }

    private void selectColor(JButton sourceButton) {
        // Set selected color based on the button clicked
        selectedColor = sourceButton.getBackground();
        JOptionPane.showMessageDialog(this, "Color selected: " + sourceButton.getText());
    }


}
