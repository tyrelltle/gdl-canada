package com.allen.website.bean;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.allen.website.PMF;
import com.allen.website.DBBean.Message;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class MessageBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<Message> msgs=null;

	
	@SuppressWarnings("unchecked")
	public void init() throws JSONException, IOException
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
        // Search for any Movie object with the passed-in title; limit the number
        // of results returned to 1 since there should be at most one movie with
        // a given title
        Query query = pm.newQuery(Message.class);

       
        msgs = (List<Message>) query.execute();
        
    	pm.close();
	    
	}
	
}
