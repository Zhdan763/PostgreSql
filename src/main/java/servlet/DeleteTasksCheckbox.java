package servlet;

import Strategy.ExportDTO;
import controller.TaskController;
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

@WebServlet("/deleteTaskCheckbox")
public class DeleteTasksCheckbox extends HttpServlet {
    private JsonByteConverter jsonByteConverter;
    private ExportDTO exportDTO;
    private byte[] bytes;
    private ArrayConverter arrayConverter;

    public DeleteTasksCheckbox() {
        this.jsonByteConverter = new JsonByteConverter();
        this.exportDTO = null;
        this.bytes = new byte[0];
        this.arrayConverter = new ArrayConverter();
    }

    @EJB
    Ejb ejb;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String journalId = request.getParameter("journalId");
        String journalName = request.getParameter("journalName");
        String[] taskId = request.getParameterValues("id");
        int[] taskIdArray = arrayConverter.stringArrayToIntArray(taskId);
        String action = request.getParameter("action");
        switch (action) {
            case (Constants.DELETE_TASKS):
                for (String id : taskId
                ) {
                    int taskIdInt = Integer.parseInt(id);
                    try {
                        TaskController.getInstance().delete(taskIdInt);
                    } catch (ControllerException e) {
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
                    }
                }
                try {
                    response.sendRedirect("tasks?journalId=" + journalId + "&journalName=" + journalName);
                } catch (IOException e) {
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
                break;
            case (Constants.EXPORT_TASKS_WITH_JOURNAL):
                String type = "Task with journal";
                try {
                    exportDTO = ejb.exportDTO(type, taskIdArray);
                } catch (EjbException e) {
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
                }

                try {
                    bytes = jsonByteConverter.exportDtoToByte(exportDTO);
                } catch (JsonByteConverterException e) {
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
                }

                try {
                    ServletOutputStream out = response.getOutputStream();
                    response.setContentType("application/json");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "json.json" + "\"");
                    out.write(bytes);
                    out.flush();
                    out.close();
                } catch (IOException e) {
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
                break;

            case (Constants.EXPORT_TASKS_WITHOUT_JOURNAL):
                String type2 = "Task without journal";
                try {
                    exportDTO = ejb.exportDTO(type2, taskIdArray);
                } catch (EjbException e) {
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
                }

                try {
                    bytes = jsonByteConverter.exportDtoToByte(exportDTO);
                } catch (JsonByteConverterException e) {
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
                }
                try {
                    ServletOutputStream out = response.getOutputStream();
                    response.setContentType("application/json");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "json.json" + "\"");
                    out.write(bytes);
                    out.flush();
                    out.close();
                } catch (IOException e) {
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
                break;
        }

    }

}
