package by.voluevich.filters;

import by.voluevich.entity.User;
import by.voluevich.service.utils.CheckInput;

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
            res.getWriter().println(CheckInput.getInputMessage("You are not an authorized user."));
        } else {
            chain.doFilter(req, res);
        }
    }
}
