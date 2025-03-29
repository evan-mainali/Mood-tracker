package com.moodtracker;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
public class Months extends  CurrentDate{

    private static final String[] DAYS = {"MONDAY", "TUESDAY", "WEDNESDAY",
            "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    private int year;
    private int month;
    private String[] months = {"JANUARY","FEBUARY","MARCH","APRIL","MAY","JUNE",
            "JULY","AUGUST","SEPETMBER","OCTOBER","NOVEMBER","DECEMBER"};
    private String[][] monthCalendar;

    public Months(int year, int month) {
        this.year = year;
        this.month = month;
        generateMonthCalendar();
    }

    private String getMonthString(){
        return months[getCurrentMonth()-1];
    }

    private void generateMonthCalendar() {
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstDay = yearMonth.atDay(1);
        DayOfWeek startDay = firstDay.getDayOfWeek();

        monthCalendar = new String[6][7]; // Adjusted for weeks & possible extra row
        int startIndex = startDay.getValue() % 7; // Convert Java's Monday=1 format
        int dayCounter = 1;

        for (int row = 0; row < monthCalendar.length; row++) {
            for (int col = 0; col < 7; col++) {
                if (row == 0 && col < startIndex) {
                    monthCalendar[row][col] = "  "; // Empty spots before first day
                } else if (dayCounter <= daysInMonth) {
                    monthCalendar[row][col] = String.format("%2d", dayCounter++);
                } else {
                    monthCalendar[row][col] = "  "; // Empty spots after last day
                }
            }
        }
    }

    public void displayMonthCalendar() {
        System.out.println(getMonthString()+" "+year);
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
        for (String[] week : monthCalendar) {
            for (String day : week) {
                System.out.print(" " + day + " ");
            }
            System.out.println();
        }
    }
















}
