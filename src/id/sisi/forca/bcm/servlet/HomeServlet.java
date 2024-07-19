package id.sisi.forca.bcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.CLogger;

import id.sisi.forca.bcm.WebEnv;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971074686439239443L;
	private CLogger log = CLogger.getCLogger(HomeServlet.class);

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.severe("Get Handler ...");
		req.getRequestDispatcher(WebEnv.JSP_DIRECTORY + "home.jsp").forward(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.severe("Service Handler ...");
		super.service(req, resp);
	}

}
