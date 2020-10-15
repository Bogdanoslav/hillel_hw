package hw_23.dao;

import hw_23.models.Student;
import hw_23.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao {
    public Student findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Student.class, id);
    }

    public List<Student> findByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Student S WHERE S.name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        List<Student> students = query.list();
        return students;
    }

    public void save(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(student);
        tx1.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(student);
        tx1.commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(student);
        tx1.commit();
        session.close();
    }

    public List<Student> findAll() {
        List<Student> students = (List<Student>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Student").list();
        return students;
    }
}
