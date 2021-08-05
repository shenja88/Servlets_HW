package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    boolean isExistUser(User user);

    List<User> getUsers();
}
