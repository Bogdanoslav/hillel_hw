package hw_14;

public class Sort {
    public Integer[] BubbleSort(Integer[] arr){
        for(int i = arr.length-1; i >=0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    public Integer[] InsertionSort(Integer[] arr){
        int out;
        int in;
        for(out = 1; out < arr.length; out++ ){
            int temp = arr[out];
            in = out;
            while(in > 0 && arr[in-1]>=temp){
                arr[in] = arr[in-1];
                in--;
            }
            arr[in] = temp;
        }
        return arr;
    }
}
