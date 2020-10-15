package hw_23.dao;

import hw_23.models.Classs;
import hw_23.models.Mark;
import hw_23.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MarkDao {
    public void save(Mark mark) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(mark);
        tx1.commit();
        session.close();
    }

    public void update(Mark mark) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(mark);
        tx1.commit();
        session.close();
    }

    public void delete(Mark mark) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(mark);
        tx1.commit();
        session.close();
    }

    public List<Mark> findAll() {
        List<Mark> classses = (List<Mark>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Mark").list();
        return classses;
    }
}
