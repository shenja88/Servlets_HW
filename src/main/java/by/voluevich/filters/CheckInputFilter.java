package by.voluevich.filters;

import by.voluevich.service.CheckInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "CalculationServlet")
public class CheckInputFilter extends HttpFilter {
    Logger logger = LoggerFactory.getLogger(CheckInputFilter.class.getName());

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean check = true;
        if (req.getMethod().equals("POST")) {
            String typeOp = req.getParameter("operation");
            String[] numStr = req.getParameterValues("num");
            for (String s : numStr) {
                if (!CheckInput.checkDouble(s)) {
                    check = false;
                }
            }
            if (!check) {
                req.setAttribute("message_inp", "Error! Enter numerical values.");
                getServletContext().getRequestDispatcher("/calc.jsp").forward(req, res);
            }

            if (CheckInput.zeroDiv(Double.parseDouble(numStr[1]), typeOp)) {
                check = false;
                req.setAttribute("message_inp", "Division by 0 is not possible");
                logger.info("Attempting to divide by 0");
                getServletContext().getRequestDispatcher("/calc.jsp").forward(req, res);
            }
        }
        if (check) {
            chain.doFilter(req, res);
        }
    }
}
