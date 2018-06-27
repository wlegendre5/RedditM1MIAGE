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
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class UpvoteServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//parametre dans l'URL
		if (req.getParameter("idMSG") != null) {
			Filter keyFilter = new FilterPredicate("id", FilterOperator.EQUAL, req.getParameter("idMSG"));
			Query q = new Query("Topic").setFilter(keyFilter);
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults().limit(1));
			
			Entity result = results.get(0);
			int karma = Integer.parseInt(result.getProperty("karma").toString());
			result.setProperty("karma", karma+1);
            datastore.put(result);
		}
		
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
