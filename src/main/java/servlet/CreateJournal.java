package servlet;

import controller.JournalController;
import exception.ControllerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createJournal")
public class CreateJournal extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        try {
            JournalController.getInstance().create(name, description);
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + CreateJournal.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + CreateJournal.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

        try {
            response.sendRedirect("journals");
        } catch (IOException e) {
            System.out.println("Error! Class: " + CreateJournal.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + CreateJournal.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }


    }
}
