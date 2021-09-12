package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateMathOperationDaoIml implements MathOperationDao {

    @Override
    public List<MathOperation> getAll() {
        Transaction t = null;
        List<MathOperation> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            t = session.beginTransaction();
            list = session.createQuery("FROM MathOperation", MathOperation.class).getResultList();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(MathOperation mathOperation) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            t = session.beginTransaction();
            session.save(mathOperation);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<MathOperation> getByType(String operation, User user) {
        Transaction t = null;
        List<MathOperation> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Query<MathOperation> query = session.createQuery("FROM MathOperation where user.id = :user and typeOp = :operation", MathOperation.class);
            query.setParameter("operation", operation);
            query.setParameter("user", user.getId());
            list = query.getResultList();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<MathOperation> getBySession(User user) {
        Transaction t = null;
        List<MathOperation> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Query<MathOperation> query = session.createQuery("FROM MathOperation where user.id = :user", MathOperation.class);
            query.setParameter("user", user.getId());
            list = query.getResultList();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }
}
