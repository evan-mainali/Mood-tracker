package com.moodtracker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ReadMoodFile file = new ReadMoodFile();
        UserInfo.calculateMoodPercentage(file.readMoodFile());
        System.out.println(file.getLineNumber());








    }


}