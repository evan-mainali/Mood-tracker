package com.moodtracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SleepHours extends CurrentDate {

    private int hours;

    public SleepHours(){
        askSleepHours();
        fileSleepHours();
    }

    private void askSleepHours(){

        System.out.print("Enter how long you slept last night ");
        Scanner input = new Scanner(System.in);
        while (true){
            if(input.hasNext()){
                hours = input.nextInt();
                break;
            }
            else{
                System.out.print("try again, invalid ");

            }

        }
    }
    private void fileSleepHours(){
        try { // this part of the program stores the sleep hours into a file
            BufferedWriter writer = new BufferedWriter(new FileWriter("SleepHours.txt",true));
            writer.write(getCurrentday()+"-"+getCurrentMonth()+"-"+getCurrentYear()+" "+String.valueOf(hours));
            writer.newLine();
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
