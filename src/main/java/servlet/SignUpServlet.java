package servlet;

import controller.UserController;
import exception.DaoException;
import exception.EncryptorException;
import exception.NotInitializedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    public SignUpServlet() {
        System.out.println("in constructor SignUpServlet");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + SignUpServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + SignUpServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        try {

            UserController.getInstance().createUser(password, login);
            response.sendRedirect("login");
        } catch (DaoException e) {
            System.out.println("Error! Class: " + SignUpServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + SignUpServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        } catch (IOException | EncryptorException | NotInitializedException e) {
            System.out.println("Error! Class: " + SignUpServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            request.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + SignUpServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }
}
