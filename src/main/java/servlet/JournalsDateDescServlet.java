package servlet;

import controller.JournalController;
import domain.Journal;
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

@WebServlet("/journalsDateDesc")
public class JournalsDateDescServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Journal> journalList = JournalController.getInstance().getAllDateDesc();
            request.setAttribute("test", journalList);
            getServletContext().getRequestDispatcher("/journals.jsp").forward(request, response);
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + JournalsDateDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsDateDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + JournalsDateDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsDateDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }


    }


}
