package by.voluevich.service;

import by.voluevich.dao.TelephonesDao;
import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;

import java.util.List;

public class UserTelephones {
    private TelephonesDao telephonesDao;

    public UserTelephones(TelephonesDao telephonesDao) {
        this.telephonesDao = telephonesDao;
    }

    public UserTelephones() {
    }

    public TelephonesDao getTelephonesDao() {
        return telephonesDao;
    }

    public List<Telephone> getByUser(User user){
        return telephonesDao.getAllByUser(user);
    }

    boolean save(Telephone telephone){
        return telephonesDao.saveTelephone(telephone);
    }

    boolean update(Telephone telephone){
        return telephonesDao.update(telephone);
    }

    boolean remove(int id, User user){
        return telephonesDao.remove(id, user);
    }
}
