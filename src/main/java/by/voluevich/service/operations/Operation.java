package by.voluevich.service.operations;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

public interface Operation {

    MathOperation getCalculation(User user, double ... num);
}
