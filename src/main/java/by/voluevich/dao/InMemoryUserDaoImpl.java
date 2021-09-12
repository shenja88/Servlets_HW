package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDaoImpl implements UserDao {
    private static final List<User> USERS = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return USERS;
    }

    @Override
    public boolean updateName(User user, String newName) {
        if(isExist(user) && !user.getName().equals(newName)){
            user.setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(User user, String oldPass, String newPass) {
        if(isExist(user) && !oldPass.equals(newPass) && oldPass.equals(user.getPassword())) {
            user.setPassword(newPass);
            return true;
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        return USERS.stream().filter(x -> x.getLogin().equals(login)).findFirst().get();
    }

    @Override
    public boolean save(User user) {
        if (!isExist(user)) {
            USERS.add(new User(USERS.size() + 1, user.getName(), user.getLogin(), user.getPassword()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExist(User user) {
        return USERS.contains(user);
    }
}
