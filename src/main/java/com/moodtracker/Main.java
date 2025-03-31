package com.moodtracker;


import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ReadFiles read = new ReadFiles();
        List<User> user = read.readMoodFile();
        User.calculateMoodPercentage(user);





    }

    public static String setName(){
        String username=" ";
        while(true){
            System.out.print("Enter name ");
            Scanner input = new Scanner(System.in);
            String name = input.nextLine();
            if(name.matches("^[A-Za-z]+(?:\\s[A-Za-z]+)*\\s*$")){
                username=name;
                break;
            }
            else{
                System.out.print("try again");
            }
        }
        return username;
    }
    public static int setAge() {


        int age=0;
        while(true){
            System.out.print("Enter age ");
            Scanner input = new Scanner(System.in);
            if(input.hasNextInt()){
                age= input.nextInt();
                break;
            }
            else{
                System.out.print("try again ");
            }
        }


        return age;
    }

}