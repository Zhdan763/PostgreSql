package servlet;

import controller.JournalController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/exportJournalCheckbox")
public class ExportJournalCheckbox extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] journalIdCheckBox = req.getParameterValues("id");
        resp.getWriter().append("export");
        System.out.println(journalIdCheckBox);
    }
}
