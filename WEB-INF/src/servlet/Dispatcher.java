package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		boolean isSecure = request.isSecure();
		String snippet = "http://www.";
		if (isSecure) {
			snippet = "https://www.";
		}

		String url = request.getRequestURL().toString();
		if (url.startsWith(snippet)) {
			url = url.substring(snippet.length());
			if (isSecure) {
				url = "https://" + url;
			} else {
				url = "http://" + url;
			}
			String queryString = request.getQueryString();
			if (queryString != null && !queryString.isEmpty()) {
				url = url + "?" + queryString;
			}
			// System.out.println(url);
			response.sendRedirect(url);
		}

	}

}
