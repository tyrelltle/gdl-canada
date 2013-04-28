package com.allen.website;
import java.io.IOException;
import javax.servlet.http.*;
//http://localhost:8888/_ah/admin
@SuppressWarnings("serial")
public class AllenwebsiteServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		//resp.sendRedirect("index.html");
	}
}
