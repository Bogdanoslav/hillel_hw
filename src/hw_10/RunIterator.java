package hw_10;

public class RunIterator {
    public static void main(String[] args) {
          /*
                ==================
                Итератор
                ==================
         */
        Integer[] intArray = {1,2,3,4,5,6};

        ArrayIterator iterator1 = new ArrayIterator(intArray);
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        String[] stringArray = {"A","B","C","D","A"};
        ArrayIterator iterator2 = new ArrayIterator(stringArray);
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }
}
