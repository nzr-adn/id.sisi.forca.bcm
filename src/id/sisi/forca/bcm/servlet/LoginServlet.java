package id.sisi.forca.bcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import id.sisi.forca.bcm.WebEnv;
import id.sisi.forca.bcm.WebUtil;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8986734470240034427L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(WebEnv.JSP_DIRECTORY + "login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = WebUtil.getParameter(req, "email");
		String password = WebUtil.getParameter(req, "password");
		if (isLogin(req, email, password)) {
			resp.sendRedirect("home");
		} else {
			resp.sendRedirect("login");
		}
	}

	private boolean isLogin(HttpServletRequest req, String email, String password) {
		HttpSession session;
		if (email.equals("nizar.adian.n@gmail.com") && password.equals("123456789")) {
			session = req.getSession(true);
			session.setAttribute("AD_User_ID", email);
			return true;
		}
		return false;
	}

}
