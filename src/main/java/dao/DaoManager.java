package dao;

import dao.postgresqlimpl.PostgreSqlJournalDao;
import dao.postgresqlimpl.PostgreSqlTaskDao;
import dao.postgresqlimpl.PostgreSqlUserDao;
import exception.ConnectionException;
import exception.PropertyException;

import java.util.HashMap;
import java.util.Map;

public class DaoManager {
    private ConnectionManager connectionManager;
    public static final String TASK_DAO = "task",
            JOURNAL_DAO = "journal",
            USER_DAO = "user";
    private Map<String, UserDao> userDaoMap;
    private Map<String, TaskDao> taskDaoMap;
    private Map<String, JournalDao> journalDaoMap;

    public DaoManager() throws ConnectionException, PropertyException {
        this.connectionManager = new ConnectionManager();
        this.userDaoMap = new HashMap<>();
        this.taskDaoMap = new HashMap<>();
        this.journalDaoMap = new HashMap<>();
        PostgreSqlTaskDao postgreSqlTaskDao = new PostgreSqlTaskDao(connectionManager.getConnection());
        PostgreSqlJournalDao postgreSqlJournalDao = new PostgreSqlJournalDao(connectionManager.getConnection());
        PostgreSqlUserDao postgreSqlUserDao = new PostgreSqlUserDao(connectionManager.getConnection());

        userDaoMap.put(USER_DAO, postgreSqlUserDao);
        taskDaoMap.put(TASK_DAO, postgreSqlTaskDao);
        journalDaoMap.put(JOURNAL_DAO, postgreSqlJournalDao);
    }

    public void closeConnection() throws ConnectionException {
        connectionManager.closeConnection();
    }

    public UserDao getUserDao() {
        return userDaoMap.get(USER_DAO);
    }

    public TaskDao getTaskDao() {
        return taskDaoMap.get(TASK_DAO);
    }

    public JournalDao getJournalDao() {
        return journalDaoMap.get(JOURNAL_DAO);
    }

}
