package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMathOperationDaoImpl implements MathOperationDao {
    private static final String GET_ALL_MO = "SELECT math_operations.num_one, math_operations.num_two," +
            " math_operations.type_operation, math_operations.result," +
            " u.name, u.login, u.password FROM calculator.math_operations" +
            " LEFT JOIN users u on u.login = math_operations.user_login";
    private static final String SAVE_MO = "INSERT INTO math_operations VALUES (default, ?, ?, ?, ?, ?)";
    private static final String BY_TYPE = " WHERE math_operations.type_operation = ? and u.login = ?";
    private static final String BY_SESSION = " WHERE u.login = ?";
    private static final int NUM_ONE_COL = 1;
    private static final int NUM_TWO_COL = 2;
    private static final int TYPE_COL = 3;
    private static final int RESULT_COL = 4;
    private static final int USER_COL = 5;

    @Override
    public List<MathOperation> getAll() {
        List<MathOperation> mathOperations = new ArrayList<>();
        try (Connection connection = ConnectorManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_MO);
            mathOperations = getListFromJDBC(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mathOperations;
    }

    @Override
    public void save(MathOperation mathOperation) {
        try (Connection connection = ConnectorManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MO);
            preparedStatement.setDouble(NUM_ONE_COL, mathOperation.getNumOne());
            preparedStatement.setDouble(NUM_TWO_COL, mathOperation.getNumTwo());
            preparedStatement.setString(TYPE_COL, mathOperation.getTypeOp());
            preparedStatement.setDouble(RESULT_COL, mathOperation.getResult());
            preparedStatement.setString(USER_COL, mathOperation.getUser().getLogin());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<MathOperation> getByType(String operation, User user) {
        List<MathOperation> list = new ArrayList<>();

        try (Connection connection = ConnectorManager.getConnection()) {
           PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MO + BY_TYPE);
           preparedStatement.setString(1, operation);
           preparedStatement.setString(2, user.getLogin());
           ResultSet resultSet = preparedStatement.executeQuery();
           list = getListFromJDBC(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<MathOperation> getBySession(User user) {
        List<MathOperation> list = new ArrayList<>();
        try (Connection connection = ConnectorManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MO + BY_SESSION);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            list = getListFromJDBC(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    private List<MathOperation> getListFromJDBC(ResultSet resultSet) throws SQLException {
        List<MathOperation> mathOperations = new ArrayList<>();
        while (resultSet.next()) {
            mathOperations.add(new MathOperation(
                    resultSet.getDouble("num_one"),
                    resultSet.getDouble("num_two"),
                    resultSet.getString("type_operation"),
                    resultSet.getDouble("result"),
                    new User(
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    )
            ));
        }
        return mathOperations;
    }
}
