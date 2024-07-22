package id.sisi.forca.bcm.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.Env;
import org.compiere.util.WebUser;

import id.sisi.forca.bcm.JSPEnv;
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

		req.getRequestDispatcher(JSPEnv.JSP_DIRECTORY + "login.jsp").forward(req, resp);
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
		Properties ctx = JSPEnv.getCtx(req);
		WebUser w_user = WebUser.get(ctx, email, password, false);
		HttpSession session;
		if (w_user != null && w_user.isPasswordOK()) {
			session = req.getSession();
			session.setAttribute(WebUser.NAME, w_user);
			Env.setContext(ctx, Env.AD_USER_ID, w_user.getAD_User_ID());
			return true;
		}
		return false;
	}

}
