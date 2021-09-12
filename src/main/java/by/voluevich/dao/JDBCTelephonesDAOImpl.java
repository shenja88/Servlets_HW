package by.voluevich.dao;

import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;
import by.voluevich.utils.ConnectorManagerForJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTelephonesDAOImpl implements TelephonesDao {
    private static final int NUMBER = 1;
    private static final int USER_ID = 2;
    private static final int IS_PRIMARY = 3;
    private static final String SAVE = "INSERT INTO telephones (number, user_id, is_primary) VALUES (?, ?, ?)";
    private static final String IS_EXIST = "SELECT * FROM telephones WHERE number = ? and user_id = ?";
    private static final String GET_ALL = "SELECT * FROM telephones JOIN users u on u.id = telephones.user_id WHERE user_id = ?";
    private static final String UPDATE = "UPDATE telephones SET number = ? WHERE telephone_id = ? and user_id = ?";
    private static final String REMOVE = "DELETE FROM telephones WHERE telephone_id = ? and  user_id = ? and is_primary = false";
    private static final String IS_EXIST_BY_ID = "SELECT * FROM telephones WHERE telephone_id = ? AND user_id = ?";

    @Override
    public boolean saveTelephone(Telephone telephone) {
        if (!isExist(telephone)) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
                preparedStatement.setString(NUMBER, telephone.getNumber());
                preparedStatement.setInt(USER_ID, telephone.getUser().getId());
                preparedStatement.setBoolean(IS_PRIMARY, telephone.isPrimary());
                preparedStatement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Telephone> getAllByUser(User user) {
        List<Telephone> telephoneList = new ArrayList<>();
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            telephoneList = getFromResultSet(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return telephoneList;
    }

    @Override
    public boolean remove(int id, User user) {
        if(isExistById(id, user)) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(REMOVE);
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(USER_ID, user.getId());
                if(preparedStatement.executeUpdate() == 1) {
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Telephone telephone) {
        if (isExist(telephone)) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setString(NUMBER, telephone.getNumber());
                preparedStatement.setInt(2, telephone.getId());
                preparedStatement.setInt(3, telephone.getUser().getId());
                preparedStatement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean isExist(Telephone telephone) {
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST);
            preparedStatement.setString(1, telephone.getNumber());
            preparedStatement.setInt(2, telephone.getUser().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean isExistById(int id, User user) {
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, user.getId());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private List<Telephone> getFromResultSet(ResultSet resultSet) throws SQLException {
        List<Telephone> telephones = new ArrayList<>();
        while (resultSet.next()) {
            telephones.add(new Telephone(
                    resultSet.getInt("telephone_id"),
                    resultSet.getBoolean("is_primary"),
                    resultSet.getString("number"),
                    new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    )));
        }
        return telephones;
    }
}
