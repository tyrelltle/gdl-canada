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
public class SignupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  	String username = req.getParameter("username");
	        String password=req.getParameter("password");
			HttpSession session=req.getSession();

	        PersistenceManager pm = PMF.get().getPersistenceManager();

	        // Search for any Movie object with the passed-in title; limit the number
	        // of results returned to 1 since there should be at most one movie with
	        // a given title
	        Query query = pm.newQuery(User.class, "username == un");
	        query.declareParameters("String un");
	        query.setRange(0, 1);

	       
            @SuppressWarnings("unchecked")
			List<User> results = (List<User>) query.execute(username);
            if (null==results||results.size()!=0) {
            	User usr=results.get(0);
            	session.setAttribute("username", usr.getUsername());
            	session.setAttribute("admin", usr.getSa());
                resp.sendRedirect("../gallaryAdmin.jsp");
                return;
            }
	        User user=new User(username,password);
	        //user.setSa(true);
	        user=pm.makePersistent(user);
	        pm.close();
            resp.getWriter().write("succeed");
	        
	}

	
}
