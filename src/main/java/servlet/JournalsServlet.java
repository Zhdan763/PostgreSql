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

@WebServlet("/journals")
public class JournalsServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Journal> journalList = JournalController.getInstance().getAll();
            request.setAttribute("test", journalList);
            getServletContext().getRequestDispatcher("/journals.jsp").forward(request, response);
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter("table");
        String value = request.getParameter("text");

        try {
            List<Journal> journalList = null;
            switch (type) {
                case ("name"):
                    journalList = JournalController.getInstance().getAllFilterByName(value);
                    break;
                case ("description"):
                    journalList = JournalController.getInstance().getAllFilterByDescription(value);
                    break;
                case ("date"):
                    journalList = JournalController.getInstance().getAllFilterByDate(value);
                    break;
            }
            request.setAttribute("test", journalList);
            request.setAttribute("type", type);
            request.setAttribute("value", value);
            getServletContext().getRequestDispatcher("/journals.jsp?hello").forward(request, response);
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }
}
