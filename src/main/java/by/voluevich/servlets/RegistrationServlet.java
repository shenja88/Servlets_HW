package by.voluevich.servlets;

import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
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

        if (sessionFacade.getRegistration(new User(name, login, password))) {
            req.setAttribute("message_reg", "Registration successful!");
        } else {
            req.setAttribute("message_reg", "Invalid registration!");
        }
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
