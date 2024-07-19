package id.sisi.forca.bcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import id.sisi.forca.bcm.WebEnv;


@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5748802576944234226L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(WebEnv.JSP_DIRECTORY + "profile.html").forward(req, resp);
	}

}
