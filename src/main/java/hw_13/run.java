package hw_13;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class run {
    public static void main(String[] args) {
        /*
        =======================
                (1)
        =======================
         */
        System.out.println("        (1)");
        Collection<Integer> col = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            col.add((int)(Math.random()*10));
        }
        System.out.println(col);
        //option 1
        Long count = col.stream().count();
        col.stream()
                .reduce((acc, x) -> acc + x)
                .ifPresent(x -> System.out.println("Option 2: Avg = " + (double)x/count));

        //option 2
        col.stream() //
                .mapToInt(i -> i) //
                .average() //
                .ifPresent(avg -> System.out.println("Option 2: Avg = " + avg));
         /*
        =======================
                (2)
        =======================
         */
        System.out.println("        (2)");
        Collection<String> strCol = new ArrayList<>();
        strCol.add("one");
        strCol.add("two");
        strCol.add("three");
        strCol.add("four");
        strCol.add("five");
        List<Pair> pairList = strCol.stream().map(s -> new Pair(s,s.toUpperCase())).collect(Collectors.toList());
        System.out.println(pairList);

         /*
        =======================
                (3)
        =======================
         */
        System.out.println("        (3)");
        Predicate<String> isLowerCaseLength4 = (s) -> s.length()==4 && s.toLowerCase()==s;

        strCol = new ArrayList<>();
        strCol.add("java");
        strCol.add("noDe");
        strCol.add("Sharp");
        strCol.add("SWIFt");
        strCol.add("ruby");
        strCol.add("go");
        strCol.add("perL");
        System.out.println(strCol);
        strCol = strCol.stream().filter(isLowerCaseLength4).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(strCol);
    }
}
