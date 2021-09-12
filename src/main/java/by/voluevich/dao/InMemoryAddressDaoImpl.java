package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryAddressDaoImpl implements AddressDao {
    private static final List<Address> ADDRESSES = new ArrayList<>();

    @Override
    public boolean saveAddress(Address address) {
        if (!isExist(address) && isExistByAddressInfo(address)) {
            ADDRESSES.add(new Address(
                    ADDRESSES.size() + 1,
                    address.getCity(),
                    address.getStreet(),
                    address.getNumHome(),
                    address.getUser()));
            return true;
        }
        return false;
    }

    @Override
    public List<Address> getAllByUser(User user) {
        return ADDRESSES.stream()
                .filter(x -> x.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public boolean remove(int id, User user) {
        Optional<Address> addr = getById(id, user);
        if (addr.isPresent()) {
            Address address = addr.get();
            if (!address.isPrimary()) {
                ADDRESSES.remove(addr.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Address address) {
        if (isExist(address)) {
            Address addressForUpdate = ADDRESSES.get(ADDRESSES.indexOf(address));
            addressForUpdate.setCity(address.getCity());
            addressForUpdate.setStreet(address.getStreet());
            addressForUpdate.setNumHome(address.getNumHome());
            return true;
        }
        return false;
    }

    @Override
    public boolean isExist(Address address) {
        return ADDRESSES.contains(address);
    }

    private Optional<Address> getById(int id, User user) {
        return ADDRESSES.stream().filter(x -> x.getId() == id && x.getUser().equals(user)).findFirst();
    }

    private boolean isExistByAddressInfo(Address address) {
        boolean check = true;
        for (Address a : ADDRESSES) {
            if (a.getCity().equals(address.getCity())
                    && a.getStreet().equals(address.getStreet()) && a.getNumHome() == address.getNumHome()) {
                check = false;
            }
        }
        return check;
    }
}

