package com.allen.website;



import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allen.website.DBBean.Message;
import com.google.appengine.api.datastore.KeyFactory;

public class MessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
            
	        if(action.equals("add"))
	        {
	            Message msg=new Message(username,mesg,new Date());
	            pm.makePersistent(msg);
	        }
	        else if(action.equals("delete"))
	        {
	        	String oid=req.getParameter("id"); 
	        	if(oid==null)
	        	{
	        		resp.getWriter().write("parameter id is not passed in");
	        		return;
	        	}
	        	long id=Long.valueOf(oid);
	        	pm.deletePersistent(pm.getObjectById(Message.class,KeyFactory.createKey(Message.class.getSimpleName(), id)));
	        	
	        }
            pm.close();
        	
            resp.sendRedirect("messageadmin.jsp");
	}

	
}
