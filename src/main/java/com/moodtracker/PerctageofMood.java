package com.moodtracker;

public class PerctageofMood extends CurrentDate{
    private String[] mood;
    private int[] dates;
    PerctageofMood(){} // separate class made to calculate the percentage
    // of the mood which I will use later on in the pie chart.

    public void calculateMoodPercentage(){



    }

    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("evan");
        return userInfo;

    }

}
