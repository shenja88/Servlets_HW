package by.voluevich.service.utils;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.operations.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Calculation {
    private MathOperationDao mathOperationDao;
    private final List<Operation> operationList;

    {
        operationList = Arrays.asList(
                new Addition(),
                new Division(),
                new Modulo(),
                new Multiplication(),
                new Subtraction());
    }

    public Calculation(MathOperationDao mathOperationDao) {
        this.mathOperationDao = mathOperationDao;
    }

    public Calculation() {
    }

    public MathOperationDao getHistoryQueriesDao() {
        return mathOperationDao;
    }

    public double getResult(User user, String typeOp, double... nums) {
        Optional<Operation> operation = getTypeOperation(typeOp);
        if (operation.isPresent()) {
            MathOperation mathOperation = operation.get().getCalculation(user, nums);
            recordToHistory(mathOperation);
            return mathOperation.getResult();
        } else {
            return -1;
        }
    }

    private Optional<Operation> getTypeOperation(String type) {
        return operationList.stream().filter(x -> x.getName().equals(type)).findFirst();
    }

    private void recordToHistory(MathOperation mathOperation) {
        mathOperationDao.save(mathOperation);
    }
}
