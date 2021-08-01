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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/SignIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        User user = new User(login, password);
        if(userDao.isExistUser(user)){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/mathOperation");
        }else{
            req.setAttribute("message_signIn","Invalid logIn!");
            getServletContext().getRequestDispatcher("/SignIn.jsp").forward(req, resp);
        }
    }
}
