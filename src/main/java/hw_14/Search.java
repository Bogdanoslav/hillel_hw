package hw_14;

public class Search {
    public static Integer BinarySearch(Integer[] arr, int key){
        int mid;
        int left = -1;
        int right = arr.length;
        do{
            mid = (left + right)/2;
            if(arr[mid]  == key){
                return mid;
            }
            if(key<arr[mid]){
                right = mid;
            } else{
                left = mid;
            }
        }while(right>left+1);
        return -1;
    }
    public static Integer BinarySearch2(Integer[] arr, int key){
        int mid = 0;
        int left = 0;
        int right = arr.length-1;
        while(left<right-1){
            mid = (left + right)/2;
            if(arr[mid]<key){
                left = mid;
            }
            else{
                right = mid;
            }
        }
        if(arr[mid]!=key)
            return -1;
        return mid;
    }
    public Integer InterpolSearch(Integer[] arr, int key){
        int l = 0;
        int r = arr.length - 1;
        while(arr[l] < key && arr[r] > key){
            int ind = l + (key - arr[l])*(l-r)/(arr[l]-arr[r]);
            if(arr[ind]>key){
               r = ind-1;
            }
            else if(arr[ind]<key){
                l = ind+1;
            }
            else {
                return ind;
            }
        }
        if(arr[l] == key){
            return l;
        }
        if (arr[r] == key){
            return r;
        }
        return -1;
    }
}
