package insite.shiftmanager.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author rjaywant
 * 
 */
public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.getRequestDispatcher("/WEB-INF/pages" + url.substring(0, url.lastIndexOf(".")) + ".jsp").forward(req, resp);
    }

}
