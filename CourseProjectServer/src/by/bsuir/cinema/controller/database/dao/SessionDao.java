package by.bsuir.cinema.controller.database.dao;

import by.bsuir.cinema.controller.exception.ProjectException;

import java.math.BigDecimal;
import java.sql.*;

public class SessionDao extends AbstractDao {
    private static final String FIND_ALL_SESSIONS = "select Session.id, name, genre, producers, main_roles, date_time, " +
            "price from Session join Film on film_id = Film.id";
    private static final String INSERT_SESSION = "insert into Session(film_id, date_time, price) values(?, ?, ?)";
    private static final String DELETE_FROM_SESSION = "delete from Session where id = ?";

    public String findAllSessions() throws ProjectException {
        String allSessions = "";
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SESSIONS);
            while (resultSet.next()){
                allSessions += resultSet.getInt(1) + "\n Название фильма - " +
                        resultSet.getString(2) + ", \nЖанр - " + resultSet.getString(3) +
                        ", \nРежиссеры " + resultSet.getString(4) + ", \nВ главных ролях " +
                        resultSet.getString(5) + " \nВремя " + resultSet.getString(6) +
                        ", \nЦена в синемакоинах " + resultSet.getString(7) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                close(statement);
            }
        }
        return allSessions;
    }

    public boolean insertSession(int filmId, String dateAndTime, String cost) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(INSERT_SESSION);
            preparedStatement.setInt(1, filmId);
            preparedStatement.setDate(2, Date.valueOf(dateAndTime));
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(Double.parseDouble(cost)));
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

    public int[] find_price() throws ProjectException {
        PreparedStatement statement = null;
        int price[] = new int[25];
        String str = "SELECT price * FROM Session ";
        try {
            statement = connection.prepareStatement(str);
            ResultSet rs = statement.executeQuery();
            //ResultSet rs = statement.executeQuery(FIND_COMEDY);
            for (int i = 0; i<25; i++) {
                rs.next();
                price[i] = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null) {
                close(statement);
            }
        }
        return price;
    }

    public boolean deleteById(int sessionId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(DELETE_FROM_SESSION);
            preparedStatement.setInt(1, sessionId);
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

}