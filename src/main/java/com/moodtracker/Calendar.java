package com.moodtracker;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
public class Calendar extends  CurrentDate{

    private static final String[] DAYS = {"MON", "TUE", "WED",
            "THU", "FRI", "SAT", "SUN"};
    private int year;
    private int month;
    private String[] months = {"JANUARY","FEBUARY","MARCH","APRIL","MAY","JUNE",
            "JULY","AUGUST","SEPETMBER","OCTOBER","NOVEMBER","DECEMBER"};
    private String[][] monthCalendar;

    public Calendar(int year, int month) {
        this.year = year;
        this.month = month;
        generateMonthCalendar();
    }

    public String getMonthString(){
        return months[getCurrentMonth()-1];
    }

    private void generateMonthCalendar() {
        YearMonth yearMonth = YearMonth.of(year, month); // calls a built-in calendar method
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstDay = yearMonth.atDay(1); // helps find first day of the month
        DayOfWeek startDay = firstDay.getDayOfWeek(); // helps find start day of the month

        monthCalendar = new String[6][7];
        int startIndex = startDay.getValue() - 1; // Monday = 0, Sunday = 6
        int dayCounter = 1;

        for (int row = 0; row < monthCalendar.length; row++) {
            for (int col = 0; col < 7; col++) {
                if (row == 0 && col < startIndex) {
                    monthCalendar[row][col] = " "; // Empty cell
                } else if (dayCounter <= daysInMonth) {
                    monthCalendar[row][col] = String.valueOf(dayCounter++);
                } else {
                    monthCalendar[row][col] = " ";
                }
            }
        }
    }



    public String[][] getMonthCalendar(){

        return monthCalendar;
    }

}
