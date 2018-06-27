package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class GetTopVotesServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("TOP 10");

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("Topic").addSort("karma", Query.SortDirection.DESCENDING);
		 PreparedQuery pq= ds.prepare(q);
		 List<Entity> results=pq.asList(FetchOptions.Builder.withLimit(10));
		for (Entity r : results) {
			resp.getWriter().println("<li>:"+r);
		}

		resp.getWriter().println("finished");	
		
	}
}
