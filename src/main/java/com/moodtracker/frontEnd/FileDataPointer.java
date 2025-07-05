package com.moodtracker.frontEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataPointer {

    private long position=0;
    private final String datapointer = "DataPointer.txt";
    private List<String> exerciseHours = new ArrayList<>();
    private List<String> sleepHours = new ArrayList<>();

    private List<String> outDoorTime = new ArrayList<>();


    public FileDataPointer(){


        File file = new File(datapointer);

        if(file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(datapointer));
                position = Long.parseLong(reader.readLine());

            } catch (IOException e) {
                System.out.println("Error");
            }
        }

        try{

            RandomAccessFile raf = new RandomAccessFile("OutdoorTime.txt","r");
            raf.seek(position);
            String line;
            int lineCounter =0;

            while((line= raf.readLine())!=null){
                lineCounter++;
                outDoorTime.add(line);

                if(lineCounter==7){
                    break;
                }

            }

            RandomAccessFile raf2 = new RandomAccessFile("Exercise.txt","r");
            raf2.seek(position);
            String line2;
            int lineCounter2=0;

            while((line2= raf.readLine())!=null){
                lineCounter2++;
                exerciseHours.add(line2);

                if(lineCounter2==7){
                    break;
                }

            }

            RandomAccessFile raf3 = new RandomAccessFile("SleepHours","r");
            raf2.seek(position);
            String line3;
            int lineCounter3=0;

            while((line3= raf.readLine())!=null){
                lineCounter3++;
                sleepHours.add(line);

                if(lineCounter3==7){
                    break;
                }

            }


            position = raf2.getFilePointer();
            BufferedWriter writer = new BufferedWriter(new FileWriter(datapointer));
            writer.write(String.valueOf(position));
            writer.close();
            exerciseHours.clear();
            sleepHours.clear();
            outDoorTime.clear();





        }

        catch (IOException e){
            System.out.println("Error");
        }

    }

    public List<String> getExerciseHours(){
        return exerciseHours;
    }

    public List<String> getSleepHours(){
        return sleepHours;
    }

    public List<String> getOutDoorTime(){
        return outDoorTime;
    }








}
