package by.voluevich.dao;

import by.voluevich.entity.Address;
import by.voluevich.entity.User;
import by.voluevich.utils.ConnectorManagerForJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAddressesDaoImpl implements AddressDao {
    private static final int CITY = 1;
    private static final int STREET = 2;
    private static final int NUM_HOME = 3;
    private static final int USER_ID = 4;
    private static final int IS_PRIMARY = 5;
    private static final String IS_EXIST = "SELECT * FROM addresses JOIN users u ON addresses.user_id = u.id WHERE " +
            " addresses.city = ? and addresses.street = ? and addresses.numHome = ? and addresses.user_id = ? and " +
            " addresses.is_primary = ?";
    private static final String SAVE = "INSERT INTO addresses (city, street, numHome, user_id, is_primary) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM addresses JOIN users u ON u.id = addresses.user_id " +
            " WHERE user_id = ?";
    private static final String UPDATE = "UPDATE addresses SET city = ?, street = ?, numHome = ? WHERE user_id = ? " +
            " and address_id = ?";
    private static final String REMOVE = "DELETE FROM addresses WHERE address_id = ? AND user_id = ? AND is_primary = false";
    private static final String GET_BY_ID = "SELECT * FROM addresses WHERE address_id = ? AND user_id = ?";

    @Override
    public boolean saveAddress(Address address) {
        if (!isExist(address)) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
                setParam(address, preparedStatement);
                preparedStatement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Address> getAllByUser(User user) {
        List<Address> addressList = new ArrayList<>();
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            addressList = getFromResultSet(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addressList;
    }

    @Override
    public boolean remove(int id, User user) {
        if (isExistById(id, user))
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(REMOVE);
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, user.getId());
                if(preparedStatement.executeUpdate() == 1){
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return false;
    }

    @Override
    public boolean update(Address address) {
        if (isExistById(address.getId(), address.getUser())) {
            try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                setParam(address, preparedStatement);
                preparedStatement.setInt(5, address.getId());
                preparedStatement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean isExist(Address address) {
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST);
            setParam(address, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private void setParam(Address address, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(CITY, address.getCity());
        preparedStatement.setString(STREET, address.getStreet());
        preparedStatement.setInt(NUM_HOME, address.getNumHome());
        preparedStatement.setInt(USER_ID, address.getUser().getId());
        preparedStatement.setBoolean(IS_PRIMARY, address.isPrimary());
    }

    private List<Address> getFromResultSet(ResultSet resultSet) throws SQLException {
        List<Address> addressList = new ArrayList<>();
        while (resultSet.next()) {
            addressList.add(new Address(
                    resultSet.getInt("address_id"),
                    resultSet.getBoolean("is_primary"),
                    resultSet.getString("city"),
                    resultSet.getString("street"),
                    resultSet.getInt("numHome"),
                    new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    )));
        }
        return addressList;
    }

    private boolean isExistById(int id, User user) {
        try (Connection connection = ConnectorManagerForJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
