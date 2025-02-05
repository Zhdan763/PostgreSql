package servlet;

import controller.JournalController;
import exception.DaoException;
import exception.NotInitializedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteJournalCheckbox")
public class DeleteJournalCheckbox extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String[] journalIdCheckBox = request.getParameterValues("id");
        try {
            for (String id : journalIdCheckBox
            ) {
                int journalIdInt = Integer.parseInt(id);
                JournalController.getInstance().delete(journalIdInt);
            }
            response.sendRedirect("journals");
        } catch (DaoException e) {
            System.out.println("Error! Class: " + DeleteJournalCheckbox.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + DeleteJournalCheckbox.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | NotInitializedException e) {
            System.out.println("Error! Class: " + DeleteJournalCheckbox.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + DeleteJournalCheckbox.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }
}
