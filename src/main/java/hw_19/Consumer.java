package hw_19;

import static hw_19.Rubble.kg;

public class Consumer implements Runnable {
    int amount;
    Rubble rubble;
    public Consumer(Rubble rubble,int amount) {
        this.amount = amount;
        this.rubble = rubble;
    }

    @Override
    public void run() {
        while (true) {
            if((rubble.getKg()-amount)<=0){
                try {
                    System.out.println("Поток поглащатель приостановлен");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            }
            rubble.Consume(amount);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
