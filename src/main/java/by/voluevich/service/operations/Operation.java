package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Operation {

    MathOperation getCalculation(User user, double ... num);

    default double getResultForOutput(double num){
        double result;
        BigDecimal resultBD = new BigDecimal(num);
        resultBD = resultBD.setScale(3, RoundingMode.DOWN);
        result = resultBD.doubleValue();
        return result;
    }
}
