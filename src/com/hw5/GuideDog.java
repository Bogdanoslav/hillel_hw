package com.hw5;

public class GuideDog extends Dog{
    boolean isTrained;

    public GuideDog(int id, int age, int weight, String color, String name, boolean isVaccinated, boolean isTrained) {
        super(id, age, weight, color, name, isVaccinated);
        this.isTrained = isTrained;
    }

    @Override
    public String Say() {
        if(isTrained)
            return super.Say() + ". I can take you home";
        else{
            return super.Say();
        }
    }



    public void TakeHome() throws InterruptedException {
        String homing = "Taking home";
        for(int i = 0 ; i < 3; i++){
            homing+=".";
            System.out.println(homing);
            Thread.sleep(1000);
        }
        System.out.println("You are at home");
    }

    public boolean isTrained() {
        return isTrained;
    }

    public void setTrained(boolean trained) {
        isTrained = trained;
    }
}
