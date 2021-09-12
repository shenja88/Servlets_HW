package by.voluevich.service;

import by.voluevich.dao.AddressDao;
import by.voluevich.entity.Address;
import by.voluevich.entity.User;

import java.util.List;

public class UserAddresses {
    private AddressDao addressDao;

    public UserAddresses(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public UserAddresses() {
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public List<Address> getByUser(User user){
        return addressDao.getAllByUser(user);
    }

    public boolean save(Address address){
        return addressDao.saveAddress(address);
    }

    public boolean update(Address address){
        return addressDao.update(address);
    }

    public boolean remove(int id, User user){
        return addressDao.remove(id, user);
    }
}
