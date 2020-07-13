package com.hw5;

public class Wild extends Animal {

    boolean predator;

    public Wild(int id, int age, int weight, String color, boolean predator) {
        super(id, age, weight, color);
        this.predator = predator;
    }

    @Override
    public String toString() {
        return "Wild{" +
                "isPredator=" + predator +
                ", id=" + this.getId() +
                ", age=" + this.getAge() +
                ", weight=" + this.getWeight() +
                ", color='" + this.getColor() + '\'' +
                '}';
    }

    public boolean isPredator() {
        return predator;
    }

    public void setPredator(boolean predator) {
        this.predator = predator;
    }

    @Override
    public String say() {
        if(predator)
            return super.say() + "I am a wild animal and i am angry";
        else
            return super.say() + "I am a wild animal";
    }
}
