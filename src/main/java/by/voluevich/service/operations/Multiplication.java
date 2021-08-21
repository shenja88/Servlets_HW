package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

public class Multiplication implements Operation {
    private final String name = "multiplication";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MathOperation getCalculation(User user, double... num) {
        return resultMathOperation(num[0], num[1], name, getResultNumForResponse(num[0] * num[1]), user);
    }
}
