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

import com.allen.website.DBBean.Picture;
import com.allen.website.DBBean.User;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * GET requests return the promotional image associated with the movie with the
 * title specified by the title query string parameter.
 */
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();

		if(session.getAttribute("username")!=null)
			session.removeAttribute("username");
		if(session.getAttribute("admin")!=null)
			session.removeAttribute("admin");
		resp.sendRedirect("../index.jsp");
      
	   
            
	        
	}

	
}
