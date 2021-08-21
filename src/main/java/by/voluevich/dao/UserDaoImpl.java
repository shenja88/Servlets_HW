package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final List<User> USERS = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return USERS;
    }

    @Override
    public String editUserInfo(User user, String oldPass, String newPass, String newName) {
        if(user.getPassword().equals(oldPass)){
            user.setPassword(newPass);
            user.setName(newName);
            return "Successful!";
        } else{
            return "You entered the wrong old password!";
        }
    }

    @Override
    public String addUser(User user) {
        if (!isExistUser(user)) {
            USERS.add(user);
            return "Registration successful!";
        }
        return "Invalid registration!";
    }

    @Override
    public boolean isExistUser(User user) {
        return USERS.contains(user);
    }
}
