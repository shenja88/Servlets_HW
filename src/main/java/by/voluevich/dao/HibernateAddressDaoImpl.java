package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.entity.User;
import by.voluevich.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateAddressDaoImpl implements AddressDao {

    @Override
    public boolean saveAddress(Address address) {
        Transaction t = null;
        boolean flag = false;
        if(!isExist(address)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                session.save(address);
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
    public List<Address> getAllByUser(User user) {
        List<Address> addressList = new ArrayList<>();
        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            addressList = session.createQuery("FROM Address WHERE user.id = :user_id", Address.class)
                    .setParameter("user_id", user.getId())
                    .list();
            t.commit();
        }catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return addressList;
    }

    @Override
    public boolean remove(int id, User user) {
        Transaction t = null;
        boolean flag = false;
        if(isExistById(id, user)) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                Address address = session.createQuery("from Address where id = :id and user.id = :user_id", Address.class)
                        .setParameter("id", id)
                        .setParameter("user_id", user.getId())
                        .uniqueResult();
                if(!address.isPrimary()) {
                    session.delete(address);
                    flag = true;
                }
                t.commit();
            }catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean update(Address address) {
        Transaction t = null;
        boolean flag = false;
        if(isExistById(address.getId(), address.getUser())){
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                t = session.beginTransaction();
                Address addressForUpdate = session.createQuery("from Address where id = :id and user.id = :user_id", Address.class)
                        .setParameter("id", address.getId())
                        .setParameter("user_id", address.getUser().getId())
                        .uniqueResult();
                addressForUpdate.setCity(address.getCity());
                addressForUpdate.setStreet(address.getStreet());
                addressForUpdate.setNumHome(address.getNumHome());
                session.update(addressForUpdate);
                t.commit();
                flag = true;
            }catch (HibernateException e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean isExist(Address address) {
        Transaction t = null;
        boolean flag = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Optional<Address> optionalAddress = session.createQuery("FROM Address where city = :city and street = :street and " +
                    " numHome = :numHome and user.id = :user_id", Address.class)
                    .setParameter("city", address.getCity())
                    .setParameter("street", address.getStreet())
                    .setParameter("numHome", address.getNumHome())
                    .setParameter("user_id", address.getUser().getId())
                    .uniqueResultOptional();
            flag = optionalAddress.isPresent();
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
            Optional<Address> optionalAddress = session.createQuery("from Address where id = :id and user.id = :user_id", Address.class)
                    .setParameter("id", id)
                    .setParameter("user_id", user.getId())
                    .uniqueResultOptional();
            flag = optionalAddress.isPresent();
            t.commit();
        }catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return flag;
    }
}
