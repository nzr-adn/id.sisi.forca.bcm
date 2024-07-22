package id.sisi.forca.bcm.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.WebUser;

@WebFilter(urlPatterns = { "/home", "/profile", "/dashboard" })
public class AuthFilter implements Filter {

	private CLogger log = CLogger.getCLogger(AuthFilter.class);
	private ServletContext context;


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		log.severe("AuthFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.severe("Auth Filter ...");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		

		if (session != null && session.getAttribute(WebUser.NAME) != null) {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            res.setHeader("Pragma", "no-cache");
            res.setDateHeader("Expires", 0);
			chain.doFilter(request, response);
		} else {
			log.severe("Unauthorized access request");
			res.sendRedirect("login");
		}
	}
}
