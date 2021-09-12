package by.voluevich.servlets.address;

import by.voluevich.entity.Address;
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

@WebServlet(name = "AddAddressServlet", urlPatterns = "/addAddress")
public class AddAddressServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(AddAddressServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/save_address.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int numHome = Integer.parseInt(req.getParameter("numHome"));

        User user = (User) req.getSession().getAttribute("user");
        if(sessionFacade.addAddress(new Address(false, city, street, numHome, user))){
            req.setAttribute("message_addr", "Address was added successfully!");
            logger.info("Adding address for user: {}", user.getName());
        }else{
            req.setAttribute("message_addr", "Operation was not successful!");
        }
        List<Address> addressList = sessionFacade.getUserAddresses(user);
        req.setAttribute("address_list", addressList);
        getServletContext().getRequestDispatcher("/addresses.jsp").forward(req, resp);
    }
}
