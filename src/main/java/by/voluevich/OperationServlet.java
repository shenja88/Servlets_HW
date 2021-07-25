package by.voluevich;

import by.voluevich.Dao.LogQueriesImpl;
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
            Operation operation = new Operation(numOne, numTwo);
            String result = operation.getOperation(typeOp);

            LogQueriesImpl logQueries = new LogQueriesImpl();
            logQueries.addQuery(numOne, numTwo, typeOp, result);

            resp.getWriter().print(result);
        }else{
            resp.getWriter().print("Operation '" + typeOp + "' not found.");
        }
    }
}
