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

@WebServlet(name = "TestHistoryServlet", urlPatterns = "/testHistory")
public class TestHistoryServlet extends HttpServlet {
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<List<MathOperation>> list = sessionFacade.getLogBySession2(1, 5, (User) req.getSession().getAttribute("user"));
        List<MathOperation> current = list.get(0);
        List<MathOperation> next = list.get(2);
        req.setAttribute("current", current);
        req.setAttribute("next", next);
        req.setAttribute("numPages", 10);
        getServletContext().getRequestDispatcher("/testHistory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");
        int numPage = 0;
        if(req.getParameter("numPage") == null){
            numPage = 1;
        }else{
            numPage = Integer.parseInt(req.getParameter("numPage"));
        }

        List<List<MathOperation>> list = sessionFacade.getLogByType2(
                numPage,
                5,
                typeOp,
                (User) req.getSession().getAttribute("user"));

        List<MathOperation> current = list.get(0);
        List<MathOperation> previous = list.get(1);
        List<MathOperation> next = list.get(2);
        req.setAttribute("current", current);
        req.setAttribute("next", next);
        req.setAttribute("previous", previous);

        getServletContext().getRequestDispatcher("/testHistory.jsp").forward(req, resp);
    }
}
