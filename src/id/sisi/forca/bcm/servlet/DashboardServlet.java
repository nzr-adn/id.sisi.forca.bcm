package id.sisi.forca.bcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import id.sisi.forca.bcm.JSPEnv;


@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet{
	

	private static final long serialVersionUID = 7697107304783090421L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JSPEnv.JSP_DIRECTORY + "dashboard.html").forward(req, resp);
	}

}
