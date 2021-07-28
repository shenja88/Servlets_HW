package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryQueriesDaoImpl implements HistoryQueriesDao {
    private static final List<MathOperation> LOG = new ArrayList<>();

    public List<MathOperation> getLog() {
        return LOG;
    }

    public void addQuery(MathOperation mathOperation){
        LOG.add(mathOperation);
    }

    @Override
    public List<MathOperation> getLogByType(String operation) {
        List<MathOperation> logByType = new ArrayList<>();
        Pattern pattern = Pattern.compile(operation);
        for (MathOperation s: LOG){
            Matcher matcher = pattern.matcher(s.getTypeOp());
            if(matcher.find()){
                logByType.add(s);
            }
        }
        return logByType;
    }

    @Override
    public List<MathOperation> getLogBySession(User user) {
        List<MathOperation> mathOperations = new ArrayList<>();
        for (MathOperation m: LOG){
            if(m.getUser().equals(user)){
                mathOperations.add(m);
            }
        }
        return mathOperations;
    }
}
