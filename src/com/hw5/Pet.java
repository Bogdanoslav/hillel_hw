package com.hw5;

public class Pet extends Animal{
    String name;
    boolean vaccinated;

    public Pet(int id, int age, int weight, String color, String name, boolean isVaccinated) {
        super(id, age, weight, color);
        this.name = name;
        this.vaccinated = isVaccinated;
    }

    @Override
    public String say() {
        return super.say() + "my name is " + name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", isVaccinated=" + vaccinated +
                ", id=" + this.getId() +
                ", age=" + this.getAge() +
                ", weight=" + this.getWeight() +
                ", color='" + this.getColor() + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
}
