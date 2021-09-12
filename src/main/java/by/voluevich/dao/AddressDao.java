package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.entity.User;

import java.util.List;

public interface AddressDao {

    boolean saveAddress(Address address);

    List<Address> getAllByUser(User user);

    boolean remove(int id, User user);

    boolean update(Address address);

    boolean isExist(Address address);
}
