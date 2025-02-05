import controller.JournalController;
import controller.TaskController;
import controller.UserController;
import dao.DaoManager;
import exception.ConnectionException;
import exception.PropertyException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Main implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            DaoManager daoManager = new DaoManager();
            UserController.initInstance(daoManager.getUserDao());
            JournalController.initInstance(daoManager.getJournalDao());
            TaskController.initInstance(daoManager.getTaskDao());
        } catch (ConnectionException | PropertyException e) {
            System.out.println("Error! Class: " + Main.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            System.exit(1);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
