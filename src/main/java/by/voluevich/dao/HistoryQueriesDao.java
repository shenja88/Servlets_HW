package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.util.List;

public interface HistoryQueriesDao {

    List<MathOperation> getLog();

    void addQuery(MathOperation mathOperation);

    List<MathOperation> getLogByType(String operation);

    List<MathOperation> getLogBySession(User user);
}
