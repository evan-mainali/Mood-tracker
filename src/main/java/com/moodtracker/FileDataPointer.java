package com.moodtracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataPointer {

    private long position=0;
    private final String datapointer = "FileDataPointer.txt";
    private List<String> exerciseHours = new ArrayList<>();
    private List<String> sleepHours = new ArrayList<>();

    private List<String> outDoorTime = new ArrayList<>();


    public FileDataPointer() {



        try{
            BufferedReader reader = new BufferedReader(new FileReader(datapointer));
            position = Long.parseLong(reader.readLine());
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Error");
        }


        try{

            RandomAccessFile raf = new RandomAccessFile("Exercise.txt","r");
            raf.seek(position);
            int lineCounter =0;
            String line;
            while((line=raf.readLine())!=null){
                DataLists.addExercise(line);
                lineCounter++;

                if(lineCounter==7){
                    break;
                }
            }
            position=raf.getFilePointer();
            BufferedWriter writer = new BufferedWriter(new FileWriter(datapointer));
            writer.write(String.valueOf(position));
            writer.close();
            exerciseHours=DataLists.getExercise();

        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    public List<String> getExerciseHours(){
        return exerciseHours;
    }






}
