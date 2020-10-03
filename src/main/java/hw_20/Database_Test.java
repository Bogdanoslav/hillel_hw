package hw_20;

import javax.xml.transform.Result;
import java.net.ConnectException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Database_Test {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)  {

        final String USERNAME = "root";
        final String PASSWORD = "password";

        PreparedStatement ps;
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

//        Генерация случайных студентов
        StudentsGenerator sg = new StudentsGenerator(firstNames, lastNames, patronymic, classes);
        students = sg.generate(5);

//        Вывод студентов и групп
        System.out.println("        -Студенты-");
        for(Student st : students){
            System.out.println(st);
        }
        System.out.println("        -Группы-");
        for(Class cl : classes){
            System.out.println(cl);
        }



        String connectionUrl = "jdbc:mysql://localhost:3306/university?serverTimezone=UTC";
        String scanVar = "";
        try (Connection conn = DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD))
        {
            conn.setAutoCommit(false);
            //Заполнение таблицы Группы
            insertClasses(classes, conn);
            conn.commit();
            //Заполнение таблицы студенты
            insertStudents(students, conn);
            conn.commit();
            int answ = -1;
            while(answ!=0){
                System.out.println("База данных Университета");
                System.out.println( "   1. Добавить студента\n" +
                                    "   2. Удалить студента\n" +
                                    "   3. Найти студента по имени\n" +
                                    "   4. Найти студента по ID\n" +
                                    "   0. Выйти");
                System.out.print("Выберите пункт меню: ");
                while(!scanner.hasNextInt()) {
                    System.out.print("Пожалуйста, введите число чтобы выбрать пункт меню: ");
                    scanner.next();
                }
                answ = scanner.nextInt();
                switch (answ){
                    case 1:
                        String name = "";
                        int cl = -1;
                        int admYear = -1;
                        scanner.nextLine();
                        System.out.println("Добавление нового студента...");
                        System.out.print("ФИО: ");
                        name = scanner.nextLine();
                        System.out.print("Группа №: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Группа №: ");
                            scanner.next();
                        }
                        cl = scanner.nextInt();
                        System.out.print("Год поступления: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Год поступления: ");
                            scanner.next();
                        }
                        admYear = scanner.nextInt();
                        insertStudent(name, cl, admYear, conn);
                        conn.commit();
                        break;
                    case 2:
                        System.out.println("Удаление студента...");
                        System.out.print("Id: ");
                        while (!scanner.hasNextInt()){
                            System.out.print("Id: ");
                            scanner.next();
                        }
                        int n = scanner.nextInt();
                        System.out.println("Будет удален студент ");
                        getStudent(n, conn);
                        deleteStudent(n, conn);
                        conn.commit();
                        break;
                    case 3:
                        System.out.println("Поиск студента по id...");
                        System.out.print("Id: ");
                        while (!scanner.hasNextInt()){
                            System.out.print("Id: ");
                            scanner.next();
                        }
                        int id = scanner.nextInt();
                        System.out.println(getStudent(id, conn));
                        break;
                    case 4:
                        scanner.nextLine();
                        System.out.println("Поиск студента по имени");
                        System.out.print("Имя: ");
                        name = scanner.nextLine();
                        System.out.println(getStudent(name, conn));
                        break;
                    case 0:
                        System.out.println("Программа завершена");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неизвестный ответ.");
                        break;
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }

    }
    static void deleteStudent(int n, Connection conn){
        String studentDelete = "DELETE FROM students Where ID = " + n + ";";
        try {
            PreparedStatement ps = conn.prepareStatement(studentDelete);
            ps.execute();
            ps.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    static void insertStudent(String name, int cl, int admYear, Connection conn){
        String studentInsert = "INSERT INTO students (Name, ClassId, AdmissionYear) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(studentInsert);
            ps.setString(1, name);
            ps.setInt(2, cl);
            ps.setInt(3, admYear);
            ps.execute();
            ps.close();
            System.out.print("Был добавлен студент =>");
            System.out.println( "\n   ФИО: " + name +
                                "\n   Группа: " + cl +
                                "\n   Гoд поступления: " + admYear);
        } catch (SQLIntegrityConstraintViolationException e){
            System.err.println("Такой группы нет. Студент не был добавлен.");
        } catch (SQLException e) {
            System.err.println("Не удалось создать запрос в БД.");
        }
    }
    static void insertStudents(Student[] students, Connection conn){
        String studentInsert = "INSERT INTO students (Name, ClassId, AdmissionYear) VALUES (?, ?, ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(studentInsert);
            prepareStudents(students, ps);
            ps.executeBatch();
            ps.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }
    static void insertClasses(Class[] classes, Connection conn){
        String classesInsert = "INSERT INTO classes (Name) VALUES (?);";
        try {
            PreparedStatement ps = conn.prepareStatement(classesInsert);
            for (Class aClass : classes) {
                ps.setString(1, aClass.getName());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    static void prepareStudents(Student[] students, PreparedStatement ps) {
        try {
            for (Student value : students) {
                ps.setString(1, value.getName());
                ps.setInt(2, value.getClassID());
                ps.setInt(3, value.getYear());
                ps.addBatch();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
    static Student getStudent(int id, Connection conn){
        String selectById = "SELECT * FROM students WHERE ID = " + id + ";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectById);
            while(rs.next()){
                id  = rs.getInt("ID");
                String name  = rs.getString("Name");
                int cl  = rs.getInt("ClassId");
                int year = rs.getInt("AdmissionYear");
                Student student = new Student(id, name, cl, year);
                return student;
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    static Student getStudent(String name, Connection conn){

        String selectById = "SELECT * FROM students WHERE Name LIKE '" + name + " %" + "'" +";";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectById);
            while(rs.next()){
                int id  = rs.getInt("ID");
                name  = rs.getString("Name");
                int cl  = rs.getInt("ClassId");
                int year = rs.getInt("AdmissionYear");
                Student student = new Student(id, name, cl, year);
                return student;
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

}
