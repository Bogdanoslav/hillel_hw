package hw_23.services;

import hw_23.dao.StudentDao;
import hw_23.models.Student;

import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDao();

    public StudentService() {
    }

    public Student findStudent(int id) {
        return studentDao.findById(id);
    }

    public List<Student> findStudent(String name) {
        return studentDao.findByName(name);
    }

    public void saveStudent(Student student) {
        studentDao.save(student);
    }

    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

    public void updateStudent(Student student) {
        studentDao.update(student);
    }

    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }
}
