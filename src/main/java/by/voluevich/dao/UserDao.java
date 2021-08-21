package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.List;

public interface UserDao {

    String addUser(User user);

    boolean isExistUser(User user);

    List<User> getUsers();

    String editUserInfo(User user, String oldPass, String newPass, String newName);
}
