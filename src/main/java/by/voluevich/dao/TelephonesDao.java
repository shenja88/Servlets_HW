package by.voluevich.dao;

import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;

import java.util.List;

public interface TelephonesDao {

    boolean saveTelephone(Telephone telephone);

    List<Telephone> getAllByUser(User user);

    boolean remove(int id, User user);

    boolean update(Telephone telephone);

    boolean isExist(Telephone telephone);

}
