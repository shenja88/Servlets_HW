package by.voluevich.servlets.address;

import by.voluevich.dao.InMemoryAddressDaoImpl;
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

@WebServlet(name = "RemoveAddressServlet", urlPatterns = "/removeAddr")
public class RemoveAddressServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(RemoveAddressServlet.class.getName());
    private final SessionFacade sessionFacade = new SessionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        User user = (User) req.getSession().getAttribute("user");
        if(sessionFacade.removeAddress(id, user)){
            req.setAttribute("message_addr", "Address was delete successfully!");
            logger.info("Delete address for user: {}", user.getName());
        }else{
            req.setAttribute("message_addr", "Operation was not successful!");
        }
        List<Address> addressList = sessionFacade.getUserAddresses(user);
        req.setAttribute("address_list", addressList);
        getServletContext().getRequestDispatcher("/addresses.jsp").forward(req, resp);
    }
}
