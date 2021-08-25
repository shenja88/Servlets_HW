package by.voluevich.service.utils;

import by.voluevich.dao.UserDao;
import by.voluevich.entity.User;

import java.util.Optional;

public class LogIn {
    private UserDao userDao;

    public LogIn(UserDao userDao) {
        this.userDao = userDao;
    }

    public LogIn() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> logIn(User user){
        if(userDao.isExist(user)){
            return Optional.of(userDao.getAll().get(userDao.getAll().indexOf(user)));
        }
        return Optional.empty();
    }
}
