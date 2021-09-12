package by.voluevich.dao;

import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryTelephonesDaoImpl implements TelephonesDao {
    private static final List<Telephone> TELEPHONES = new ArrayList<>();

    @Override
    public boolean saveTelephone(Telephone telephone) {
        if (!isExist(telephone) && isExistByNumber(telephone)) {
            TELEPHONES.add(new Telephone(
                    TELEPHONES.size() + 1,
                    telephone.getNumber(),
                    telephone.getUser()));
            return true;
        }
        return false;
    }

    @Override
    public List<Telephone> getAllByUser(User user) {
        return TELEPHONES.stream()
                .filter(x -> x.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public boolean remove(int id, User user) {
        Optional<Telephone> tel = getById(id, user);
        if (tel.isPresent()) {
            Telephone telephone = tel.get();
            if (!telephone.isPrimary()) {
                TELEPHONES.remove(tel.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Telephone telephone) {
        if (isExist(telephone)) {
            Telephone telephoneForUpdate = TELEPHONES.get(TELEPHONES.indexOf(telephone));
            telephoneForUpdate.setNumber(telephone.getNumber());
            return true;
        }
        return false;
    }

    @Override
    public boolean isExist(Telephone telephone) {
        return TELEPHONES.contains(telephone);
    }

    private Optional<Telephone> getById(int id, User user) {
        return TELEPHONES.stream().filter(x -> x.getId() == id && x.getUser().equals(user)).findFirst();
    }

    private boolean isExistByNumber(Telephone telephone) {
        boolean check = true;
        for (Telephone t : TELEPHONES) {
            if (t.getNumber().equals(telephone.getNumber())) {
                check = false;
                break;
            }
        }
        return check;
    }
}
