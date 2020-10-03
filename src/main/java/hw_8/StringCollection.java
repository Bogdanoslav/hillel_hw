package hw_8;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class StringCollection  implements IStringCollection {
    public int Count = 0;
    public String[] str_arr;
    public StringCollection() {
        this.str_arr = new String[10];
    }

    @Override
    public boolean add(Object o) {
        if(Count< str_arr.length){
            str_arr[Count] = (String)o;
        }
        else{
            String[] new_arr = new String[Count+10];
            for(int i = 0; i < str_arr.length; i++){
                new_arr[i] = str_arr[i];
            }
            new_arr[Count] = (String)o;
            str_arr = new_arr;
        }
        Count++;
        return true;
    }

    @Override
    public boolean add(int index, Object o) {
        String[] new_arr = new String[str_arr.length];
        if(index>=str_arr.length || Count==str_arr.length-1){
            new_arr = new String[str_arr.length+10];
        }
        for(int i = 0; i <= Count; i++){
            if(i<index){
                new_arr[i] = str_arr[i];
            }
            else{
                new_arr[i+1] = str_arr[i];
            }
        }
        new_arr[index] = (String)o;
        str_arr = new_arr;
        if (index > Count) {
            Count = index+1;
        } else {
            Count++;
        }
        return true;
    }

    @Override
    public boolean delete(Object o) {
        String[] new_arr = new String[str_arr.length];
        if((Count-1)%10==0 && Count > 10){new_arr = new String[str_arr.length-10];}
        int index = -1;
        boolean isFound = false;
        for(int i = 0; i < str_arr.length; i++){
            if(str_arr[i]==(String)o){
                index = i;
                isFound = true;
            }
        }
        if(isFound){
            for(int j = 0; j < new_arr.length-1; j++){
                if(j < index){
                    new_arr[j] = str_arr[j];
                }
                else{
                    new_arr[j] = str_arr[j+1];
                }
            }
            str_arr = new_arr;
            Count--;
            return true;
        }
        return false;
    }

    @Override
    public Object get(int index) {
        return str_arr[index];
    }

    @Override
    public boolean contain(Object o) {
        for(int i = 0; i < str_arr.length; i++){
            if(str_arr[i] == (String)o){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Collection str) {
        if (this == str) return true;
        if (str == null || getClass() != str.getClass()) return false;
        StringCollection that = (StringCollection) str;
        return Count == that.Count &&
                Arrays.equals(str_arr, that.str_arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(Count);
        result = 31 * result + Arrays.hashCode(str_arr);
        return result;
    }

    @Override
    public boolean clear() {
        str_arr = new String[10];
        Count = 0;
        return false;
    }

    @Override
    public int size() {
        return Count;
    }

    @Override
    public String toString() {
        return "StringCollection{" +
                "Count=" + Count +
                ", str_arr=" + Arrays.toString(str_arr) +
                '}';
    }
}
