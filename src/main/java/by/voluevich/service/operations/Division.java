package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

public class Division implements Operation {
    private final String name = "division";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MathOperation getCalculation(User user, double... num) {
        double result = getResultNumForResponse(num[0] / num[1]);
        return resultMathOperation(num[0], num[1], name, result, user);
    }
}
