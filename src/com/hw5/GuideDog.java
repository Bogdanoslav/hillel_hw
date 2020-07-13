package com.hw5;

public class GuideDog extends Dog implements IGuide {
    boolean trained;

    public GuideDog(int id, int age, int weight, String color, String name, boolean isVaccinated, boolean trained) {
        super(id, age, weight, color, name, isVaccinated);
        this.trained = trained;
    }

    @Override
    public String say() {
        if(trained)
            return super.say() + ". I can take you home";
        else{
            return super.say();
        }
    }



    public void takeHome() throws InterruptedException {
        String homing = "Taking home";
        for(int i = 0 ; i < 3; i++){
            homing+=".";
            System.out.println(homing);
            Thread.sleep(1000);
        }
        System.out.println("You are at home");
    }

    public boolean isTrained() {
        return trained;
    }

    public void setTrained(boolean trained) {
        this.trained = trained;
    }
}
