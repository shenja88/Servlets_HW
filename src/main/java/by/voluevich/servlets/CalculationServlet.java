package by.voluevich.servlets;

import by.voluevich.dao.HistoryQueriesDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.utils.Calculation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculationServlet", urlPatterns = "/mathOperation")
public class CalculationServlet extends HttpServlet {
    private final Calculation calculation = new Calculation(new HistoryQueriesDaoImpl());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("operation");
        String[] numStr = req.getParameterValues("num");

        double result = calculation.getResult((User) req.getSession().getAttribute("user"), typeOp, numStr);

        req.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/Calc.jsp").forward(req, resp);
    }
}

