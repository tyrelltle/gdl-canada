package com.allen.website.bean;


import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import com.allen.website.PMF;
import com.allen.website.DBBean.Message;
import com.google.appengine.labs.repackaged.org.json.JSONException;
/*
 * request parameter:
 * long cursor
 * long numpage
 * 
 * */
public class MessageBean implements java.io.Serializable{
	public final int numPostPerPage=5;
	public long numPages=0;
	public long start=0;
	public long end=0;
	public long curpage=0;
	private HttpServletRequest req=null;
	private static final long serialVersionUID = 1L;
	public List<Message> msgs=null;

	
	@SuppressWarnings("unchecked")
	public void init(HttpServletRequest r) throws JSONException, IOException
	{
		this.req=r;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Message.class);

		if(!requestHasParam())
		{
			query.setOrdering("date desc");
	        query.setResult("count(this)");
	        this.numPages=((Long) query.execute()).longValue();
	        this.numPages=(this.numPages/this.numPostPerPage)+1;
	        this.start=0;
	        this.end=this.numPostPerPage;
		}	
		
        query = pm.newQuery(Message.class);
        query.setOrdering("date desc");
        query.setRange(start,end);
        msgs = (List<Message>) query.execute();
    	pm.close();
    	
	    
	}
	
	private boolean requestHasParam() {

		String start,end,numPage,curpage=null;
		start=this.req.getParameter("start");
		end=this.req.getParameter("end");
		numPage=this.req.getParameter("numpage");
		curpage=this.req.getParameter("curpage");
		if(start==null||end==null||numPage==null||curpage==null)
			return false;
		
		this.numPages=Long.valueOf(numPage);
		this.start=Long.valueOf(start);
		this.end=Long.valueOf(end);
		this.curpage=Long.valueOf(curpage);
		return true;
	
	}



	
	
}
