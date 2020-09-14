package hw_19;

public class Program {
    public static void main(String[] args) {
        Rubble rubble = new Rubble(250, 500);
        Consumer consumer = new Consumer(rubble,100);
        Generator generator = new Generator(rubble,200);

        Thread consumerThread = new Thread(consumer);
        Thread generatorThread = new Thread(generator);

        consumerThread.start();
        generatorThread.start();
    }
}
