package by.voluevich.servlets;

import by.voluevich.dao.HibernateUserDaoImpl;
import by.voluevich.dao.InMemoryUserDaoImpl;
import by.voluevich.dao.JDBCUserDaoImpl;
import by.voluevich.entity.Address;
import by.voluevich.entity.Telephone;
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
        String telephone = req.getParameter("telephone");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int numHome = Integer.parseInt(req.getParameter("numHome"));

        User user = new User(name, login, password);
        if (sessionFacade.getRegistration(user)) {
            User userForInfo = new HibernateUserDaoImpl().getUserByLogin(user.getLogin());
            sessionFacade.addAddress(new Address(true, city, street, numHome, userForInfo));
            sessionFacade.addTelephone(new Telephone(true, telephone, userForInfo));

            req.setAttribute("message_reg", "Registration successful!");
            logger.info("Register new user - {}.", user.getName());
        } else {
            req.setAttribute("message_reg", "Invalid registration!");
            logger.info("Failed registration attempt.");
        }
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
