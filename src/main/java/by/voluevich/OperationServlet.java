package by.voluevich;

import by.voluevich.Dao.LogQueriesImpl;
import by.voluevich.service.Operation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mathOperation")
public class OperationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int numOne = Integer.parseInt(req.getParameter("numOne"));
        int numTwo = Integer.parseInt(req.getParameter("numTwo"));
        String operation = req.getParameter("operation");

        Operation operation1 = new Operation(numOne, numTwo);
        String result = operation1.getOperation(operation);

        LogQueriesImpl logQueries = new LogQueriesImpl();
        logQueries.addQuery(numOne, numTwo, operation, result);

        resp.getWriter().print(result);
    }
}
