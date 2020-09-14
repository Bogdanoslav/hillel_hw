package hw_19;

import static hw_19.Rubble.kg;

public class Generator implements Runnable {
    int amount;
    Rubble rubble;
    int sleepTime = 1000;
    public Generator(Rubble rubble,int amount) {
        this.amount = amount;
        this.rubble = rubble;
    }


    @Override
    public void run() {
        while (true) {
            if((rubble.getKg()+amount)>=rubble.getMax()){
                try {
                    System.out.println("Поток генератор приостановлен");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            }
            rubble.Generate(amount);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
