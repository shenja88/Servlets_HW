package by.voluevich.servlets;

import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditNameServlet", urlPatterns = "/editName")
public class EditNameServlet extends HttpServlet {
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/editName.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("newName");

        if (sessionFacade.editName((User) req.getSession().getAttribute("user"), newName)) {
            req.setAttribute("message_acc", "Successful!");
        } else {
            req.setAttribute("message_acc", "The new name cannot be the same as the old one!");
        }
        getServletContext().getRequestDispatcher("/editName.jsp").forward(req, resp);
    }
}
