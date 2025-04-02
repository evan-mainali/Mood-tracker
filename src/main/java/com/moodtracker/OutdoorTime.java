package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OutdoorTime {

    private double[] weatherArray;
    private String name;
    private int age;
    private int hours;
    private String date;

    public OutdoorTime(String name, int age, String date,double[] array) {
        this.name = name;
        this.age = age;
        this.weatherArray = array;
        this.date=date;
        askOutdoorHours();
        writeOutdoorTime();

    }
    private int askOutdoorHours() {
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
        return hours;
    }

    public void getSunShineHours(){

        System.out.println("Sunshine hours: "+weatherArray[3]+" hours");
        System.out.println("Effective sunshine hours: "+weatherArray[6]+" hours");


    }

    private void writeOutdoorTime(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Outdoortime.txt"));
            writer.write(name+" "+age+" "+date+" "+askOutdoorHours());
            writer.newLine();
            writer.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
