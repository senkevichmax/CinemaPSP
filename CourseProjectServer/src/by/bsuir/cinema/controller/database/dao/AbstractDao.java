package by.bsuir.cinema.controller.database.dao;


import by.bsuir.cinema.controller.database.pool.ProxyConnection;
import by.bsuir.cinema.controller.exception.ProjectException;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao {
    protected ProxyConnection connection;

    public void setConnection(ProxyConnection connection){
        this.connection = connection;
    }

    public void close(Statement statement) throws ProjectException {
        try{
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new ProjectException("CloseStatementException!", e);
        }
    }
}
