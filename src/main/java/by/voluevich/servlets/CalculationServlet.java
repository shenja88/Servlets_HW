package by.voluevich.servlets;

import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;
import by.voluevich.service.CheckInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculationServlet", urlPatterns = "/mathOperation")
public class CalculationServlet extends HttpServlet {
    private final SessionFacade sessionFacade = new SessionFacade();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("operation");
        String[] numStr = req.getParameterValues("num");
        double[] nums = CheckInput.parseToDouble(numStr);

        double result = sessionFacade.calculate((User) req.getSession().getAttribute("user"), typeOp, nums);

        req.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/calc.jsp").forward(req, resp);
    }
}

