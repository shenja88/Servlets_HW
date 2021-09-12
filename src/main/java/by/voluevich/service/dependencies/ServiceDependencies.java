package by.voluevich.service.dependencies;

import by.voluevich.dao.*;
import by.voluevich.service.*;

public class ServiceDependencies {
    public final static Calculation CALCULATION = new Calculation(new HibernateMathOperationDaoIml());
    public final static EditName EDIT_NAME = new EditName(new HibernateUserDaoImpl());
    public final static EditPassword EDIT_PASSWORD = new EditPassword(new HibernateUserDaoImpl());
    public final static LogIn LOG_IN = new LogIn(new HibernateUserDaoImpl());
    public final static RecordMathOperation RECORD_MATH_OPERATION = new RecordMathOperation(new HibernateMathOperationDaoIml());
    public final static Registration REGISTRATION = new Registration(new HibernateUserDaoImpl());
    public final static UserAddresses USER_ADDRESSES = new UserAddresses(new HibernateAddressDaoImpl());
    public final static UserTelephones USER_TELEPHONES = new UserTelephones(new HibernateTelephonesDaoImpl());
}
