package by.voluevich.service.utils;

import by.voluevich.dao.HistoryQueriesDao;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.operations.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Calculation {
    private HistoryQueriesDao historyQueriesDao;
    private List<Operation> operationList;

    public Calculation(HistoryQueriesDao historyQueriesDao) {
        this.historyQueriesDao = historyQueriesDao;
        operationList = Arrays.asList(
                new Addition(),
                new Division(),
                new Modulo(),
                new Multiplication(),
                new Subtraction());
    }

    public Calculation() {
    }

    public HistoryQueriesDao getHistoryQueriesDao() {
        return historyQueriesDao;
    }

    public double getResult(User user, String typeOp, String ... numStr) {
        Optional<Operation> operation = getTypeOperation(typeOp);
        double[] nums = parseToDouble(numStr);
        if(operation.isPresent()){
            MathOperation mathOperation = operation.get().getCalculation(user, nums);
            recordToHistory(mathOperation);
            return mathOperation.getResult();
        }else {
            return -1;
        }

    }

    private Optional<Operation> getTypeOperation(String type) {
        return operationList.stream().filter(x -> x.getName().equals(type)).findFirst();
    }

    private double[] parseToDouble(String [] numStr){
        double[] nums = new double[numStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Double.parseDouble(numStr[i]);
        }
        return nums;
    }

    private void recordToHistory(MathOperation mathOperation){
        historyQueriesDao.addQuery(mathOperation);
    }
}
