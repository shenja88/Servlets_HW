package by.voluevich.dao;

import by.voluevich.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDaoImpl implements UserDao {

    @Override
    public boolean save(User user) {
        if(!isExist(user)) {
            try (Connection connection = ConnectorManager.getConnection()) {
                String query = "INSERT INTO users (name, login, password) values (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getPassword());
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
        try (Connection connection = ConnectorManager.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectorManager.getConnection()) {
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while ((resultSet.next())){
                users.add(new User(
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
        if(isExist(user)) {
            try (Connection connection = ConnectorManager.getConnection()) {
                String query = "UPDATE users SET name = ? WHERE login = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newName);
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
    public boolean updatePassword(User user, String oldPass, String newPass) {
        if(isExist(user) && !oldPass.equals(newPass)){
            try(Connection connection = ConnectorManager.getConnection()){
                String query = "UPDATE users SET password = ? WHERE login = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
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
}
