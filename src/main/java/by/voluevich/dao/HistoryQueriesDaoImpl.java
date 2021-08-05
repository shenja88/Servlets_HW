package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryQueriesDaoImpl implements HistoryQueriesDao {
    private static List<MathOperation> log = new ArrayList<>();

    public List<MathOperation> getLog() {
        return log;
    }

    public void addQuery(MathOperation mathOperation){
        log.add(mathOperation);
    }

    @Override
    public List<MathOperation> getLogByType(String operation) {
        List<MathOperation> logByType = new ArrayList<>();
        Pattern pattern = Pattern.compile(operation);
        for (MathOperation s: log){
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
        for (MathOperation m: log){
            if(m.getUser().equals(user)){
                mathOperations.add(m);
            }
        }
        return mathOperations;
    }
}
