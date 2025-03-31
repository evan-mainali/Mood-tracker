package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mood extends CurrentDate {// this class, collects mood, sleep hours and exercise hours for later on and uses a API weather.

    protected String[] moodSelect = {"HAPPY", "SAD", "ANGRY", "OK"};

    private String mood;

    private String name;

    private int age;

    public Mood(String name,int age) {
        this.mood = askMood();
        fileMood();
        this.name=name;
        this.age=age;
    }


    private String askMood() { // part of the program asks for the mood of the user

        boolean checkInput = false;
        String userMood = " ";

        // Display mood options once
        System.out.println("Select your mood from the following: ");
        Scanner input = new Scanner(System.in);
        for (String i : moodSelect) {
            System.out.print(i + ", ");
        }
        System.out.println(); // Move to next line

        // Keep asking until a valid input is given
        while (!checkInput) {
            userMood = input.nextLine().toUpperCase();

            for (String i : moodSelect) {
                if (userMood.equals(i)) {
                    checkInput = true;
                    break; // Exit loop if valid mood is found
                }
            }

            if (!checkInput) {
                System.out.println("Invalid mood. Try again: ");
            }
        }
        return userMood;


    }


    private void fileMood() {// writes the private methods in a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Mood.txt", true))) {
            writer.write(name+" "+age+" "+getCurrentday() + "-" + getCurrentMonth() + "-" + getCurrentYear() + " " + mood);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getMood() {
        return mood;
    }

}














