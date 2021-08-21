package by.voluevich.servlets;

import by.voluevich.dao.MathOperationDao;
import by.voluevich.dao.MathOperationDaoImpl;
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
    private final MathOperationDao mathOperationDao = new MathOperationDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MathOperation> mathOperationList = mathOperationDao.getBySession((User) req.getSession().getAttribute("user"));
        req.setAttribute("log_list", mathOperationList);
        getServletContext().getRequestDispatcher("/History.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");
        if (CheckInput.isExistOperation(typeOp)) {
            List<MathOperation> mathOperationList = mathOperationDao.getByType(typeOp, (User) req.getSession().getAttribute("user"));
            req.setAttribute("log_list", mathOperationList);
        }
        getServletContext().getRequestDispatcher("/History.jsp").forward(req, resp);
    }
}

