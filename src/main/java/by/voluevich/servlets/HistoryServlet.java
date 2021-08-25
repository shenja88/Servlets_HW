package by.voluevich.servlets;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = "/log")
public class HistoryServlet extends HttpServlet {
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MathOperation> mathOperationList = sessionFacade.getLogBySession((User) req.getSession().getAttribute("user"));
        req.setAttribute("log_list", mathOperationList);
        getServletContext().getRequestDispatcher("/history.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");

        List<MathOperation> mathOperationList = sessionFacade.getLogByType(typeOp, (User) req.getSession().getAttribute("user"));
        req.setAttribute("log_list", mathOperationList);

        getServletContext().getRequestDispatcher("/history.jsp").forward(req, resp);
    }
}

