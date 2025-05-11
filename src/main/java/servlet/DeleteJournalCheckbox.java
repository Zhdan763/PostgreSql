package servlet;

import Strategy.ExportDTO;
import controller.JournalController;
import exception.ControllerException;
import exception.EjbException;
import exception.JsonByteConverterException;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.ArrayConverter;
import util.Constants;
import util.JsonByteConverter;

import java.io.IOException;

@WebServlet("/deleteJournalCheckbox")
public class DeleteJournalCheckbox extends HttpServlet {
    private JsonByteConverter jsonByteConverter;
    private ExportDTO exportDTO;
    private byte[] bytes;
    private ArrayConverter arrayConverter;


    public DeleteJournalCheckbox() {
        this.jsonByteConverter = new JsonByteConverter();
        this.exportDTO = null;
        this.bytes = new byte[0];
        this.arrayConverter = new ArrayConverter();
    }

    @EJB
    Ejb ejb;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String[] journalIdCheckBox = request.getParameterValues("id");
        int[] journalIdArray = arrayConverter.stringArrayToIntArray(journalIdCheckBox);
        String action = request.getParameter("action");

        switch (action) {
            case (Constants.DELETE_JOURNALS):
                for (String id : journalIdCheckBox
                ) {
                    int journalId = Integer.parseInt(id);
                    try {
                        JournalController.getInstance().delete(journalId);
                    } catch (ControllerException e) {
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
                    }
                }
                try {
                    response.sendRedirect("journals");
                } catch (IOException e) {
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
                break;
            case (Constants.EXPORT_JOURNALS_WITH_TASKS):
                String type = "Journal with tasks";
                try {
                    exportDTO = ejb.exportDTO(type, journalIdArray);
                } catch (EjbException e) {
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
                }
                try {
                    bytes = jsonByteConverter.exportDtoToByte(exportDTO);
                } catch (JsonByteConverterException e) {
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
                }

                try {
                    ServletOutputStream out = response.getOutputStream();
                    response.setContentType("application/json");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "json.json" + "\"");
                    out.write(bytes);
                    out.flush();
                    out.close();
                } catch (IOException e) {
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

                break;
            case (Constants.EXPORT_JOURNALS_WITHOUT_TASKS):
                String type2 = "Journal without tasks";
                try {
                    exportDTO = ejb.exportDTO(type2, journalIdArray);
                } catch (EjbException e) {
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
                }

                try {
                    bytes = jsonByteConverter.exportDtoToByte(exportDTO);
                } catch (JsonByteConverterException e) {
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
                }

                try {
                    ServletOutputStream out = response.getOutputStream();
                    response.setContentType("application/json");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "json.json" + "\"");
                    out.write(bytes);
                    out.flush();
                    out.close();
                } catch (IOException e) {
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
                break;
        }
    }
}
