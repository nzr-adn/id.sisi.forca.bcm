package id.sisi.forca.bcm.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.WebUser;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

	private CLogger log = CLogger.getCLogger(AuthFilter.class);

	private static final String[] loginRequiredURLs = { "/home", "/profile", "/dashboard" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.severe("AuthFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.severe("Auth Filter ...");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String loginURI = req.getContextPath() + "/login";
		String registerURI = req.getContextPath() + "/register";
		boolean isLoggedIn = session != null && session.getAttribute(WebUser.NAME) != null;
		boolean isLoginRegisterRequest = req.getRequestURI().equals(loginURI)
				|| req.getRequestURI().equals(registerURI);

		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);

		if (isLoggedIn && isLoginRegisterRequest) {
			res.sendRedirect("home");
		} else if (!isLoggedIn && isLoginRequired(req)) {
			log.severe("Unauthorized access request");
			res.sendRedirect("login");
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isLoginRequired(HttpServletRequest req) {
		String requestURL = req.getRequestURL().toString();

		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}
}
