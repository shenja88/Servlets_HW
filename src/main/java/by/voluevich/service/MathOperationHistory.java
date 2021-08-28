package by.voluevich.service;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MathOperationHistory {
    private MathOperationDao mathOperationDao;

    public MathOperationHistory(MathOperationDao mathOperationDao) {
        this.mathOperationDao = mathOperationDao;
    }

    public MathOperationHistory() {
    }

    public MathOperationDao getMathOperationDao() {
        return mathOperationDao;
    }

    public void setMathOperationDao(MathOperationDao mathOperationDao) {
        this.mathOperationDao = mathOperationDao;
    }

    public List<MathOperation> getBySession(User user){
        return mathOperationDao.getBySession(user);
    }

    public List<MathOperation> getByType(String operation, User user){
        if (CheckInput.isExistOperation(operation)) {
            return mathOperationDao.getByType(operation, user);
        } else{
            return new ArrayList<>();
        }
    }
}
