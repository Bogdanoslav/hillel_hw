package hw_6;

public class MyStrCollection {
    private String[] str_arr;
    private int Count = 16;
    private int pointer = 0;

    public MyStrCollection(){
        str_arr = new String[Count];
    }
    public void add(int index, String toAdd){
        String[] new_arr = new String[Count+1];
        if(index<0){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index >= Count){
            Count*=2;
            new_arr = new String[Count];
            for(int i = 0; i < Count/2;i++){
                new_arr[i] = str_arr[i];
            }
            str_arr = new_arr;
        }
        str_arr[index] = toAdd;
        if(index >= pointer){
            pointer = index+1;
        }
    }
    public void add(String toAdd){
        if(pointer == Count){
            Count*=2;
            String[] new_arr = new String[Count];
            for(int i = 0; i < Count/2;i++){
                new_arr[i] = str_arr[i];
            }
            str_arr = new_arr;
        }
        str_arr[pointer] = toAdd;
        pointer++;
    }

    public void delete(int index){
        String[] new_arr = new String[Count];
        if(index<0 || index>Count-1){
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = 0; i < Count-1; i++){
            if(i>=index){
                new_arr[i] = str_arr[i+1];
            }
            else{
                new_arr[i] = str_arr[i];
            }
        }
        str_arr = new_arr;
        pointer--;
    }
    public void delete(String toDel){
        int index = 0;
        boolean isFound = false;

        for(int i = 0; i < pointer; i++){
            if(str_arr[i].equals(toDel)){
                index = i;
                isFound = true;
            }
        }
        if(isFound){
            String[] new_arr = new String[Count];
            for(int i = 0; i < Count-1; i++){
                if(i>=index){
                    new_arr[i] = str_arr[i+1];
                }
                else{
                    new_arr[i] = str_arr[i];
                }
            }
            str_arr = new_arr;
            pointer--;
        }
    }

    @Override
    public String toString() {
        String txt = "";
        for(int i = 0; i < pointer; i++){
            txt+=str_arr[i] + " ";
        }
        return txt;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
