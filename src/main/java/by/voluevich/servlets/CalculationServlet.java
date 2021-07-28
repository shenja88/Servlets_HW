package by.voluevich.servlets;

import by.voluevich.dao.HistoryQueriesDaoImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.operations.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculationServlet", urlPatterns = "/mathOperation")
public class CalculationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] numStr = req.getParameterValues("num");
        double[] nums = new double[numStr.length];
        for(int i = 0; i < nums.length; i ++){
            nums[i] = Double.parseDouble(numStr[i]);
        }
        String typeOp = req.getParameter("operation");

        MathOperation result = getOperation(typeOp).getCalculation((User) req.getSession().getAttribute("user"), nums);

        HistoryQueriesDaoImpl logQueries = new HistoryQueriesDaoImpl();
        logQueries.addQuery(result);

        resp.getWriter().print("Result: " + result.getResult());
    }

    private Operation getOperation(String type) {
        switch (type) {
            case "addition":
                return new Addition();
            case "subtraction":
                return new Subtraction();
            case "multiplication":
                return new Multiplication();
            case "division":
                return new Division();
            case "modulo":
                return new Modulo();
            default:
                return null;
        }
    }
}
