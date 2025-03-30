package com.moodtracker;

import java.util.Scanner;

public class User {
    private String name;
    private int age;

    public User(String name, int age){ // this class just has the user information like nam and age which is relevant;
        this.name=setName(name);
        this.age=setAge(age);
    }

    private String setName(String name){// these are validators for thr name and ages.
        String userName =" ";
        while(true){
            if(name.matches("[a-zA-Z ]+")){
                userName=name;
                break;
            }
            else{
                System.out.print("Invalid name try again ");
                Scanner input = new Scanner(System.in);
                name= input.nextLine();

            }
        }
        return userName;
    }

    private int setAge(int age){
        int userAge=0;
        while(true){
            if(age<=0 || age>120){
                System.out.print("Invalid age, try again ");
                Scanner input = new Scanner(System.in);
                userAge = input.nextInt();
            }
            else{
                userAge=age;
                break;
            }
        }
        return userAge;
    }

    public String getName(){ // these are getters
        return name;
    }
    public int getAge(){
        return age;
    }
}
