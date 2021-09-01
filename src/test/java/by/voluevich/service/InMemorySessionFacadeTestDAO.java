package by.voluevich.service;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.dao.InMemoryMathOperationDaoImpl;
import by.voluevich.dao.UserDao;
import by.voluevich.dao.InMemoryUserDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InMemorySessionFacadeTestDAO {
    private final MathOperationDao mathOperationDao = new InMemoryMathOperationDaoImpl();
    private final UserDao userDao = new InMemoryUserDaoImpl();
    private final SessionFacade sessionFacade = new SessionFacade();

    @BeforeAll
    void initDB() {
        List<User> users = userDao.getAll();
        User user1 = new User("alex", "qwe@mail.ru", "12345");
        User user2 = new User("eugene", "ewq@mail.ru", "54321");
        users.add(user1);
        users.add(user2);

        List<MathOperation> mathOperationList = mathOperationDao.getAll();
        mathOperationList.add(new MathOperation(5, 10, "addition", 15, user1));
        mathOperationList.add(new MathOperation(10, 5, "subtraction", 5, user1));
        mathOperationList.add(new MathOperation(2.5, 6, "multiplication", 15, user1));
        mathOperationList.add(new MathOperation(12, 4, "division", 3, user1));
        mathOperationList.add(new MathOperation(9, 2, "modulo", 1, user1));
        mathOperationList.add(new MathOperation(5, 10, "addition", 15, user2));
        mathOperationList.add(new MathOperation(10, 5, "subtraction", 5, user2));
        mathOperationList.add(new MathOperation(2.5, 6, "multiplication", 15, user2));
        mathOperationList.add(new MathOperation(12, 4, "division", 3, user2));
        mathOperationList.add(new MathOperation(9, 2, "modulo", 1, user2));
    }

    @DisplayName("testing getRegistration method to false from facade")
    @ParameterizedTest
    @CsvSource({
            "alex, qwe@mail.ru, 12345",
            "eugene, ewq@mail.ru, 54321"
    })
    void getRegistrationFalseTesting(String name, String login, String pass) {
        assertFalse(sessionFacade.getRegistration(new User(name, login, pass)));
    }

    @DisplayName("testing getRegistration method to true from facade")
    @ParameterizedTest
    @CsvSource({
            "sara, wsx@mail.ru, 12qw",
            "john, 123@mail.ru, 43er"
    })
    void getRegistrationTrueTesting(String name, String login, String pass) {
        assertTrue(sessionFacade.getRegistration(new User(name, login, pass)));
    }


    @DisplayName("testing getLogIn method to false from facade")
    @ParameterizedTest
    @CsvSource({
            "sara, wsx@mail.ru, 12qw",
            "john, 123@mail.ru, 43er"
    })
    void getLogInFalseTest(String name, String login, String pass) {
        assertEquals(Optional.empty(), sessionFacade.getLogIn(new User(name, login, pass)));
    }

    @DisplayName("testing getLogIn method to true from facade")
    @ParameterizedTest
    @CsvSource({
            "alex, qwe@mail.ru, 12345",
            "eugene, ewq@mail.ru, 54321"
    })
    void getLogInTrueTest(String name, String login, String pass) {
        assertEquals(Optional.of(new User(name, login, pass)), sessionFacade.getLogIn(new User(name, login, pass)));
    }

    @DisplayName("testing calculate method to addition from facade")
    @ParameterizedTest
    @CsvSource({
            "3, 10, addition",
            "15, 4, addition",
            "2.5, 50, addition"
    })
    void calculateAddition(double num1, double num2, String typeOp) {
        double[] nums = {num1, num2};
        assertEquals(trimDouble(num1 + num2), sessionFacade.calculate(new User(), typeOp, nums));
    }

    @DisplayName("testing calculate method to subtraction from facade")
    @ParameterizedTest
    @CsvSource({
            "15, 5, subtraction",
            "24, 4.5, subtraction",
            "55.45, 4.454545654654, subtraction"
    })
    void calculateSubtraction(double num1, double num2, String typeOp) {
        double[] nums = {num1, num2};
        assertEquals(trimDouble(num1 - num2), sessionFacade.calculate(new User(), typeOp, nums));
    }

    @DisplayName("testing calculate method to multiplication from facade")
    @ParameterizedTest
    @CsvSource({
            "15, 5, multiplication",
            "24, 4.5, multiplication",
            "55.45, 4.454545654654, multiplication"
    })
    void calculateMultiplication(double num1, double num2, String typeOp) {
        double[] nums = {num1, num2};
        assertEquals(trimDouble(num1 * num2), sessionFacade.calculate(new User(), typeOp, nums));
    }

    @DisplayName("testing calculate method to division from facade")
    @ParameterizedTest
    @CsvSource({
            "15, 5, division",
            "24, 4.5, division",
            "55.45, 4.454545654654, division"
    })
    void calculateDivision(double num1, double num2, String typeOp) {
        double[] nums = {num1, num2};
        assertEquals(trimDouble(num1 / num2), sessionFacade.calculate(new User(), typeOp, nums));
    }

    @DisplayName("testing calculate method to division from facade")
    @ParameterizedTest
    @CsvSource({
            "19, 4, modulo",
            "23, 5, modulo",
            "154, 20, modulo"
    })
    void calculateModulo(double num1, double num2, String typeOp) {
        double[] nums = {num1, num2};
        assertEquals(trimDouble(num1 % num2), sessionFacade.calculate(new User(), typeOp, nums));
    }

    //To match the format of the double output
    private double trimDouble(double num) {
        double result;
        BigDecimal resultBD = new BigDecimal(num);
        resultBD = resultBD.setScale(3, RoundingMode.DOWN);
        result = resultBD.doubleValue();
        return result;
    }


    @DisplayName("testing editName method to false from facade")
    @ParameterizedTest
    @ValueSource(strings = {"eugene"})
    void editNameTestFalse(String newName) {
        assertFalse(sessionFacade.editName(new User("eugene", "qwe@gmail.com", "123"), newName));
    }

    @DisplayName("testing editName method to true from facade")
    @ParameterizedTest
    @ValueSource(strings = {"eugene", "sara", "laura"})
    void editNameTestTrue(String newName) {
        assertTrue(sessionFacade.editName(new User("alex", "qwe@gmail.com", "123"), newName));
    }

    @DisplayName("testing editPassword method to true from facade")
    @ParameterizedTest
    @CsvSource({
            "1234, 12345",
            "wrq22312, 23s12e!"
    })
    void editPasswordToTrue(String oldPass, String newPass) {
        User user = new User("alex", "qwe@mail.ru", oldPass);
        assertTrue(sessionFacade.editPassword(user, oldPass, newPass));
    }

    @DisplayName("testing editPassword method to false from facade")
    @ParameterizedTest
    @CsvSource({
            "1234, 1234",
            "wrq22312, 23s12e!"
    })
    void editPasswordToFalse(String oldPass, String newPass) {
        User user = new User("alex", "qwe@mail.ru", "1234");
        assertFalse(sessionFacade.editPassword(user, oldPass, newPass));
    }

    @Test
    @DisplayName("testing getLogBySession method from facade")
    void getLogBySession() {
        User user = userDao.getAll().get(0);

        List<MathOperation> listExpected = mathOperationDao.getAll().stream()
                .filter(x -> x.getUser().equals(user))
                .collect(Collectors.toList());
        List<MathOperation> listActual = mathOperationDao.getBySession(user);
        listExpected.removeAll(listActual);

        boolean flag = listExpected.isEmpty();
        assertTrue(flag);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "addition",
            "subtraction",
            "multiplication",
            "division",
            "modulo"
    })
    @DisplayName("testing getLogByType method from facade")
    void getLogByType(String type) {
        User user = userDao.getAll().get(1);

        List<MathOperation> listExpected = mathOperationDao.getAll().stream()
                .filter(x -> x.getUser().equals(user) && x.getTypeOp().equals(type))
                .collect(Collectors.toList());
        List<MathOperation> listActual = mathOperationDao.getByType(type, user);
        listExpected.removeAll(listActual);

        boolean flag = listExpected.isEmpty();
        assertTrue(flag);
    }


    @AfterAll
    void cleaning() {
        List<User> users = userDao.getAll();
        List<MathOperation> mathOperations = mathOperationDao.getAll();

        users.clear();
        mathOperations.clear();
    }
}

