package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public boolean addUser(User user) {
        if (!isExistUser(user)) {
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistUser(User user) {
        return users.contains(user);
    }
}
