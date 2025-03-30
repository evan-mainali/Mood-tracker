package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExerciseHours extends CurrentDate { // this class is for the exercise hours

    private int hours;

    public ExerciseHours(){
        askExerciseHours();
        exerciseFileWriter();
    }

    private void askExerciseHours(){ // validates the input of hours
        while(true){
            System.out.print("Enter how long you have excercised for today ");
            Scanner input = new Scanner(System.in);
            if(input.hasNextInt()){
                hours=input.nextInt();
                break;
            }

            else{
                System.out.println("Invalid data, try again");

            }

        }
    }
    private void exerciseFileWriter(){ // writes the hours of exercise done to a file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Excercise.txt", true)); // stores excercise hour in a file
            writer.write(getCurrentday()+"-"+getCurrentMonth()+"-"+getCurrentYear()+" "+String.valueOf(hours));
            writer.newLine();
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getExerciseHours(){ // this is just a getter for the hours if required
        return hours;
    }

}
