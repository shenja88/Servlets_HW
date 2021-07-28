package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

public class Subtraction implements Operation{
    private static final String NAME = "subtraction";

    @Override
    public MathOperation getCalculation(User user, double ... num) {
        return new MathOperation(num[0], num[1], NAME, (num[0] - num[1]), user);
    }
}
