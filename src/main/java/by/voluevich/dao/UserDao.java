package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.List;

public interface UserDao {

    boolean save(User user);

    boolean isExist(User user);

    List<User> getAll();

    boolean editInfo(User user, String oldPass, String newPass, String newName);
}
