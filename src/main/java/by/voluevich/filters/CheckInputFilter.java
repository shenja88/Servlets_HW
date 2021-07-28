package by.voluevich.filters;

import by.voluevich.service.utils.CheckInput;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter(servletNames = "CalculationServlet")

public class CheckInputFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean check = true;
        String typeOp = req.getParameter("operation");

        if (CheckInput.isExistOperation(typeOp)) {
            String[] numStr = req.getParameterValues("num");
            for (String s : numStr) {
                if (!CheckInput.checkDouble(s)) {
                    check = false;
                }
            }
            if (!check) {
                res.getWriter().println(CheckInput.getInputMessage("Error! Enter numerical values."));
            }

            if(CheckInput.zeroDiv(Double.parseDouble(numStr[1]), typeOp)){
                check = false;
                res.getWriter().println(CheckInput.getInputMessage("Division by 0 is not possible"));
            }
        } else {
            check = false;
            res.getWriter().println(CheckInput.getInputMessage("Operation '" + typeOp + "' not found."));
        }

        if(check) {
            chain.doFilter(req, res);
        }
    }
}
