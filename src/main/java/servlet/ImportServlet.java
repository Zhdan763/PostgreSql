package servlet;

import Strategy.ExportDTO;
import exception.EjbException;
import exception.JsonByteConverterException;
import exception.PartConverterException;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.JsonByteConverter;
import util.PartConverter;

import java.io.IOException;

@WebServlet("/import")
@MultipartConfig
public class ImportServlet extends HttpServlet {
    private JsonByteConverter jsonByteConverter;
    private byte[] bytes;
    private ExportDTO exportDTO;

    public ImportServlet() {
        this.jsonByteConverter = new JsonByteConverter();
        this.bytes = new byte[0];
        this.exportDTO = new ExportDTO();

    }

    @EJB
    Ejb ejb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            getServletContext().getRequestDispatcher("/import.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String type = req.getParameter("table");
        Part part = null;
        try {
            part = req.getPart("f");

        } catch (IOException | ServletException e) {
            System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            req.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }
        PartConverter partConverter = new PartConverter(part);

        try {
            bytes = partConverter.partToByte();
        } catch (PartConverterException e) {
            System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            req.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

        try {
            exportDTO = jsonByteConverter.byteToExportDto(bytes);
        } catch (JsonByteConverterException e) {
            System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            req.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

        try {
            ejb.importDto(type, exportDTO);
        } catch (EjbException e) {
            System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = e.getMessage();
            req.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }
        try {
            resp.sendRedirect("journals");
        } catch (IOException e) {
            System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            String error = "Sorry, server error, try again later";
            req.setAttribute("error", error);
            try {
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                System.out.println("Error! Class: " + ImportServlet.class.getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + ex);
            }
        }

    }

}
