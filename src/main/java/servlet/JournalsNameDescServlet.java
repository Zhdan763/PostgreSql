package servlet;

import controller.JournalController;
import domain.Journal;
import exception.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/journalsNameDesc")
public class JournalsNameDescServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            List<Journal> journalList = JournalController.getInstance().getAllNameDesc();
            request.setAttribute("test", journalList);
            getServletContext().getRequestDispatcher("/journals.jsp").forward(request, response);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + JournalsNameDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsNameDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (ServletException | IOException | NotInitializedException e) {
            System.out.println("Error! Class: " + JournalsNameDescServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + JournalsNameDescServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }


    }
}
