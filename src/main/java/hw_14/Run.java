package hw_14;

import org.w3c.dom.ls.LSOutput;

public class Run {
    public static void main(String[] args) {
        //SORT
        Integer[] arr = {23, 0, 4, -2, -4, -1, 2 };
        Sort s = new Sort();

        System.out.println("Сортировка вставками");
        Integer[] ins_sorted = s.InsertionSort(arr);
        for(int i = 0; i < ins_sorted.length; i++){
            System.out.print(ins_sorted[i] + " ");
        }

        System.out.println();

        System.out.println("\nСортировка пузырьком");
        arr = s.BubbleSort(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        //SEARCH
        Search search = new Search();
        System.out.println("\nИнтерполяционный поиск");
        int i = search.InterpolSearch(arr, -1);
        System.out.println("index: " + i + " value: " + arr[i]);
        i = search.InterpolSearch(arr, -100);
        System.out.println("index: " + i);

        System.out.println();

        System.out.println("Бинарный поиск1");
        i = search.BinarySearch(arr, -1);
        System.out.println("index: " + i + " value: " + arr[i]);
        i = search.BinarySearch(arr, -100);
        System.out.println("index: " + i);

        System.out.println();

        System.out.println("Бинарный поиск2");
        i = search.BinarySearch2(arr, -1);
        System.out.println("index: " + i + " value: " + arr[i]);
        i = search.BinarySearch2(arr, -100);
        System.out.println("index: " + i);


    }
}
