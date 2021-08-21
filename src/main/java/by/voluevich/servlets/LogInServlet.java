package by.voluevich.servlets;

import by.voluevich.dao.UserDao;
import by.voluevich.dao.UserDaoImpl;
import by.voluevich.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogInServlet", urlPatterns = "/login")
public class LogInServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/LogIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(login, password);
        if (userDao.isExist(user)) {
            User userAuth = userDao.getAll().get(userDao.getAll().indexOf(user));
            req.getSession().setAttribute("user", userAuth);
            req.setAttribute("message_signIn", "Log in successful!");
        } else {
            req.setAttribute("message_signIn", "Invalid logIn!");
        }
        getServletContext().getRequestDispatcher("/LogIn.jsp").forward(req, resp);
    }
}
