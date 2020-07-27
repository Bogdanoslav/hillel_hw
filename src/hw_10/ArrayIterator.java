package hw_10;

import java.lang.reflect.Type;

public class ArrayIterator implements IArrayIterator{
    private int i = 0;
    private Object[] array;

    public ArrayIterator(Object[] array){
        this.array = array;
    }
    @Override
    public boolean hasNext() {
        return (i<array.length)? true : false;
    }

    @Override
    public Object next() {
        return array[i++];
    }
}
