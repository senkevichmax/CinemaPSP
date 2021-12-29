package by.bsuir.cinema.controller.database.dao;


import by.bsuir.cinema.controller.exception.ProjectException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketDao extends AbstractDao {
    private static final String INSERT_INTO_BASKET = "insert into Basket(user_id, session_id) values(?, ?);";
    private static final String DELETE_FROM_BASKET = "delete from Basket where session_id = ? and user_id = ?";
    private static final String FIND_PRICE_BY_SESSION_ID = "select price from Session where id = ?";
    private static final String FIND_ORDERS_BY_USER_ID = "select Session.id, name, genre, producers, main_roles, " +
            "date_time, price from Film join Session on film_id = Film.id join Basket on Session.id = Basket.session_id " +
            "where Basket.user_id = ?";

    public String findOrdersByUserId(int userId) throws ProjectException {
        String allSessions = "";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allSessions += resultSet.getInt(1) + "\nНазвание фильма - " +
                        resultSet.getString(2) + ",\nЖанр - " + resultSet.getString(3) +
                        ",\nРежиссеры " + resultSet.getString(4) + ",\nВ главных ролях " +
                        resultSet.getString(5) + ",\nВремя " + resultSet.getString(6) +
                        ",\nЦена в синемакоинах " + resultSet.getString(7) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return allSessions;
    }

    public boolean deleteFromBasket(int userId, int sessionId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(DELETE_FROM_BASKET);
            preparedStatement.setInt(1, sessionId);
            preparedStatement.setInt(2, userId);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }

    public boolean insertIntoBasket(int userId, int sessionId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(INSERT_INTO_BASKET);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, sessionId);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }

    public boolean isEnoughMoney(BigDecimal balance, int sessionId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try{
            preparedStatement = connection.prepareStatement(FIND_PRICE_BY_SESSION_ID);
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                BigDecimal cost = resultSet.getBigDecimal(1);
                flag = balance.doubleValue() >= cost.doubleValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }
}
