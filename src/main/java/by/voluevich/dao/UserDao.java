package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean save(User user);

    boolean isExist(User user);

    List<User> getAll();

    boolean updateName(User user, String newName);

    boolean updatePassword(User user, String oldPass, String newPass);

    User getUserByLogin(String login);
}
