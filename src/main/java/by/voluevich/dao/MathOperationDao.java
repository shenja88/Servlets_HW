package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.util.List;

public interface MathOperationDao {

    List<MathOperation> getAll();

    void save(MathOperation mathOperation);

    List<MathOperation> getByType(String operation, User user);

    List<MathOperation> getBySession(User user);
}
