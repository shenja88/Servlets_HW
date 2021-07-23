package by.voluevich.Dao;

import java.util.List;

public interface LogQueries {

    List<String> getLog();

    void addQuery(int one, int two, String typeOp, String result);
}
