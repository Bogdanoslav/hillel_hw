package hw_20;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Database_Test {
    public static void main(String[] args)  {

        final String USERNAME = "root";
        final String PASSWORD = "21032002Vfhnf";
        PreparedStatement ps = null;
        Statement s = null;
        Class[] classes = new Class[4];
        Student[] students;


        String[] firstNames = {"Валерий", "Констатин", "Петр", "Семен"
                            , "Михаил", "Роман", "Хамзат"};
        String[] lastNames = {"Никонян", "Изотов", "Кускустразов", "Кофеваров"
                , "Щебнев", "Проводкин", "Мухаслонский"};
        String[] patronymic = {"Валерьевич", "Констатинович", "Петрович", "Семенович"
                , "Михаилович", "Романович", "Хамзатович"};
        String[] classesNames = {"A", "B", "C", "D"};

        for(int i = 1; i <= classes.length; i++){
            classes[i-1] = new Class(i, classesNames[i-1]);
        }

        StudentsGenerator sg = new StudentsGenerator(firstNames, lastNames, patronymic, classes);
        students = sg.generate(10);

        System.out.println("        -Студенты-");
        for(Student st : students){
            System.out.println(st);
        }
        System.out.println("        -Группы-");
        for(Class cl : classes){
            System.out.println(cl);
        }



        String connectionUrl = "jdbc:mysql://localhost:3306/university?serverTimezone=UTC";

        try (Connection conn = DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD))
        {
            //Заполнение таблицы Группы
            conn.setAutoCommit(false);
            String classesInsert = "INSERT INTO classes (Name) VALUES (?);";
            ps = conn.prepareStatement(classesInsert);
            for(int i = 0; i < classes.length; i++){
                ps.setString(1, classes[i].getName());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
          //Заполнение таблицы студенты
          String studentInsert = "INSERT INTO students (Name, ClassId, AdmissionYear) VALUES (?, ?, ?);";
            ps = conn.prepareStatement(studentInsert);
            for(int i = 0; i < students.length; i++){
                ps.setString(1, students[i].getName());
                ps.setInt(2, students[i].getClassID());
                ps.setInt(3, students[i].getYear());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();

            System.out.print("Какое количество новых студентов добавить? -> ");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            students = sg.generate(n);
            System.out.println("        -Новые студенты-");
            for(Student st : students){
                System.out.println(st);
            }
            ps = conn.prepareStatement(studentInsert);
            for(int i = 0; i < students.length; i++){
                ps.setString(1, students[i].getName());
                ps.setInt(2, students[i].getClassID());
                ps.setInt(3, students[i].getYear());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();

            System.out.println("Удалить студента. Id -> ");
            n = scanner.nextInt();
            String studentDelete = "DELETE FROM students Where ID = " + n + ";";
            ps = conn.prepareStatement(studentDelete);
            ps.execute();
            ps.close();
            conn.commit();

        }
        catch (SQLException e) {
            System.out.println(e);
        }

    }
}
