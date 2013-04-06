package com.allen.website;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allen.website.gallary.Picture;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
@SuppressWarnings("serial")
public class GetImageInfoServlet extends HttpServlet {

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
        // Search for any Movie object with the passed-in title; limit the number
        // of results returned to 1 since there should be at most one movie with
        // a given title
        Query query = pm.newQuery(Picture.class);

       
       List<Picture> results = (List<Picture>) query.execute();
       JSONObject json=new JSONObject();
       try {
    	  
    	Iterator<Picture> i=results.iterator();
    	while(i.hasNext())
		{
    		Picture p=(Picture) i.next();
    		json.put(p.getId().toString(),p.getTitle());//p.getTitle());
		
		}
    	
    	resp.setContentType("application/json");
        resp.getWriter().write(json.toString());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
	}
}