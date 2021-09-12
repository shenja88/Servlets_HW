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

@WebServlet(name = "UpdateTelephoneServlet", urlPatterns = "/updateTel")
public class UpdateTelephoneServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UpdateTelephoneServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();
    private int idForUpd = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idForUpd = Integer.parseInt(req.getParameter("id"));
        getServletContext().getRequestDispatcher("/update_telephone.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String telephone = req.getParameter("telephone");

        User user = (User) req.getSession().getAttribute("user");
        if(sessionFacade.updateTelephone(new Telephone(idForUpd, telephone, user))){
            req.setAttribute("message_tel", "Phone was update successfully!");
            logger.info("Update telephone for user: {}", user.getName());
        } else {
            req.setAttribute("message_tel", "Operation was not successful!");
        }
        List<Telephone> telephones = sessionFacade.getUserTelephones(user);
        req.setAttribute("telephones_list", telephones);
        getServletContext().getRequestDispatcher("/telephones.jsp").forward(req, resp);
    }
}
