package by.voluevich.dao;

import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private static final List<User> USER_DAO_LIST = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        if(!isExistUser(user)){
            USER_DAO_LIST.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistUser(User user) {
        return USER_DAO_LIST.contains(user);
    }
}
