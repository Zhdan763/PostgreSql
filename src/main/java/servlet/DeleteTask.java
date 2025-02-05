package servlet;

import controller.TaskController;
import exception.DaoException;
import exception.NotInitializedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String journalId = request.getParameter("journalId");
        String journalName = request.getParameter("journalName");
        try {
            TaskController.getInstance().delete(taskId);
            response.sendRedirect("tasks?journalId=" + journalId + "&journalName=" + journalName);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + DeleteTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + DeleteTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | NotInitializedException e) {
            System.out.println("Error! Class: " + DeleteTask.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + DeleteTask.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }


}
