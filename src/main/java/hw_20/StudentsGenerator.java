package hw_20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class StudentsGenerator {
    String[] firstNames;
    String[] lastNames;
    String[] patronymic;
    Class[] classes;


    public Student[] generate(int amount){
        String name = "";
        Integer year;
        int groupID;
        Student[] students = new Student[amount];
        for(int i = 1; i <= amount; i++){
            name+=firstNames[(int)(Math.random()*firstNames.length)] + " ";
            name+=lastNames[(int)(Math.random()*lastNames.length)] + " ";
            name+=patronymic[(int)(Math.random()*patronymic.length)];
            year = (int)(Math.random()*3 + 2018);
            groupID = classes[(int)(Math.random()*classes.length)].getID();
            students[i-1] = new Student(i, name, groupID, year);
            name="";
        }
        return students;
    }
}

