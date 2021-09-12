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

@WebServlet(name = "RemoveTelephoneServlet", urlPatterns = "/removeTel" )
public class RemoveTelephoneServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(RemoveTelephoneServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        User user = (User) req.getSession().getAttribute("user");
        if(sessionFacade.removeTelephone(id, user)){
            req.setAttribute("message_tel", "Telephone was delete successfully!");
            logger.info("Delete telephone for user: {}", user.getName());
        }else{
            req.setAttribute("message_tel", "Operation was not successful!");
        }
        List<Telephone> telephones = sessionFacade.getUserTelephones(user);
        req.setAttribute("telephones_list", telephones);
        getServletContext().getRequestDispatcher("/telephones.jsp").forward(req, resp);
    }
}
