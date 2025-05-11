package servlet;

import controller.TaskController;
import exception.ControllerException;
import exception.DaoException;
import exception.DateConverterException;
import exception.NotInitializedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateTask")
public class UpdateTask extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        request.setAttribute("taskId", taskId);
        int journalId = Integer.parseInt(request.getParameter("journalId"));
        request.setAttribute("journalId", journalId);
        String journalName = request.getParameter("journalName");
        request.setAttribute("journalName", journalName);
        String taskName = request.getParameter("taskName");
        request.setAttribute("taskName", taskName);
        String taskDescription = request.getParameter("taskDescription");
        request.setAttribute("taskDescription", taskDescription);
        try {
            getServletContext().getRequestDispatcher("/updateTask.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + UpdateTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + UpdateTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        int journalId = Integer.parseInt(request.getParameter("journalId"));
        String date = request.getParameter("date");
        String journalName = request.getParameter("journalName");
        String name = request.getParameter("name");
        String description = request.getParameter("descriptionTask");
        try {
            TaskController.getInstance().update(taskId, journalId, name, description, date);
            response.sendRedirect("tasks?journalId=" + journalId + "&journalName=" + journalName);
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + UpdateTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + UpdateTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException e) {
            System.out.println("Error! Class: " + UpdateTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + UpdateTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }
    }
}
