package by.voluevich.servlets;

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

@WebServlet(name = "EditPasswordServlet", urlPatterns = "/editPassword")
public class EditPasswordServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(EditPasswordServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/editPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPass");
        String newPassword = req.getParameter("newPass");

        User user = (User) req.getSession().getAttribute("user");

        if (sessionFacade.editPassword(user, oldPassword, newPassword)) {
            req.setAttribute("message_acc", "Successful!");
            user.setPassword(newPassword);
            logger.info("Successful request to edit password for user - {}", user.getName());
        } else {
            req.setAttribute("message_acc", "You entered the wrong old password or new password is the same as the old one!");
            logger.info("Failed request to edit password for user - {}", user.getName());
        }
        getServletContext().getRequestDispatcher("/editPassword.jsp").forward(req, resp);
    }
}
