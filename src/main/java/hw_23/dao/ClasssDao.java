package hw_23.dao;

import hw_23.models.Classs;
import hw_23.models.Student;
import hw_23.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClasssDao {
    public void save(Classs classs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(classs);
        tx1.commit();
        session.close();
    }

    public void update(Classs classs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(classs);
        tx1.commit();
        session.close();
    }

    public void delete(Classs classs) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(classs);
        tx1.commit();
        session.close();
    }

    public List<Classs> findAll() {
        List<Classs> classses = (List<Classs>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Classs").list();
        return classses;
    }
}
