package by.voluevich.service.dependencies;

import by.voluevich.dao.HibernateMathOperationDaoIml;
import by.voluevich.dao.HibernateUserDaoImpl;
import by.voluevich.dao.JDBCMathOperationDaoImpl;
import by.voluevich.dao.JDBCUserDaoImpl;
import by.voluevich.service.*;

public class ServiceDependencies {
    public final static Calculation calculation = new Calculation(new HibernateMathOperationDaoIml());
    public final static EditName editName = new EditName(new HibernateUserDaoImpl());
    public final static EditPassword editPassword = new EditPassword(new HibernateUserDaoImpl());
    public final static LogIn logIn = new LogIn(new HibernateUserDaoImpl());
    public final static RecordMathOperation recordMathOperation = new RecordMathOperation(new HibernateMathOperationDaoIml());
    public final static Registration registration = new Registration(new HibernateUserDaoImpl());
}
