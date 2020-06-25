package kr.or.connect.naverreservation.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

@WebFilter(urlPatterns = "/*")
public class CookieAttributeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Filter is running");
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Collection<String> headers = httpServletResponse.getHeaders(HttpHeaders.SET_COOKIE);
		for(String header:headers) {
			System.out.println(header);
		}
		chain.doFilter(request, response);
		addSameSite(httpServletResponse, "None");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	private void addSameSite(HttpServletResponse response, String sameSite) {
		Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		boolean firstHeader = true;
		
		for(String header : headers) {
			System.out.println(header);
			if(firstHeader) {
				response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s;", header, "Same-Site=", sameSite));
				firstHeader = false;
				continue;
			}
			response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s;", header, "Same-Site=", sameSite));
		}
	}

}
