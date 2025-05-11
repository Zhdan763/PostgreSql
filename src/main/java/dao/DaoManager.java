package dao;

import dao.hibernate.HibernatePostgreSqlJournalDao;
import dao.hibernate.HibernatePostgreSqlTaskDao;
import dao.hibernate.HibernatePostgreSqlUserDao;
import dao.postgresqlimpl.PostgreSqlJournalDao;
import dao.postgresqlimpl.PostgreSqlTaskDao;
import dao.postgresqlimpl.PostgreSqlUserDao;
import exception.ConnectionException;
import exception.DaoManagerException;
import util.Constants;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DaoManager {
    private ConnectionManager connectionManager;
    private HibernateConnectionManager hibernateConnectionManager;
    public static final String TASK_DAO = "task",
            JOURNAL_DAO = "journal",
            USER_DAO = "user";
    private Map<String, UserDao> userDaoMap;
    private Map<String, TaskDao> taskDaoMap;
    private Map<String, JournalDao> journalDaoMap;
    private PostgreSqlTaskDao postgreSqlTaskDao;
    private PostgreSqlJournalDao postgreSqlJournalDao;
    private PostgreSqlUserDao postgreSqlUserDao;
    private HibernatePostgreSqlJournalDao hibernatePostgreSqlJournalDao;
    private HibernatePostgreSqlTaskDao hibernatePostgreSqlTaskDao;
    private HibernatePostgreSqlUserDao hibernatePostgreSqlUserDao;

    public DaoManager() throws DaoManagerException {
        this.connectionManager = null;
        this.hibernateConnectionManager = null;
        this.userDaoMap = new HashMap<>();
        this.taskDaoMap = new HashMap<>();
        this.journalDaoMap = new HashMap<>();
        this.postgreSqlTaskDao = null;
        this.postgreSqlJournalDao = null;
        this.postgreSqlUserDao = null;
        this.hibernatePostgreSqlJournalDao = null;
        this.hibernatePostgreSqlTaskDao = null;
        this.hibernatePostgreSqlUserDao = null;

        initProperty();

    }


    public void initProperty() throws DaoManagerException {
        URL jbdc = getClass().getResource(Constants.PATH_JBDC);
        URL hib = getClass().getResource(Constants.PATH_HIBERNATE);

        if (jbdc != null && hib != null) {
            throw new DaoManagerException("Choose one property file");
        } else if (jbdc != null) {
            try {
                connectionManager = new ConnectionManager();
            } catch (ConnectionException e) {
                System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + e);
                throw new DaoManagerException("It is impossible to get a connectionManager");
            }
            postgreSqlTaskDao = new PostgreSqlTaskDao(connectionManager.getConnection());
            postgreSqlJournalDao = new PostgreSqlJournalDao(connectionManager.getConnection());
            postgreSqlUserDao = new PostgreSqlUserDao(connectionManager.getConnection());
            taskDaoMap.put(TASK_DAO, postgreSqlTaskDao);
            journalDaoMap.put(JOURNAL_DAO, postgreSqlJournalDao);
            userDaoMap.put(USER_DAO, postgreSqlUserDao);
        } else if (hib != null) {
            hibernateConnectionManager = new HibernateConnectionManager();
            hibernatePostgreSqlJournalDao =
                    new HibernatePostgreSqlJournalDao(hibernateConnectionManager.getSessionFactory());
            hibernatePostgreSqlTaskDao =
                    new HibernatePostgreSqlTaskDao(hibernateConnectionManager.getSessionFactory());
            hibernatePostgreSqlUserDao =
                    new HibernatePostgreSqlUserDao(hibernateConnectionManager.getSessionFactory());
            userDaoMap.put(USER_DAO, hibernatePostgreSqlUserDao);
            taskDaoMap.put(TASK_DAO, hibernatePostgreSqlTaskDao);
            journalDaoMap.put(JOURNAL_DAO, hibernatePostgreSqlJournalDao);

        } else {
            throw new DaoManagerException("Property file not found");
        }

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
