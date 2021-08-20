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

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        if(userDao.addUser(new User(name, login, password))){
            req.setAttribute("message_reg","Registration successful!");
        } else{
            req.setAttribute("message_reg","Invalid registration.");
        }
        getServletContext().getRequestDispatcher("/Registration.jsp").forward(req, resp);
    }
}
