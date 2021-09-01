package by.voluevich.service;

import by.voluevich.dao.UserDao;
import by.voluevich.entity.User;

public class EditPassword {
    private UserDao userDao;

    public EditPassword(UserDao userDao) {
        this.userDao = userDao;
    }

    public EditPassword() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public boolean editPassword(User user, String oldPass, String newPass) {
        return userDao.updatePassword(user, oldPass, newPass);
    }
}

