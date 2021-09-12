package by.voluevich.servlets.telephone;

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
import java.util.List;

@WebServlet(name = "TelephonesServlet", urlPatterns = "/telephones")
public class TelephonesServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UpdateTelephoneServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Telephone> telephones = sessionFacade.getUserTelephones(user);
        req.setAttribute("telephones_list", telephones);
        logger.info("Request telephones list.");
        getServletContext().getRequestDispatcher("/telephones.jsp").forward(req, resp);
    }
}
