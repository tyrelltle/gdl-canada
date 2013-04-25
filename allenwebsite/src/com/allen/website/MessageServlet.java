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

import com.allen.website.DBBean.Message;
import com.allen.website.DBBean.Picture;
import com.allen.website.DBBean.User;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class MessageServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			HttpSession session=req.getSession();
			PersistenceManager pm = PMF.get().getPersistenceManager();
		  	String username = (String) session.getAttribute("username");
		  	if(username==null || username.length()==0)
		  		username="unnamed user";
	        String mesg=req.getParameter("msg");
	        String action=req.getParameter("action");
            
            
            Message msg=new Message(username,mesg);
            pm.makePersistent(msg);
            pm.close();

            resp.sendRedirect("../messages/messageadmin.jsp");
             
	        
	        
	}

	
}
