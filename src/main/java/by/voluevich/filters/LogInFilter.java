package by.voluevich.filters;

import by.voluevich.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"CalculationServlet", "HistoryServlet"})
public class LogInFilter extends HttpFilter {
    Logger logger = LoggerFactory.getLogger(LogInFilter.class.getName());

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null || req.getSession() == null) {
            logger.warn("Attempted unauthorized access to the application!");
            res.sendRedirect("/main");
        } else {
            chain.doFilter(req, res);
        }
    }
}
