package by.voluevich.dao;

import by.voluevich.entity.User;
import by.voluevich.utils.ConnectorManagerForJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDaoImpl implements UserDao {
    private static final String SAVE_USER = "INSERT INTO calculator.users (name, login, password) values (?, ?, ?)";
    private static final String GET_BY_LOGIN = "SELECT * FROM calculator.users WHERE login = ?";
    private static final String GET_ALL = "SELECT * FROM calculator.users";
    private static final String UPDATE_NAME = "UPDATE calculator.users SET name = ? WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE calculator.users SET password = ? WHERE login = ?";
    private static final int NAME_COL = 1;
    private static final int LOGIN_COL = 2;
    private static final int PASSWORD_COL = 3;

    @Override
    public boolean save(User user) {
        if(!isExist(user)) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(SAVE_USER);
                statement.setString(NAME_COL, user.getName());
                statement.setString(LOGIN_COL, user.getLogin());
                statement.setString(PASSWORD_COL, user.getPassword());
                statement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean isExist(User user) {
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN);
            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User userForCheck = getUserFromResultSet(resultSet);
                if(user.equals(userForCheck)){
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while ((resultSet.next())){
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateName(User user, String newName) {
        if(isExist(user) && !newName.equals(user.getName())) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME);
                preparedStatement.setString(NAME_COL, newName);
                preparedStatement.setString(LOGIN_COL, user.getLogin());
                preparedStatement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updatePassword(User user, String oldPass, String newPass) {
        if(isExist(user) && user.getPassword().equals(oldPass) && !oldPass.equals(newPass)){
            try(Connection connection = ConnectorManagerForJDBC.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = getUserFromResultSet(resultSet);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("login"),
                resultSet.getString("password")
        );
    }
}
