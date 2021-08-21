package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathOperationDaoImpl implements MathOperationDao {
    private static final List<MathOperation> OPERATIONS = new ArrayList<>();

    public List<MathOperation> getAll() {
        return OPERATIONS;
    }

    public void save(MathOperation mathOperation) {
        OPERATIONS.add(mathOperation);
    }

    @Override
    public List<MathOperation> getByType(String operation, User user) {
        List<MathOperation> logByType = new ArrayList<>();
        Pattern pattern = Pattern.compile(operation);
        for (MathOperation mathOperation : OPERATIONS) {
            Matcher matcher = pattern.matcher(mathOperation.getTypeOp());
            if (matcher.find() && nameMath(mathOperation.getUser(), user)) {
                logByType.add(mathOperation);
            }
        }
        return logByType;
    }

    private boolean nameMath(User thisUser, User userInLog) {
        return userInLog.getName().equals(thisUser.getName());
    }

    @Override
    public List<MathOperation> getBySession(User user) {
        List<MathOperation> mathOperations = new ArrayList<>();
        for (MathOperation mathOperation : OPERATIONS) {
            if (mathOperation.getUser().equals(user)) {
                mathOperations.add(mathOperation);
            }
        }
        return mathOperations;
    }
}
