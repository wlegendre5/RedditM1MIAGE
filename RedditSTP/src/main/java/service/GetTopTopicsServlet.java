package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class GetTopTopicsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("TOP 10");

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		//Filter propertyFilter =
		//	    new FilterPredicate("karma", FilterOperator.GREATER_THAN_OR_EQUAL, 90);
		//Query q = new Query("Topic").setFilter(propertyFilter).addSort("karma", Query.SortDirection.DESCENDING);
		Query q = new Query("Topic").addSort("karma", Query.SortDirection.DESCENDING);
		 PreparedQuery pq= ds.prepare(q);
		 List<Entity> results=pq.asList(FetchOptions.Builder.withLimit(10));
		for (Entity r : results) {
			resp.getWriter().println("<li>:"+r);
		}

		resp.getWriter().println("finished");	
		
	}
}
