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
import java.util.Optional;

@WebServlet(name = "LogInServlet", urlPatterns = "/login")
public class LogInServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(LogInServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/logIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User userForCheck = new User(login, password);
        Optional<User> user = sessionFacade.getLogIn(userForCheck);
        if (user.isPresent()) {
            User userForAuth = user.get();
            req.getSession().setAttribute("user", userForAuth);
            req.setAttribute("message_signIn", "LogIn successful!");
            logger.info("Successful logIn for user - {}.", userForAuth.getName());
        } else {
            req.setAttribute("message_signIn", "Invalid logIn!");
            logger.info("Login attempt failed.");
        }
        getServletContext().getRequestDispatcher("/logIn.jsp").forward(req, resp);
    }
}
