package servlet;

import controller.TaskController;
import domain.Task;
import exception.ControllerException;
import exception.DaoException;
import exception.NotInitializedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasksNameDesc")
public class TasksNameDescServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String journalName = request.getParameter("journalName");
            request.setAttribute("journalName", journalName);
            int journalId = Integer.parseInt(request.getParameter("journalId"));
            request.setAttribute("journalId", journalId);
            List<Task> taskList = TaskController.getInstance().getTasksByJournalIdNameDesc(journalId);
            request.setAttribute("taskList", taskList);
            getServletContext().getRequestDispatcher("/tasks.jsp").forward(request, response);
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + TasksNameDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksNameDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | ServletException e) {
            System.out.println("Error! Class: " + TasksNameDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksNameDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }


    }


}
