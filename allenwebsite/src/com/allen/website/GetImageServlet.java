package com.allen.website;



import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allen.website.DBBean.Picture;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * GET requests return the promotional image associated with the movie with the
 * title specified by the title query string parameter.
 */
public class GetImageServlet extends HttpServlet {

	public static final int thumsize=200;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String title = req.getParameter("title");
        String isthum=req.getParameter("isthum");
        Picture pic = getPic(title);
        
        if (pic != null && pic.getImageType() != null &&
        		pic.getImage() != null) {
        	byte imgbyte[]=pic.getImage();
        	//scale image to thumb if required
        	if(isthum!=null)
        		imgbyte=scaleImage(imgbyte);
            // Set the appropriate Content-Type header and write the raw bytes
            // to the response's output stream
            resp.setContentType(pic.getImageType());
            resp.getOutputStream().write(imgbyte);
        } else {
            // If no image is found with the given title, redirect the user to
            // a static image
            resp.sendRedirect("/static/noimage.jpg");
        }
    }
    
    private byte[] scaleImage(byte[] imgbyte) {
    	 ImagesService imagesService = ImagesServiceFactory.getImagesService();

         Image oldImage = ImagesServiceFactory.makeImage(imgbyte);
         Transform resize = ImagesServiceFactory.makeResize(thumsize, thumsize);

         Image newImage = imagesService.applyTransform(resize, oldImage);
		return newImage.getImageData();
	}

	/**
     * Queries the datastore for the Movie object with the passed-in title. If
     * found, returns the Movie object; otherwise, returns null.
     *
     * @param title movie title to look up
     */
    private Picture getPic(String title) {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        // Search for any Movie object with the passed-in title; limit the number
        // of results returned to 1 since there should be at most one movie with
        // a given title
        Query query = pm.newQuery(Picture.class, "title == titleParam");
        query.declareParameters("String titleParam");
        query.setRange(0, 1);

        try {
            List<Picture> results = (List<Picture>) query.execute(title);
            if (results.iterator().hasNext()) {
                // If the results list is non-empty, return the first (and only)
                // result
                return results.get(0);
            }
        	
        } finally {
            query.closeAll();
            pm.close();
        }
		return null;
    }
}
