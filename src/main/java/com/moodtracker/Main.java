package com.moodtracker;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        CurrentDate date = new CurrentDate();

        int year = date.getCurrentYear();
        int month = date.getCurrentMonth();

        Months monthcal = new Months(year,month);
        monthcal.displayMonthCalendar();
        PerctageofMood mood = new PerctageofMood();
        mood.calculateMoodPercentage();
        UserInfo user = new UserInfo();
        user.askExcerciseHours();
        user.askSleepHours();
        user.storeMood();





    }


}