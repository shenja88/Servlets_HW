package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

public class Multiplication implements Operation{
    private static final String NAME = "multiplication";

    @Override
    public MathOperation getCalculation(User user, double ... num) {
        double result = getResultForOutput(num[0] * num[1]);
        return new MathOperation(num[0], num[1], NAME, result, user);
    }
}
