package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/*
 * Servlet de fonctionnalité : Récupération des 1000 topics les mieux notés
 * 
 */
public class GetTopTopicsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		long startTime = System.currentTimeMillis();
		
		resp.getWriter().println("TOP 1000 TOPICS ORDERED BY KARMA");

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("Topic").addSort("karma", Query.SortDirection.DESCENDING);
	    PreparedQuery pq= ds.prepare(q);
	    List<Entity> results=pq.asList(FetchOptions.Builder.withLimit(1000));
		
	    for (Entity r : results) {
			resp.getWriter().println("<li>:"+r);
		}		
	    
	    long endTime = System.currentTimeMillis();
		resp.getWriter().println("Done in " + (endTime - startTime)+ " milliseconds");	
	    
	}
}
