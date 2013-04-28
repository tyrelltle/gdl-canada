package com.allen.website.bean;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class GallaryBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] ids=null;
	private String[] titles=null;
	private HttpServletRequest req=null;
	public void init(HttpServletRequest reqe) throws JSONException, IOException
	{
		req=reqe;
		JSONObject json;
	    InputStream is = new URL(req.getRequestURL()+"/../getimagelist").openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      json = new JSONObject(jsonText);
	      int len=json.length();
	      titles=new String[len];
	      ids=new String[len];
	      @SuppressWarnings("rawtypes")
		Iterator i=json.keys();
	      int arrind=0;
	      while(i.hasNext())
	      {
	    	  String ii=(String) i.next();
	    	  ids[arrind]=ii;
	    	  titles[arrind]=(String) json.getString(ii);
	    	  arrind++;
	      }
	       
	       
	    }
	    finally
	    {is.close();}
	    
	}
	private  String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}
	public String[] getIds()
	{
		return ids;
	}
	public String[] getTitles()
	{
		return titles;
	}
}
