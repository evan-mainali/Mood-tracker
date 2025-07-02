package com.moodtracker;

public class Water {

    private double water;
    private boolean checker;
    private boolean isNull;

    public Water(){
    }


    public void isWaterValid(String water){

        if(water!=null || water.trim().isEmpty()){
            try{

                this.water=Double.parseDouble(water);
                checker=true;
                isNull =false;

            }
            catch (NumberFormatException e){
                checker=false;
                isNull=false;
            }
        }
        else{
            isNull=true;


        }
    }

    public double getWater(){
        return water;
    }

    public boolean getChecker(){
        return checker;
    }

    public boolean getNull(){
        return isNull;
    }


}
