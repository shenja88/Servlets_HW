package by.voluevich.servlets;

import by.voluevich.entity.MathOperation;
import by.voluevich.entity.User;
import by.voluevich.service.MathOperationHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = "/log")
public class HistoryServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HistoryServlet.class.getName());
    private final MathOperationHistory mathOperationHistory = new MathOperationHistory();
    private int currentPage = 1;
    private final int numValuesPage = 5;
    private int numPages = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("type") == null) {
            if (req.getParameter("currentPage") != null) {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }

            List<List<MathOperation>> list = mathOperationHistory.listForResponseBySession(
                    currentPage,
                    numValuesPage,
                    (User) req.getSession().getAttribute("user"));
            numPages = mathOperationHistory.getSizeListForResponse();

            List<MathOperation> current = list.get(0);
            List<MathOperation> next = list.get(2);
            List<MathOperation> prev = list.get(1);

            req.setAttribute("currentList", current);
            req.setAttribute("nextList", next);
            req.setAttribute("prevList", prev);
            req.setAttribute("numPages", numPages);
            req.setAttribute("currentPage", currentPage);
            logger.info("Request history by session.");
            getServletContext().getRequestDispatcher("/sessionHistory.jsp").forward(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOp = req.getParameter("type");
        currentPage = 1;
        if (req.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        List<List<MathOperation>> list = mathOperationHistory.listForResponseByType(
                currentPage,
                numValuesPage,
                typeOp,
                (User) req.getSession().getAttribute("user"));
        numPages = mathOperationHistory.getSizeListForResponse();

        List<MathOperation> next = list.get(2);
        List<MathOperation> prev = list.get(1);
        List<MathOperation> current = list.get(0);

        req.setAttribute("currentList", current);
        req.setAttribute("nextList", next);
        req.setAttribute("prevList", prev);
        req.setAttribute("numPages", numPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("type", typeOp);

        logger.info("Request history by type.");
        getServletContext().getRequestDispatcher("/historyByType.jsp").forward(req, resp);
    }
}
