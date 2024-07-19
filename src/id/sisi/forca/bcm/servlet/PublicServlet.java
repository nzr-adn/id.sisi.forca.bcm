package id.sisi.forca.bcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import id.sisi.forca.bcm.WebEnv;

@WebServlet(urlPatterns = "/public")
public class PublicServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3049235024658254404L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(WebEnv.JSP_DIRECTORY + "public.jsp").forward(req, resp);
	}

}
