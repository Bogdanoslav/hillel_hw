package hw_7;

public class MyStrCollection {
    private int Count = 0;
    public String[] str_arr;

    public MyStrCollection() {
        this.str_arr = new String[10];
    }

    public void add(String el){
        if(Count< str_arr.length){
            str_arr[Count] = el;
        }
        else{
            String[] new_arr = new String[Count+1];
            for(int i = 0; i < str_arr.length; i++){
                new_arr[i] = str_arr[i];
            }
            new_arr[Count] = el;
            str_arr = new_arr;
        }
        Count++;
    }
    public void add(String el, int index){
        if(index>str_arr.length){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }
        String[] new_arr = new String[str_arr.length+1];
        for(int i = 0; i < str_arr.length; i++){
            if(i<index){
                new_arr[i] = str_arr[i];
            }
            else{
                new_arr[i+1] = str_arr[i];
            }
        }
        new_arr[index] = el;
        str_arr = new_arr;
        Count++;
    }
    public void delete(int index){
        String[] new_arr = new String[Count-1];
        if(index>str_arr.length){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }
        for(int i = 0; i < new_arr.length; i++){
            if(i< index){
                new_arr[i] = str_arr[i];
            }
            else{
                new_arr[i] = str_arr[i+1];
            }
        }
        str_arr = new_arr;
        Count--;
    }
    public void delete(String el){
        String[] new_arr = new String[Count-1];
        int index = -1;
        boolean isFound = false;
        for(int i = 0; i < str_arr.length; i++){
            if(str_arr[i]==el){
                index = i;
                isFound = true;
            }
        }
        if(isFound){
            for(int j = 0; j < new_arr.length; j++){
                if(j < index){
                    new_arr[j] = str_arr[j];
                }
                else{
                    new_arr[j] = str_arr[j+1];
                }
            }
            str_arr = new_arr;
            Count--;
        }
        else{
            throw new IndexOutOfBoundsException("Item: " + el + " not found");
        }
        }

    }
