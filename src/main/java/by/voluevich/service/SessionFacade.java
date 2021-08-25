package by.voluevich.service;

import by.voluevich.dao.MathOperationDaoImpl;
import by.voluevich.dao.UserDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.utils.*;

import java.util.List;
import java.util.Optional;

public class SessionFacade {
    private final Calculation calculation = new Calculation(new MathOperationDaoImpl());
    private final EditName editName = new EditName();
    private final EditPassword editPassword = new EditPassword();
    private final LogIn logIn = new LogIn(new UserDaoImpl());
    private final MathOperationHistory mathOperationHistory = new MathOperationHistory(new MathOperationDaoImpl());
    private final RecordMathOperation recordMathOperation = new RecordMathOperation(new MathOperationDaoImpl());
    private final Registration registration = new Registration(new UserDaoImpl());
    private final MathOperationLog mathOperationLog = new MathOperationLog();

    public double calculate(User user, String typeOp, double... nums) {
        Optional<MathOperation> mathOperation = calculation.getResult(user, typeOp, nums);
        if (mathOperation.isPresent()) {
            recordMathOperation.record(mathOperation.get());
            return mathOperation.get().getResult();
        } else {
            return -1;
        }
    }

    public boolean editName(User user, String newName) {
        return editName.editName(user, newName);
    }

    public String editPassword(User user, String oldPass, String newPass) {
        if (editPassword.checkNewPass(user, newPass)) {
            if (editPassword.editPassword(user, oldPass, newPass)) {
                return "Successful!";
            } else {
                return "You entered the wrong old password!";
            }
        } else {
            return "The old password is the same as the new password!";
        }
    }

    public List<MathOperation> getLogBySession(User user) {
        return mathOperationHistory.getBySession(user);
    }

    public List<MathOperation> getLogByType(String type, User user) {
        return mathOperationHistory.getByType(type, user);
    }

    public boolean getRegistration(User user) {
        return registration.save(user);
    }

    public Optional<User> getLogIn(User user){
        return logIn.logIn(user);
    }




    public List<List<MathOperation>> getLogBySession2(int numCurrentPage, int numValuesPage, User user) {
        return mathOperationLog.listForResponseBySession(numCurrentPage, numValuesPage, user);
    }

    public List<List<MathOperation>> getLogByType2(int numCurrentPage, int numValuesPage, String type, User user) {
        return mathOperationLog.listForResponseByType(numCurrentPage, numValuesPage, type, user);
    }
}
