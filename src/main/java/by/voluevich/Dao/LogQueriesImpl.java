package by.voluevich.Dao;

import java.util.ArrayList;
import java.util.List;

public class LogQueriesImpl implements LogQueries {
    private static final List<String> LOG = new ArrayList<>();

    public List<String> getLog() {
        return LOG;
    }

    public void addQuery(int one, int two, String typeOp, String result){
        LOG.add("Operation: " + typeOp + ", with number " + one + " and " + two + ". " + result);
    }
}
