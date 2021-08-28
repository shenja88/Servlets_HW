package by.voluevich.service;

import by.voluevich.dao.UserDao;
import by.voluevich.entity.User;

public class Registration {
    private UserDao userDao;

    public Registration(UserDao userDao) {
        this.userDao = userDao;
    }

    public Registration() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean save(User user) {
        return userDao.save(user);
    }
}
