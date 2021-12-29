package by.bsuir.cinema.controller.database.dao;

import by.bsuir.cinema.controller.database.pool.ConnectionPool;
import by.bsuir.cinema.controller.database.pool.ProxyConnection;
import by.bsuir.cinema.controller.exception.ProjectException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class DaoManager {
        private ProxyConnection connection;
        private List<AbstractDao> abstractDaos;

        public DaoManager(){
            abstractDaos = new ArrayList<>();
        }

        public void startDAO(AbstractDao abstractDao, AbstractDao... abstractDaos) throws ProjectException {
            if (connection == null){
                connection = ConnectionPool.getInstance().getConnection();
            }
            try{
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new ProjectException("SQLException", e);
            }
            abstractDao.setConnection(connection);
            this.abstractDaos.add(abstractDao);
            for (AbstractDao currentDao: abstractDaos){
                currentDao.setConnection(connection);
                this.abstractDaos.add(currentDao);
            }
        }

        public void endDAO(){
            for (AbstractDao currentDao: abstractDaos){
                currentDao.setConnection(null);
            }
            abstractDaos.clear();
            if (connection != null){
                ConnectionPool.getInstance().releaseConnection(connection);
                connection = null;
            }
        }

        public void commit() throws ProjectException {
            if (connection != null){
                try{
                    connection.commit();
                } catch (SQLException e) {
                    throw new ProjectException("SQLException", e);
                }
            }
        }

        public void rollback() throws ProjectException {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException e) {
                    throw new ProjectException("SQLException", e);
                }
            }
        }
    }

