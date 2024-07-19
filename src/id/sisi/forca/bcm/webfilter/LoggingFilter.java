package id.sisi.forca.bcm.webfilter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.compiere.util.CLogger;

@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class LoggingFilter implements Filter {

	private CLogger log = CLogger.getCLogger(LoggingFilter.class);
	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("LoggingFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String method = ((HttpServletRequest) request).getMethod();
		String url = ((HttpServletRequest) request).getRequestURI();
		log.severe("Logging Filter ...\t" + method + "\t:\t" + url);
		chain.doFilter(request, response);
	}

}
