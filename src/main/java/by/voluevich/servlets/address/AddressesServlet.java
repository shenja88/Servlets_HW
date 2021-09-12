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

@WebServlet(name = "AddressesServlet", urlPatterns = "/addresses")
public class AddressesServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(AddressesServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Address> addressList = sessionFacade.getUserAddresses(user);
        req.setAttribute("address_list", addressList);
        logger.info("Request address list.");
        getServletContext().getRequestDispatcher("/addresses.jsp").forward(req, resp);
    }
}
