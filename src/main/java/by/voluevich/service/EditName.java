package by.voluevich.service;

import by.voluevich.dao.UserDao;
import by.voluevich.entity.User;

public class EditName {
    private UserDao userDao;

    public EditName(UserDao userDao) {
        this.userDao = userDao;
    }

    public EditName() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public boolean editName(User user, String newName) {
        return userDao.updateName(user, newName);
    }

}
