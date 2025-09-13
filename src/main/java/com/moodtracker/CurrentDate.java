package com.moodtracker;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CurrentDate {


        protected DayOfWeek currentday;
        protected String date;

        public CurrentDate(){
           setCurrentdate();

        }

        private void setCurrentdate(){
            LocalDate currentdate = LocalDate.now();
            String date = currentdate.toString();
            currentday= currentdate.getDayOfWeek();
            this.date=date;
        }



        public int getCurrentday(){ // this method displays the day like Wedneday.

            int counter =0;
            String day="";
            for(char c :date.toCharArray()){

                if(c=='-'){
                    counter++;
                }
                else if(counter==2){
                    day+=Character.toString(c);
                }

            }
            return Integer.parseInt(day);

        }

        public int getCurrentMonth(){ // this method gets the current month;

            int counter=0;
            int month=0;
            for(char c:date.toCharArray()){

                if(c=='-'){
                    counter++;
                }
                else if(counter==1){
                    month+=Integer.parseInt(String.valueOf(c));
                }

            }
            return month;
        }


        public int getCurrentYear(){ // this method gets the current year
            String year="";
            int counter=0;

            for(char c:date.toCharArray()){

                if(c=='-') {
                    counter++;
                }
                else if(counter==0){
                    year+=Character.toString(c);
                }
            }
            return Integer.parseInt(year);


        }

        public String getDate(){
            return getCurrentday()+"-"+getCurrentMonth()+"-"+getCurrentYear();
        }
}
