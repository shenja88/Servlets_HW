package by.voluevich.service.utils;

import by.voluevich.dao.HistoryQueriesDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.operations.*;

public class Calculation {

    public static MathOperation getResult(User user, String typeOp, double ... num) {
        Operation operation = getOperation(typeOp);
        MathOperation mathOperation = operation.getCalculation(user, num);

        HistoryQueriesDaoImpl logQueries = new HistoryQueriesDaoImpl();
        logQueries.addQuery(mathOperation);
        return mathOperation;
    }

    private static Operation getOperation(String type) {
        switch (type) {
            case "addition":
                return new Addition();
            case "subtraction":
                return new Subtraction();
            case "multiplication":
                return new Multiplication();
            case "division":
                return new Division();
            case "modulo":
                return new Modulo();
            default:
                return null;
        }
    }
}
