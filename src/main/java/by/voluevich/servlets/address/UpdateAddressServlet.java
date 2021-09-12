package by.voluevich.servlets.address;

import by.voluevich.entity.Address;
import by.voluevich.entity.User;
import by.voluevich.service.SessionFacade;
import by.voluevich.servlets.telephone.UpdateTelephoneServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateAddressServlet", urlPatterns = "/updateAddr")
public class UpdateAddressServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UpdateTelephoneServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();
    private int idForUpd = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idForUpd = Integer.parseInt(req.getParameter("id"));
        getServletContext().getRequestDispatcher("/update_address.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int numHome = Integer.parseInt(req.getParameter("numHome"));

        User user = (User) req.getSession().getAttribute("user");

        if(sessionFacade.updateAddress(new Address(idForUpd, city, street, numHome, user))){
            req.setAttribute("message_addr", "Address was update successfully!");
            logger.info("Update address for user: {}", user.getName());
        }else{
            req.setAttribute("message_addr", "Operation was not successful!");
        }
        List<Address> addressList = sessionFacade.getUserAddresses(user);
        req.setAttribute("address_list", addressList);
        getServletContext().getRequestDispatcher("/addresses.jsp").forward(req, resp);
    }
}
