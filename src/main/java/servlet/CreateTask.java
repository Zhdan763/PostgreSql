package servlet;

import controller.TaskController;
import exception.DaoException;
import exception.DateConverterException;
import exception.NotInitializedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createTask")
public class CreateTask extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        int journalId = Integer.parseInt(request.getParameter("journalId"));
        request.setAttribute("journalId", journalId);
        String journalName = request.getParameter("journalName");
        request.setAttribute("journalName", journalName);
        try {
            getServletContext().getRequestDispatcher("/createTask.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + CreateTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        int journalId = Integer.parseInt(request.getParameter("journalId"));
        String name = request.getParameter("name");
        String journalName = request.getParameter("journalName");
        String descriptionTask = request.getParameter("descriptionTask");
        String date = request.getParameter("date");
        try {
            TaskController.getInstance().createTask(journalId, name, descriptionTask, date);
            response.sendRedirect("tasks?journalId=" + journalId + "&journalName=" + journalName);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + CreateTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + CreateTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (DateConverterException | IOException | NotInitializedException e) {
            System.out.println("Error! Class: " + CreateTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + CreateTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }


    }
}
