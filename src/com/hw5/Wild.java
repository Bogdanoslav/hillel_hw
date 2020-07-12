package com.hw5;

public class Wild extends Animal {

    boolean isPredator;

    public Wild(int id, int age, int weight, String color, boolean isPredator) {
        super(id, age, weight, color);
        this.isPredator = isPredator;
    }

    @Override
    public String toString() {
        return "Wild{" +
                "isPredator=" + isPredator +
                ", id=" + id +
                ", age=" + age +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public boolean isPredator() {
        return isPredator;
    }

    public void setPredator(boolean predator) {
        isPredator = predator;
    }

    @Override
    public String Say() {
        if(isPredator)
            return super.Say() + "I am a wild animal and i am angry";
        else
            return super.Say() + "I am a wild animal";
    }
}
