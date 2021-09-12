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

@WebServlet(name = "AddTelephoneServlet", urlPatterns = "/saveTelephone")
public class AddTelephoneServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(AddTelephoneServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/save_telephone.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String telephone = req.getParameter("telephone");

        User user = (User) req.getSession().getAttribute("user");

        if (sessionFacade.addTelephone(new Telephone(false, telephone, user))) {
            req.setAttribute("message_tel", "Phone was added successfully!");
            logger.info("Adding phone for user: {}", user.getName());
        } else {
            req.setAttribute("message_tel", "Operation was not successful!");
        }
        List<Telephone> telephones = sessionFacade.getUserTelephones(user);
        req.setAttribute("telephones_list", telephones);
        getServletContext().getRequestDispatcher("/telephones.jsp").forward(req, resp);
    }
}
