package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class HibernateMathOperationDaoIml implements MathOperationDao{
    private Session session;

    @Override
    public List<MathOperation> getAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<MathOperation> list = session.createQuery("FROM MathOperation", MathOperation.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public void save(MathOperation mathOperation) {
        Transaction t = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            t= session.getTransaction();
            t.begin();
            session.save(mathOperation);
            t.commit();
        } catch (HibernateException e) {
            if(t != null){
                t.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<MathOperation> getByType(String operation, User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query<MathOperation> query = session.createQuery("FROM MathOperation where user.id = :user and typeOp = :operation", MathOperation.class);
        query.setParameter("operation", operation);
        query.setParameter("user", user.getId());
        List<MathOperation> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public List<MathOperation> getBySession(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query<MathOperation> query = session.createQuery("FROM MathOperation where user.id = :user", MathOperation.class);
        query.setParameter("user", user.getId());
        List<MathOperation> list = query.getResultList();
        session.close();
        return list;
    }
}
