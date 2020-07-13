package com.hw5;

public class Animal {
    //id (у всех животных),
   // name (у домашних),
   // age(у всех животных),
   // weight(у всех животных),
   // color(у всех животных),
   /// isVaccinated(у домашних),
   // isPredator(у диких),
   // isTrained (дрессированная - у собаки-поводыря).
    private int id;
    private int age;
    private  int weight;
    private String color;

    public Animal(int id,  int age, int weight, String color) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    public String say(){
        return ("Hello, ");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", age=" + age +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
