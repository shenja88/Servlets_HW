package by.voluevich.servlets;

import by.voluevich.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", urlPatterns = "/logOut")
public class LogOutServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(LogOutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        logger.info("End session user - {}.", user.getName());

        req.getSession().invalidate();
        resp.sendRedirect("/main");
    }
}
