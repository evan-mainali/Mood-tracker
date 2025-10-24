package com.moodtracker;

import com.moodtracker.frontEnd.*;

import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String[] userData = LoginGUI.loadUserFromFile();

        if (userData != null) {
            boolean loaded = ColorSelect.loadColorsFromFile();
            if (loaded) {
                new MoodSelect();
            } else {

                new ColorSelect();
            }

        } else {
            new LoginGUI(); // Show login page
        }






















    }
}