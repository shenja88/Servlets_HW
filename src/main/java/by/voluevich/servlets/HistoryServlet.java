package by.voluevich.servlets;

import by.voluevich.dao.HistoryQueriesDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.utils.CheckInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HistoryServlet", urlPatterns = "/log")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");
        boolean logBySession = Boolean.parseBoolean(req.getParameter("logBySession"));
        HistoryQueriesDaoImpl log = new HistoryQueriesDaoImpl();
        if (typeOp != null) {
            if (CheckInput.isExistOperation(typeOp)) {
                for (MathOperation s : log.getLogByType(typeOp)) {
                    resp.getWriter().println(s);
                }
            }
        } else if (logBySession) {
            for (MathOperation s : log.getLogBySession((User) req.getSession().getAttribute("user"))) {
                resp.getWriter().println(s);
            }
        } else {
            for (MathOperation s : log.getLog()) {
                resp.getWriter().println(s);
            }
        }
    }
}

