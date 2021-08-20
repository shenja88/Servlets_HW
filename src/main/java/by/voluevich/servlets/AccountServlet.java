package by.voluevich.servlets;

import by.voluevich.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountServlet", urlPatterns = "/account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPass");
        String newPassword = req.getParameter("newPass");
        String newName = req.getParameter("newName");

        User thisUser = (User) req.getSession().getAttribute("user");

        if (thisUser.getPassword().equals(oldPassword)) {
            thisUser.setPassword(newPassword);
            thisUser.setName(newName);
            req.setAttribute("message_acc", "Successful!");
        } else {
            req.setAttribute("message_acc", "You entered the wrong old password!");
        }
        getServletContext().getRequestDispatcher("/Account.jsp").forward(req, resp);
    }
}
