package hw_18;

public class Atm{
    private volatile static int bank;



    public Atm(int bank) {
        this.bank = bank;
    }

    public static synchronized void putMoney(int money){
        bank+=money;
    }
    public static synchronized void withdrawMoney(int money){
        bank-=money;
    }

    public static int getBank() {
        return bank;
    }

    public static void setBank(int bank) {
        Atm.bank = bank;
    }
}
