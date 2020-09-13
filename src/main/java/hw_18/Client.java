package hw_18;

public class Client implements Runnable {
    private String name;
    private String action;
    private Atm atm;
    private boolean isPutting;
    private int amount;
    private static Boolean isFin = false;
    public Client(Atm atm, String name, boolean isPutting, int amount) {
        this.atm = atm;
        this.name = name;
        this.isPutting = isPutting;
        this.amount = amount;
        if(isPutting){
            action = "Пополнение";
        } else {
            action = "Снятие";
        }
    }
    public void run() {
        Thread.currentThread().setName(name);
        do{
            if(((atm.getBank()-amount)<0 && isPutting == false)||isFin){
                isFin = true;
                System.out.println("Остановлен поток " + Thread.currentThread().getName());
                Thread.currentThread().interrupt();
            }
            if (!Thread.interrupted()) {
                if (isPutting) {
                    atm.putMoney(amount);
                } else {
                    atm.withdrawMoney(amount);
                }
            } else {
                return;
            }
            System.out.println("Поток " + name + " " + action + " Сумма=> " + atm.getBank());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }while (true);
    }
}
