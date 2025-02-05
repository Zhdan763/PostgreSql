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

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            String journalName = request.getParameter("journalName");
            request.setAttribute("journalName", journalName);
            int journalId = Integer.parseInt(request.getParameter("journalId"));
            request.setAttribute("journalId", journalId);
            List<Task> taskList = TaskController.getInstance().getTasksByJournalId(journalId);
            request.setAttribute("taskList", taskList);
            getServletContext().getRequestDispatcher("/tasks.jsp").forward(request, response);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | ServletException | NotInitializedException e) {
            System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String journalName = request.getParameter("journalName");
        request.setAttribute("journalName", journalName);
        int journalId = Integer.parseInt(request.getParameter("journalId"));
        request.setAttribute("journalId", journalId);
        String type = request.getParameter("table");
        String value = request.getParameter("text");

        try {
            List<Task> taskList;
            switch (type) {
                case "name":
                    taskList = TaskController.getInstance().getTasksByJournalIdFilterByName(journalId, value);
                    request.setAttribute("taskList", taskList);
                    break;
                case "description":
                    taskList = TaskController.getInstance().getTasksByJournalIdFilterByDescription(journalId, value);
                    request.setAttribute("taskList", taskList);
                    break;
                case "date":
                    taskList = TaskController.getInstance().getTasksByJournalIdFilterByDate(journalId, value);
                    request.setAttribute("taskList", taskList);
                    break;
                case "status":
                    taskList = TaskController.getInstance().getTasksByJournalIdFilterByStatus(journalId, value);
                    request.setAttribute("taskList", taskList);
                    break;
            }
            request.setAttribute("type", type);
            request.setAttribute("value", value);
            getServletContext().getRequestDispatcher("/tasks.jsp?journalId=" + journalId +
                    "&journalName=" + journalName).forward(request, response);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | ServletException | NotInitializedException e) {
            System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + TasksServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }
}
