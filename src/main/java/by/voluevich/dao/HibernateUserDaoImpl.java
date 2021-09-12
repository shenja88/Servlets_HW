package by.voluevich.dao;

import by.voluevich.entity.User;
import by.voluevich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateUserDaoImpl implements UserDao {

    @Override
    public boolean save(User user) {
        Transaction t = null;
        boolean flag = false;
        if (!isExist(user)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                session.save(user);
                t.commit();
                flag = true;
            } catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean isExist(User user) {
        Transaction t = null;
        boolean flag = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Query<User> query = session.createQuery("from User where login= :login", User.class);
            query.setParameter("login", user.getLogin());
            Optional<User> userForCheck = query.uniqueResultOptional();
            if (userForCheck.isPresent()) {
                if (user.equals(userForCheck.get())) {
                    t.commit();
                    flag = true;
                }
            }
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            list = session.createQuery("FROM User", User.class).getResultList();
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
    public boolean updateName(User user, String newName) {
        Transaction t = null;
        boolean flag = false;
        if (!user.getName().equals(newName)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                User user1 = session.get(User.class, user.getId());
                user1.setName(newName);
                session.update(user1);
                t.commit();
                flag = true;
            } catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean updatePassword(User user, String oldPass, String newPass) {
        Transaction t = null;
        boolean flag = false;
        if (isExist(user) && user.getPassword().equals(oldPass) && !oldPass.equals(newPass)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                User user1 = session.get(User.class, user.getId());
                user1.setPassword(newPass);
                session.update(user1);
                t.commit();
                flag = true;
            } catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Query<User> query = session.createQuery("from User where login= :login", User.class);
            query.setParameter("login", login);
            user = query.getSingleResult();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
}
