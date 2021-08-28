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

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        

        User user = new User(name, login, password);
        if (sessionFacade.getRegistration(user)) {
            req.setAttribute("message_reg", "Registration successful!");
            logger.info("Register new user - {}.", user.getName());
        } else {
            req.setAttribute("message_reg", "Invalid registration!");
            logger.info("Failed registration attempt.");
        }
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
