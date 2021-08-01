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
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = "/log")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/History.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");
        boolean logBySession = Boolean.parseBoolean(req.getParameter("logBySession"));
        HistoryQueriesDaoImpl log = new HistoryQueriesDaoImpl();
        if (typeOp != null) {
            if (CheckInput.isExistOperation(typeOp)) {
                List<MathOperation> mathOperationList = log.getLogByType(typeOp);
                req.setAttribute("log_list", mathOperationList);
            }
        } else if (logBySession) {
            List<MathOperation> mathOperationList = log.getLogBySession((User) req.getSession().getAttribute("user"));
            req.setAttribute("log_list", mathOperationList);
        } else {
            List<MathOperation> mathOperationList = log.getLog();
            req.setAttribute("log_list", mathOperationList);
        }
        getServletContext().getRequestDispatcher("/History.jsp").forward(req, resp);
    }
}

