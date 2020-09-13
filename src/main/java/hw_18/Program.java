package hw_18;

import java.util.ArrayList;
import java.util.List;

public class Program	//Класс с методом main()
{
    public static void main(String[] args) throws InterruptedException {
        Atm atm = new Atm(1000);
        Client c1 = new Client(atm, "C1", false, 70);
        Client c2 = new Client(atm, "C2", true, 20);
        Client c3 = new Client(atm, "C3", false, 7);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        List<Thread> clients = new ArrayList<>();
        clients.add(t1);
        clients.add(t2);
        clients.add(t3);

        for(int i = 0; i < clients.size(); i++){
            clients.get(i).start();
            clients.get(i).sleep(500);
        }
        for(int i = 0; i < clients.size(); i++){
            clients.get(i).join();
        }


        System.out.println("Cъем больше не возможен. Прога завершилась. Банк " + atm.getBank());
    }
}