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

@WebServlet(name = "AccountServlet", urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPass");
        String newPassword = req.getParameter("newPass");
        String newName = req.getParameter("newName");

        String responseMessage = userDao.editUserInfo((User) req.getSession().getAttribute("user"), oldPassword, newPassword, newName);
        req.setAttribute("message_acc", responseMessage);
        getServletContext().getRequestDispatcher("/Account.jsp").forward(req, resp);
    }
}
