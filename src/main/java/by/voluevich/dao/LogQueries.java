package by.voluevich.dao;

import by.voluevich.entity.MathOperation;

import java.util.List;

public interface LogQueries {

    List<MathOperation> getLog();

    void addQuery(MathOperation mathOperation);

    List<MathOperation> getLogByType(String operation);
}
