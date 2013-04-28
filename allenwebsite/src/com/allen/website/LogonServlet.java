package com.allen.website;



import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allen.website.DBBean.User;

/**
 * GET requests return the promotional image associated with the movie with the
 * title specified by the title query string parameter.
 */
public class LogonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			HttpSession session=req.getSession();
		  	String username = req.getParameter("username");
	        String password=req.getParameter("password");
	   
	        PersistenceManager pm = PMF.get().getPersistenceManager();

	        // Search for any Movie object with the passed-in title; limit the number
	        // of results returned to 1 since there should be at most one movie with
	        // a given title
	        Query query = pm.newQuery(User.class, "username == '"+username+"' && password == '"+password+"'");

	       
            @SuppressWarnings("unchecked")
			List<User> results = (List<User>) query.execute();
            if (null==results||results.size()!=0) {
                // If the results list is non-empty, return the first (and only)
                // result
            	User usr=results.get(0);
            	session.setAttribute("username", usr.getUsername());
            	session.setAttribute("admin", usr.getSa());
                resp.sendRedirect("../index.jsp");
                return;
            }

	        pm.close();
            resp.getWriter().write("user not exist");
	        
	}

	
}
