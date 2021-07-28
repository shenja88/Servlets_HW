package by.voluevich.dao;

import by.voluevich.entity.User;

public interface UserDao {
    boolean addUser(User user);

    boolean isExistUser(User user);
}
