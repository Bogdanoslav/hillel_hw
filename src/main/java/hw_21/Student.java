package hw_21;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int ID;
    private String Name;
    private int ClassID;
    private int year;
}
