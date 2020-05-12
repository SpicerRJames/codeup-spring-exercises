package com.codeup.springblogapp.model;

public class Dice {

    boolean roll;

    public Dice(boolean roll) {
        this.roll = roll;
    }

    public boolean isRoll() {
        return roll;
    }

    public void setRoll(boolean roll) {
        this.roll = roll;
    }

    public int rollDice(){
        int max = 6;
        int min = 1;

        int roll = (int) (Math.random() * (max - min + 1) + min);
        return roll;
    }

}
