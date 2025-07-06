package com.moodtracker;

import java.util.ArrayList;
import java.util.List;

public class DataLists {


    private List<String> water = new ArrayList<>();
    private List<String> sleepHours = new ArrayList<>();
    private static List<String> exerciseHours = new ArrayList<>();
    private List<String> outDoor = new ArrayList<>();

    public DataLists(){
    }

    public void addSleep(String data){
        sleepHours.add(data);
    }
    public static void addExercise(String data){
        exerciseHours.add(data);
    }
    public void addOutdoor(String data){
        outDoor.add(data);
    }
    public void clearSleep(){
        sleepHours.clear();
    }
    public void clearExercise(){
        exerciseHours.clear();
    }
    public void clearOutdoor(){
        outDoor.clear();
    }

    public static List<String> getExercise(){
        return exerciseHours;
    }
    public List<String> getSleepHours(){
        return sleepHours;
    }
    public List<String> getOutDoor(){
        return outDoor;
    }





}
