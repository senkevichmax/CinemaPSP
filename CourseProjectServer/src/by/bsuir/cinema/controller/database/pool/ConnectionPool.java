package by.bsuir.cinema.controller.database.pool;


import by.bsuir.cinema.controller.exception.ProjectException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private BlockingQueue<ProxyConnection> connectionQueue;
    private final int DEFAULT_POOL_SIZE = 10;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();

    private ConnectionPool() {
        connectionQueue = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = ConnectorDb.getConnection();
            try {
                connectionQueue.put(new ProxyConnection(connection));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws ProjectException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ProjectException("InterruptedException", e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            connection.setAutoCommit(true);
            connectionQueue.put(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }
}
