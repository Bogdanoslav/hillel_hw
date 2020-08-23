package hw_9_Double_Linked_List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MyDoubleLinkedListExceptionsTest {
    MyDoubleLinkedList l_filled;
    MyDoubleLinkedList l_empty;
    String[] arr;
    Collection<String> col;
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp(){
        arr = new String[]{"A", "B", "C"};
        col = new ArrayList<>();
        col.add("D");
        col.add("E");
        l_filled = new MyDoubleLinkedList();
        l_empty = new MyDoubleLinkedList();
        l_filled.addAll(arr);
    }
    @Test
    public void testGet() {
        exception.expect(IndexOutOfBoundsException.class);
        l_filled.get(22);
    }
}
