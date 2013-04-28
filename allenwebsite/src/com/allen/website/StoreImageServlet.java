package com.allen.website;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.allen.website.DBBean.Picture;
@SuppressWarnings("serial")
public class StoreImageServlet extends HttpServlet {
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		InputStream in = null;
		try 
		{
		       ServletFileUpload.isMultipartContent(req);
	           ServletFileUpload upload = new ServletFileUpload();
	           FileItemIterator iter = upload.getItemIterator(req);
	           while(iter.hasNext()) 
	           {
	               FileItemStream item = iter.next();
	               item.getFieldName();
	               item.getName();
	               item.openStream();
	               if(item.isFormField()) 
	               {
	                   // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	               } else 
	               {
	            	   store(item,req);
	               }
	           }
		     
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(in!=null)
			{
				in.close();
			}
			
		
		}
		//HttpSession session = req.getSession(true);
		//session.setAttribute("postback", true);
	     resp.sendRedirect(req.getHeader("referer"));

    }
	
	private void store(FileItemStream item,HttpServletRequest req) throws IOException
	{
		InputStream in=item.openStream();
		byte dataBytes[]=new byte[req.getContentLength()]; 
		int nread=0;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		
		while(-1!=(nread=in.read(dataBytes, 0, req.getContentLength())))
		{
			buffer.write(dataBytes, 0, nread);
		}
		dataBytes=buffer.toByteArray();
		//store
		Picture movie = new Picture();
	     movie.setTitle(item.getName());
	     movie.setImageType(req.getContentType());
	
	     // Set the movie's promotional image by passing in the bytes pulled
	     // from the image fetched via the URL Fetch service
	     movie.setImage(dataBytes);
	
	     //...
	
	     PersistenceManager pm = PMF.get().getPersistenceManager();
	     try {
	         // Store the image in App Engine's datastore
	         pm.makePersistent(movie);
	     } finally { 
	         pm.close();
	     }
	     
	}
/*
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        URLFetchService fetchService =
            URLFetchServiceFactory.getURLFetchService();

        // Fetch the image at the location given by the url query string parameter
        HTTPResponse fetchResponse = fetchService.fetch(new URL(
                req.getParameter("url")));
        
        String fetchResponseContentType = null;
        for (HTTPHeader header : fetchResponse.getHeaders()) {
            // For each request header, check whether the name equals
            // "Content-Type"; if so, store the value of this header
            // in a member variable
            if (header.getName().equalsIgnoreCase("content-type")) {
                fetchResponseContentType = header.getValue();
                break;
            }
        }

        if (fetchResponseContentType != null) {
            // Create a new Movie instance
            Picture movie = new Picture();
            movie.setTitle(req.getParameter("title"));
            movie.setImageType(fetchResponseContentType);

            // Set the movie's promotional image by passing in the bytes pulled
            // from the image fetched via the URL Fetch service
            movie.setImage(fetchResponse.getContent());

            //...

            PersistenceManager pm = PMF.get().getPersistenceManager();
            try {
                // Store the image in App Engine's datastore
                pm.makePersistent(movie);
            } finally { 
                pm.close();
            }
        }
        else
        {
        	resp.getOutputStream().write(new String("error: no content type").getBytes());
        }
    }
	
	*/
	
}
