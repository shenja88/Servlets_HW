package by.voluevich.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogQueriesImpl implements LogQueries {
    private static final List<String> LOG = new ArrayList<>();

    public List<String> getLog() {
        return LOG;
    }

    public void addQuery(int one, int two, String typeOp, String result){
        LOG.add("Operation: " + typeOp + ", with number " + one + " and " + two + ". " + result);
    }

    @Override
    public List<String> getLogByType(String operation) {
        List<String> logByType = new ArrayList<>();
        Pattern pattern = Pattern.compile(operation);
        for (String s: LOG){
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                logByType.add(s);
            }
        }
        return logByType;
    }
}
