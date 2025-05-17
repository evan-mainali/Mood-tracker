package com.moodtracker;

import java.util.Scanner;

public class OutdoorTime extends Weather{


    private String name;
    private int age;
    private int hours;

    public OutdoorTime(String name, int age) {
        this.name = name;
        this.age = age;


    }
    public void askOutdoorHours() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("How long were you outside for: ");

            if (input.hasNextInt()) {
                hours = input.nextInt();

                if (hours >= 0) { // Accept only non-negative integers
                    break;
                } else {
                    System.out.println("Number has to be positive or zero.");
                }
            } else {
                System.out.println("Input has to be an integer. Try again.");
                input.next(); // Clear the invalid input
            }

        }

    }

    public int getHours(){

        return hours;
    }

    public int sunshineHours(){

        return (int)getWeatherArray()[6];


    }

}
