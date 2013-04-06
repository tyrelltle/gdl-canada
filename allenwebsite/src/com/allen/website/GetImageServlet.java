package com.allen.website;



import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allen.website.gallary.Picture;

/**
 * GET requests return the promotional image associated with the movie with the
 * title specified by the title query string parameter.
 */
public class GetImageServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String title = req.getParameter("title");
        Picture pic = getPic(title);

        if (pic != null && pic.getImageType() != null &&
        		pic.getImage() != null) {
            // Set the appropriate Content-Type header and write the raw bytes
            // to the response's output stream
            resp.setContentType(pic.getImageType());
            resp.getOutputStream().write(pic.getImage());
        } else {
            // If no image is found with the given title, redirect the user to
            // a static image
            resp.sendRedirect("/static/noimage.jpg");
        }
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
