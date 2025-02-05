package dao;

import exception.ConnectionException;
import exception.PropertyException;
import util.Constants;
import util.PropertiesParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class ConnectionManager {
    private Connection connection;
    private PropertiesParser propertiesParser;

    public ConnectionManager() throws ConnectionException, PropertyException {
        this.connection = null;
        this.propertiesParser = new PropertiesParser(Constants.PROPERTY_NAME);
        initConnection();
    }

    private void initConnection() throws ConnectionException {

        String driver = propertiesParser.getPropertyByName(Constants.JBDC_DRIVER);
        String url = propertiesParser.getPropertyByName(Constants.JBDC_URL);
        String user = propertiesParser.getPropertyByName(Constants.JBDC_USER);
        String password = propertiesParser.getPropertyByName(Constants.JBDC_PASSWORD);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error! Class: " + ConnectionManager.class.getName() +
                    ". Date: " + new Date() + ". Message: " + e.getMessage());
            throw new ConnectionException("incorrect jbdc.driver");
        }

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error! Class: " + ConnectionManager.class.getName() +
                    ". Date: " + new Date() + ". Message: " + e);
            throw new ConnectionException("Incorrect connection data");

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws ConnectionException {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error! Class: " + ConnectionManager.class.getName() +
                    ". Date: " + new Date() + ". Message: " + e.getMessage());
            throw new ConnectionException("Failed to close connection" + e.getMessage());
        }
    }
}
