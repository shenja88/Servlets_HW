package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

public class Subtraction implements Operation{
    private final String name = "subtraction";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MathOperation getCalculation(User user, double ... num) {
        double result = getResultForOutput(num[0] - num[1]);
        return new MathOperation(num[0], num[1], name, result, user);
    }
}
