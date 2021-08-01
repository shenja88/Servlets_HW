package by.voluevich.filters;

import by.voluevich.entity.User;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"CalculationServlet", "HistoryServlet"})
public class LogInFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null || req.getSession() == null) {
            getServletContext().getRequestDispatcher("/RespAccessError.jsp").forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
}
