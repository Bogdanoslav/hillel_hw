package hw_23;

import hw_23.models.Classs;
import hw_23.services.ClasssService;
import hw_23.services.MarkService;
import hw_23.services.StudentService;
import hw_23.utils.HibernateUtil;
import hw_23.models.Mark;
import hw_23.models.Student;
import org.hibernate.Session;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        ClasssService classsService = new ClasssService();
        MarkService markService = new MarkService();

        Classs classs1 = new Classs("A");
        Classs classs2 = new Classs("B");
        classsService.saveClasss(classs1);
        classsService.saveClasss(classs2);


        Student student1 = new Student("Alstein Bertein Kazimirchenko", 1111, classs1);
        Student student2 = new Student("Vasuta Yaklukov Borisovich", 2222, classs2);
        Student student3 = new Student("A", 2222, classs2);
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.saveStudent(student3);

        Mark mark1 = new Mark(2, 12,student1);
        Mark mark2 = new Mark(2, 10,student3);
        markService.saveMark(mark1);
        markService.saveMark(mark2);

        System.out.println("Get student by id");
        System.out.println(studentService.findStudent(1));
        System.out.println("Get student by name");
        System.out.println(studentService.findStudent("A"));
        System.out.println("Get all students");
        List<Student> studentList = studentService.findAllStudents();
        for(Student s: studentList){
            System.out.println(s);
        }
//        Mark mark1 = new Mark(2, 12,student1);
//        Mark mark2 = new Mark(2, 10,student1);
//        session.save(student1);
//        session.save(student2);
//        session.save(mark1);
//        session.save(mark2);
    }
}
