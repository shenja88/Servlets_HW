package by.voluevich.dao;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMathOperationDaoImpl implements MathOperationDao {

    @Override
    public List<MathOperation> getAll() {
        List<MathOperation> mathOperations = new ArrayList<>();
        try (Connection connection = ConnectorManager.getConnection()) {
            String query = "SELECT math_operations.num_one, math_operations.num_two," +
                    " math_operations.type_operation, math_operations.result," +
                    " u.name, u.login, u.password FROM calculator.math_operations" +
                    " LEFT JOIN users u on u.login = math_operations.user_login";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            mathOperations = getListFromJDBC(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mathOperations;
    }

    @Override
    public void save(MathOperation mathOperation) {
        try (Connection connection = ConnectorManager.getConnection()) {
            String query = "INSERT INTO math_operations VALUES (default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, mathOperation.getNumOne());
            preparedStatement.setDouble(2, mathOperation.getNumTwo());
            preparedStatement.setString(3, mathOperation.getTypeOp());
            preparedStatement.setDouble(4, mathOperation.getResult());
            preparedStatement.setString(5, mathOperation.getUser().getLogin());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<MathOperation> getByType(String operation, User user) {
        List<MathOperation> list = new ArrayList<>();

        try (Connection connection = ConnectorManager.getConnection()) {
            String query = "SELECT math_operations.num_one, math_operations.num_two," +
                    " math_operations.type_operation, math_operations.result," +
                    " u.name, u.login, u.password FROM calculator.math_operations" +
                    " LEFT JOIN users u on u.login = math_operations.user_login" +
                    " WHERE math_operations.type_operation = ? and u.login = ?";
           PreparedStatement preparedStatement = connection.prepareStatement(query);
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
            String query = "SELECT math_operations.num_one, math_operations.num_two," +
                    " math_operations.type_operation, math_operations.result," +
                    " u.name, u.login, u.password FROM calculator.math_operations" +
                    " LEFT JOIN users u on u.login = math_operations.user_login" +
                    " WHERE u.login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
