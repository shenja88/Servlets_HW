package by.voluevich;

import by.voluevich.dao.LogQueriesImpl;
import by.voluevich.entity.MathOperation;
import by.voluevich.service.Operation;
import by.voluevich.service.utils.CheckInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mathOperation")
public class OperationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numOne = Integer.parseInt(req.getParameter("numOne"));
        int numTwo = Integer.parseInt(req.getParameter("numTwo"));
        String typeOp = req.getParameter("operation");

        if(CheckInput.isExistOperation(typeOp)) {
            MathOperation result = Operation.getOperation(numOne, numTwo, typeOp);

            LogQueriesImpl logQueries = new LogQueriesImpl();
            logQueries.addQuery(result);

            resp.getWriter().print(result);
        }else{
            resp.getWriter().print("Operation '" + typeOp + "' not found.");
        }
    }
}
