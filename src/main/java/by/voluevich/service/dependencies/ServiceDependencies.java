package by.voluevich.service.dependencies;

import by.voluevich.dao.MathOperationDaoImpl;
import by.voluevich.dao.UserDaoImpl;
import by.voluevich.service.*;

public class ServiceDependencies {
    public final static Calculation calculation = new Calculation(new MathOperationDaoImpl());
    public final static EditName editName = new EditName();
    public final static EditPassword editPassword = new EditPassword();
    public final static LogIn logIn = new LogIn(new UserDaoImpl());
    public final static RecordMathOperation recordMathOperation = new RecordMathOperation(new MathOperationDaoImpl());
    public final static Registration registration = new Registration(new UserDaoImpl());
}
