package by.voluevich.servlets.account;

import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditNameServlet", urlPatterns = "/editName")
public class EditNameServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(EditNameServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/editName.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("newName");
        User user = (User) req.getSession().getAttribute("user");

        if (sessionFacade.editName(user, newName)) {
            req.setAttribute("message_acc", "Successful!");
            user.setName(newName);
            logger.info("Successful edit name attempt.");
        } else {
            req.setAttribute("message_acc", "The new name cannot be the same as the old one!");
            logger.info("Failed edit name attempt.");
        }
        getServletContext().getRequestDispatcher("/editName.jsp").forward(req, resp);
    }
}
