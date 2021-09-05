package by.voluevich.dao;

import by.voluevich.entity.User;
import by.voluevich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class HibernateUserDaoImpl implements UserDao {
    private Session session;

    @Override
    public boolean save(User user) {
        Transaction t = null;
        boolean flag = false;
        if (!isExist(user)){
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                t = session.getTransaction();
                t.begin();
                session.save(user);
                t.commit();
                flag = true;
            } catch (HibernateException e) {
                if(t != null){
                    t.rollback();
                }
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public boolean isExist(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("from User where login= :login", User.class);
        query.setParameter("login", user.getLogin());
        Optional<User> userForCheck = query.uniqueResultOptional();
        if (userForCheck.isPresent()){
            if(user.equals(userForCheck.get())){
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }

    @Override
    public List<User> getAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> list = session.createQuery("FROM User", User.class).getResultList();
        session.close();
        return list;
    }

    @Override
    public boolean updateName(User user, String newName) {
        Transaction t = null;
        boolean flag = false;
        if (!user.getName().equals(newName)) {
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                t = session.getTransaction();
                t.begin();
                User user1 = session.get(User.class, user.getId());
                user1.setName(newName);
                session.update(user1);
                t.commit();
                flag = true;
            } catch (HibernateException e) {
                if(t != null){
                    t.rollback();
                }
                e.printStackTrace();
            }finally {
                session.close();
            }

        }
        return flag;
    }

    @Override
    public boolean updatePassword(User user, String oldPass, String newPass) {
        Transaction t = null;
        boolean flag = false;
        if (isExist(user) && user.getPassword().equals(oldPass) && !oldPass.equals(newPass)) {
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                t = session.getTransaction();
                t.begin();
                User user1 = session.get(User.class, user.getId());
                user1.setPassword(newPass);
                session.update(user1);
                t.commit();
                flag = true;
            } catch (HibernateException e) {
                if(t != null){
                    t.rollback();
                }
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
        return flag;
    }
}
