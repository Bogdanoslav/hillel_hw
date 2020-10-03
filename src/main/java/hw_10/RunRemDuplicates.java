package hw_10;
import java.util.*;

public class RunRemDuplicates {
    public static void main(String[] args) {
        /*
                ==================
                удаление дубликатов
                ==================
         */
        Collection<String> test = new LinkedList();
        test.add("A");
        test.add("A");
        test.add("A");
        test.add("A");
        System.out.println(test);
        test = removeDuplicates(test);
        System.out.println(test);

    }
    public static <T> Collection<T> removeDuplicates(Collection<T> collection){
        Set<String> set=new LinkedHashSet(collection);
        Collection col = new ArrayList(set);
        return col;
    }

}
