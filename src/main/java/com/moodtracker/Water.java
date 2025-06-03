package com.moodtracker;

public class Water {

    private int water;
    private boolean checker;
    private boolean isNull;

    public Water(){
    }


    public void isWaterValid(String water){

        if(water!=null){
            try{

                this.water=Integer.parseInt(water);
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

    public int getWater(){
        return water;
    }

    public boolean getChecker(){
        return checker;
    }

    public boolean getNull(){
        return isNull;
    }


}
