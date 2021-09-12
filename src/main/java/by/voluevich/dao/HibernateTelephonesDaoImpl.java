package by.voluevich.dao;

import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;
import by.voluevich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateTelephonesDaoImpl implements TelephonesDao {

    @Override
    public boolean saveTelephone(Telephone telephone) {
        Transaction t = null;
        boolean flag = false;
        if (!isExist(telephone)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                session.save(telephone);
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
    public List<Telephone> getAllByUser(User user) {
        Transaction t = null;
        List<Telephone> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            list = session.createQuery("FROM Telephone WHERE user.id = :id", Telephone.class)
                    .setParameter("id", user.getId())
                    .getResultList();
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
    public boolean remove(int id, User user) {
        Transaction t = null;
        boolean flag = false;
        if (isExistById(id, user)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                Telephone telephoneForRemove = session.createQuery("from Telephone where id = :id and user.id = :user_id", Telephone.class)
                        .setParameter("id", id)
                        .setParameter("user_id", user.getId())
                        .uniqueResult();
                if (!telephoneForRemove.isPrimary()) {
                    session.delete(telephoneForRemove);
                    flag = true;
                }
                t.commit();
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
    public boolean update(Telephone telephone) {
        Transaction t = null;
        boolean flag = false;
        if (isExistById(telephone.getId(), telephone.getUser())) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                Telephone telForUpdate = session.get(Telephone.class, telephone.getId());
                telForUpdate.setNumber(telephone.getNumber());
                session.update(telForUpdate);
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
    public boolean isExist(Telephone telephone) {
        Transaction t = null;
        boolean flag = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Optional<Telephone> optionalTelephone = session.createQuery("from Telephone where number = :number and user.id = :user_id", Telephone.class)
                    .setParameter("number", telephone.getNumber())
                    .setParameter("user_id", telephone.getUser().getId())
                    .uniqueResultOptional();
            flag = optionalTelephone.isPresent();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return flag;
    }

    private boolean isExistById(int id, User user) {
        Transaction t = null;
        boolean flag = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Optional<Telephone> optionalTelephone = session.createQuery("from Telephone WHERE id = :id and user.id = :user_id", Telephone.class)
                    .setParameter("id", id)
                    .setParameter("user_id", user.getId())
                    .uniqueResultOptional();
            flag = optionalTelephone.isPresent();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return flag;
    }
}
