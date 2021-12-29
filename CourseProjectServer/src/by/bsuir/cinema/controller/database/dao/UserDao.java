package by.bsuir.cinema.controller.database.dao;


import by.bsuir.cinema.controller.entity.user.Client;
import by.bsuir.cinema.controller.entity.user.TypeUser;
import by.bsuir.cinema.controller.entity.user.User;
import by.bsuir.cinema.controller.exception.ProjectException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao {
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from User where login = ? and password = ?";
    private static final String FIND_CLIENT_BY_ID = "select * from Client where user_id = ?";
    private static final String FIND_SESSION_PRICE = "select price from Session join Basket on session_id = id where " +
            "session_id = ?";
    private static final String UPDATE_CLIENT_MONEY = "update Client set cash = ? where user_id = ?";
    private static final String FIND_ID_BY_LOGIN = "select id from User where login = ?";
    private static final String INSERT_USER = "insert into User(login, password, role) values(?, ?, 'client')";
    private static final String INSERT_CLIENT = "insert into Client(user_id, cash) values(?, ?)";

    public User findUserByLoginAndPassword(String login, String password) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getString(4).equals("admin")){
                    return new User(resultSet.getInt(1), resultSet.getString(2),
                            TypeUser.valueOf(resultSet.getString(4).toUpperCase()));
                } else {
                    PreparedStatement preparedStatementForClient = connection.prepareStatement(FIND_CLIENT_BY_ID);
                    preparedStatementForClient.setInt(1, resultSet.getInt(1));
                    ResultSet resultSetForClient = preparedStatementForClient.executeQuery();
                    if (resultSetForClient.next()){
                        return new Client(resultSet.getInt(1), resultSet.getString(2),
                                resultSetForClient.getBigDecimal(2),
                                TypeUser.valueOf(resultSet.getString(4).toUpperCase()));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return null;
    }

    public boolean updateUserMoney(BigDecimal value, Client client) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_CLIENT_MONEY);
            BigDecimal newBalance = BigDecimal.valueOf(client.getMoney().doubleValue() + value.doubleValue());
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, client.getId());
            flag = preparedStatement.executeUpdate() != 0;
            client.setMoney(newBalance);
        } catch (SQLException e) {
            throw new ProjectException("UpdateUserMoneyException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }

    public BigDecimal updateUserMoney(int userId, int sessionId, BigDecimal userBalance) throws ProjectException {
        PreparedStatement preparedStatement = null;
        BigDecimal newBalance = BigDecimal.valueOf(-1);
        try{
            preparedStatement = connection.prepareStatement(FIND_SESSION_PRICE);
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                BigDecimal sessionPrice = resultSet.getBigDecimal(1);
                preparedStatement = connection.prepareStatement(UPDATE_CLIENT_MONEY);
                newBalance = BigDecimal.valueOf(userBalance.doubleValue() - sessionPrice.doubleValue());
                preparedStatement.setBigDecimal(1, newBalance);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ProjectException("UpdateUserMoneyException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return newBalance;
    }

    public boolean isLoginExists(String login) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_ID_BY_LOGIN);
            preparedStatement.setString(1, login);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            throw new ProjectException("SQLException, e", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    public Client insertClient(String login, String password) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(FIND_ID_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int insertedId = resultSet.getInt(1);
                preparedStatement = connection.prepareStatement(INSERT_CLIENT);
                preparedStatement.setInt(1, insertedId);
                preparedStatement.setBigDecimal(2, BigDecimal.valueOf(500.0));
                preparedStatement.executeUpdate();
                return new Client(insertedId, login, BigDecimal.valueOf(500.0));
            }

        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return null;
    }
}
