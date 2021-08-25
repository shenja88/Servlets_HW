package by.voluevich.servlets;

import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditPasswordServlet", urlPatterns = "/editPassword")
public class EditPasswordServlet extends HttpServlet {
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/editPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPass");
        String newPassword = req.getParameter("newPass");

        String message = sessionFacade.editPassword(
                (User) req.getSession().getAttribute("user"),
                oldPassword,
                newPassword);

        req.setAttribute("message_acc", message);
        getServletContext().getRequestDispatcher("/editPassword.jsp").forward(req, resp);
    }
}
