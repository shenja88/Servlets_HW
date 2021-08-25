package by.voluevich.servlets;

import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LogInServlet", urlPatterns = "/login")
public class LogInServlet extends HttpServlet {
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/logIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(login, password);
        Optional<User> userAuth = sessionFacade.getLogIn(user);

        if (userAuth.isPresent()) {
            req.getSession().setAttribute("user", userAuth.get());
            req.setAttribute("message_signIn", "LogIn successful!");
        } else {
            req.setAttribute("message_signIn", "Invalid logIn!");
        }
        getServletContext().getRequestDispatcher("/logIn.jsp").forward(req, resp);
    }
}
