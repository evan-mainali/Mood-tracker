package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/* move the logic for the seperate methods into a different class
* Only have getters and setters for this class UserInfo
* Crete a seperate reader class which will read the data in UserInfo file in a class called UserInfoReader
* Create a method in this class to read line by line and seprate it by a space.
* Input of this method should take the file name
* Put these sepearet words into UserInfo
*
*
*
*
*
* */
public class UserInfo extends CurrentDate {// this class, collects mood, sleep hours and exercise hours for later on and uses a API weather.

    protected String[] moodSelect = {"HAPPY", "SAD", "ANGRY", "OK"};

    private String name;
    private int age;

    public UserInfo() {}


    private String askMood() { // part of the program asks for the mood of the user

        boolean checkInput = false;
        String mood = "";

        // Display mood options once
        Scanner input = new Scanner(System.in);

        System.out.println("Select your mood from the following: ");
        for (String i : moodSelect) {
            System.out.print(i + ", ");
        }
        System.out.println(); // Move to next line

        // Keep asking until a valid input is given
        while (!checkInput) {
            mood = input.nextLine().toUpperCase();

            for (String i : moodSelect) {
                if (mood.equals(i)) {
                    checkInput = true;
                    break; // Exit loop if valid mood is found
                }
            }

            if (!checkInput) {
                System.out.println("Invalid mood. Try again: ");
            }
        }

        return mood;

    }


    public String storeMood() {// writes the private methods in a file
        String mood=" ";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UserInfo.txt", true))) {
            int currentDate = getCurrentday();
            int currentYear = getCurrentYear();
            mood = askMood();

            writer.write(currentDate + " " + currentYear + " " + mood);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return mood;

    }

    public int askSleepHours(){ // asks how long you slept for, validating your hours in case of invalid data.
        int number;
        System.out.print("Enter how long you slept last night ");
        Scanner input = new Scanner(System.in);
        while (true){
            if(input.hasNext()){
                number = input.nextInt();
                break;
            }
            else{
                System.out.print("try again, invalid ");

            }

        }
        try { // this part of the program stores the sleep hours into a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("SleepHours.txt",true));
            writer.write(String.valueOf(number)+"hours" + " "+getCurrentday()+" "+getCurrentMonth()+" "+getCurrentYear());
            writer.newLine();
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }



        return number;
    }

    public int askExcerciseHours(){
        int excerciseHour=0;

        while(true){
            System.out.print("Enter how long you have excercised for today ");
            Scanner input = new Scanner(System.in);
            if(input.hasNextInt()){
                excerciseHour=input.nextInt();
                break;
            }

            else{
                System.out.println("Invalid data, try again");

            }

        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Excercise.txt", true)); // stores excercise hour in a file
            writer.write(String.valueOf(excerciseHour)+"hours"+" "+getCurrentday()+" "+getCurrentYear()+" "+getCurrentMonth());
            writer.newLine();
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return excerciseHour;


    }
    public void getApiWeather(){

        Weather weather = new Weather();
        for(double i: weather.returnFunctions()){
            System.out.print(i+" ");
        }



    }

    public void setName(String name){
        this.name=name;
    }










}














