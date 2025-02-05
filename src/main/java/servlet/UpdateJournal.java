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

@WebServlet("/updateJournal")
public class UpdateJournal extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        int journalId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("journalId", journalId);
        String journalName = request.getParameter("journalName");
        request.setAttribute("journalName", journalName);
        String journalDescription = request.getParameter("journalDescription");
        request.setAttribute("journalDescription", journalDescription);
        try {
            getServletContext().getRequestDispatcher("/updateJournal.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + UpdateJournal.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + UpdateJournal.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        int journalId = Integer.parseInt(request.getParameter("journalId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        try {
            JournalController.getInstance().update(journalId, name, description);
            response.sendRedirect("journals");
        } catch (DaoException e) {
            System.out.println("Error! Class: " + UpdateJournal.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + UpdateJournal.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | NotInitializedException e) {
            System.out.println("Error! Class: " + UpdateJournal.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + UpdateJournal.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }
    }
}
