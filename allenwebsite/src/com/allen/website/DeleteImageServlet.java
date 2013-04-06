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
public class DeleteImageServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String title = req.getParameter("title");
        Picture pic = delPic(title);

     
        
	     resp.sendRedirect(req.getHeader("referer"));

    }
    
    /**
     * Queries the datastore for the Movie object with the passed-in title. If
     * found, returns the Movie object; otherwise, returns null.
     *
     * @param title movie title to look up
     */
    private Picture delPic(String title) {
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
                Picture pic= results.get(0);
                pm.deletePersistent(pic);
            }
        	
        } finally {
            query.closeAll();
            pm.close();
        }
		return null;
    }
}
