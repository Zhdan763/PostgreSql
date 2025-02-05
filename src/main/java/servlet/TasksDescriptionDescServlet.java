package servlet;

import controller.TaskController;
import domain.Task;
import exception.DaoException;
import exception.NotInitializedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasksDescriptionDesc")
public class TasksDescriptionDescServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String journalName = request.getParameter("journalName");
            request.setAttribute("journalName", journalName);
            int journalId = Integer.parseInt(request.getParameter("journalId"));
            request.setAttribute("journalId", journalId);
            List<Task> taskList = TaskController.getInstance().getTasksByJournalIdDescriptionDesc(journalId);
            request.setAttribute("taskList", taskList);
            getServletContext().getRequestDispatcher("/tasks.jsp").forward(request, response);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + TasksDescriptionDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksDescriptionDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | ServletException | NotInitializedException e) {
            System.out.println("Error! Class: " + TasksDescriptionDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksDescriptionDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }

}
