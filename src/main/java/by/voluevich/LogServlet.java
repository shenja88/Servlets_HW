package by.voluevich;

import by.voluevich.Dao.LogQueriesImpl;
import by.voluevich.service.utils.CheckInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/log")
public class LogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");

        LogQueriesImpl log = new LogQueriesImpl();
        if (typeOp != null) {
            if (CheckInput.isExistOperation(typeOp)) {
                for (String s : log.getLogByType(typeOp)) {
                    resp.getWriter().println(s);
                }
            }else{
                resp.getWriter().print("Operation '" + typeOp + "' not found.");
            }
        } else {
            for (String s : log.getLog()) {
                resp.getWriter().println(s);
            }
        }
    }
}
