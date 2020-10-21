package hw_26;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Student student1 = new Student("Vasya");
        Student student2 = new Student("Luba");
        Student student3 = new Student("Mike");
        Student student4 = new Student("Kolya");

        MyCache myCache = new MyCache(2, 3);

        myCache.put(student1);
        myCache.put(student2);
        myCache.put(student3);

        //Первый и второй студент больше не менее используемые -> улетит Третий студент если превысить размер
        System.out.println(myCache.get(student1));
        System.out.println(myCache.get(student2));

        myCache.put(student4);
        System.out.println(myCache.printValues());

        System.out.println("Main is sleeping");
        Thread.sleep(3000);
        System.out.println("Main woke up");

        //За это время кэш уже очистился
        System.out.println(myCache.get(student1));
    }
}
