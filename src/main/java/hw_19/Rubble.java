package hw_19;

public class Rubble {
    public static volatile int kg;
    private int max;

    public Rubble(int kg, int max)
    {
        this.max = max;
        this.kg = kg;
    }

    public synchronized void Consume(int amount){
        setKg(kg-amount);
        System.out.println("Использовано " + amount  + " кг" +". Осталось килограммов щебня " + kg);
    }
    public synchronized void Generate(int amount){
        setKg(kg+amount);
        System.out.println("Сгенерировано " + amount  + " кг" +". Осталось килограммов щебня " + kg);
    }

    public int getKg()
    {
        return kg;
    }

    public void setKg(int kg)
    {
        this.kg = kg;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
