package com.allen.website.bean;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.allen.website.PMF;
import com.allen.website.DBBean.Message;
import com.allen.website.DBBean.Picture;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class MessageBean implements java.io.Serializable{

	public List<Message> msgs=null;

	
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
