package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.utils.ConnectorManagerForJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMathOperationDaoImpl implements MathOperationDao {
    private static final String GET_ALL_MO = "SELECT * FROM calculator.math_operations" +
            " JOIN users u on u.id = math_operations.user_id";
    private static final String SAVE_MO = "INSERT INTO calculator.math_operations VALUES (default, ?, ?, ?, ?, ?)";
    private static final String BY_TYPE = " WHERE typeOp = ? and u.id = ?";
    private static final String BY_SESSION = " WHERE u.id = ?";
    private static final int NUM_ONE = 1;
    private static final int NUM_TWO = 2;
    private static final int TYPE = 3;
    private static final int RESULT = 4;
    private static final int USER = 5;

    @Override
    public List<MathOperation> getAll() {
        List<MathOperation> mathOperations = new ArrayList<>();
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
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
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MO);
            preparedStatement.setDouble(NUM_ONE, mathOperation.getNumOne());
            preparedStatement.setDouble(NUM_TWO, mathOperation.getNumTwo());
            preparedStatement.setString(TYPE, mathOperation.getTypeOp());
            preparedStatement.setDouble(RESULT, mathOperation.getResult());
            preparedStatement.setInt(USER, mathOperation.getUser().getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<MathOperation> getByType(String operation, User user) {
        List<MathOperation> list = new ArrayList<>();

        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
           PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MO + BY_TYPE);
           preparedStatement.setString(1, operation);
           preparedStatement.setInt(2, user.getId());
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
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MO + BY_SESSION);
            preparedStatement.setInt(1, user.getId());
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
                    resultSet.getDouble("numOne"),
                    resultSet.getDouble("numTwo"),
                    resultSet.getString("typeOp"),
                    resultSet.getDouble("result"),
                    new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    )
            ));
        }
        return mathOperations;
    }
}
