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

@WebServlet("/deleteTaskCheckbox")
public class DeleteTasksCheckbox extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String journalId = request.getParameter("journalId");
        String journalName = request.getParameter("journalName");
        String[] taskId = request.getParameterValues("id");
        try {
            for (String id : taskId
            ) {
                int taskIdInt = Integer.parseInt(id);
                TaskController.getInstance().delete(taskIdInt);
            }
            response.sendRedirect("tasks?journalId=" + journalId + "&journalName=" + journalName);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + DeleteTasksCheckbox.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + DeleteTasksCheckbox.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | NotInitializedException e) {
            System.out.println("Error! Class: " + DeleteTasksCheckbox.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + DeleteTasksCheckbox.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }
    }

}
