package by.voluevich;

import by.voluevich.Dao.LogQueriesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/log")
public class LogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogQueriesImpl log = new LogQueriesImpl();
        for (String s: log.getLog()) {
            resp.getWriter().println(s);
        }
    }
}