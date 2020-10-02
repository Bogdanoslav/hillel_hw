package hw_20;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
@Data
@AllArgsConstructor
public class Student {
    private int ID;
    private String Name;
    private int ClassID;
    private int year;
}
