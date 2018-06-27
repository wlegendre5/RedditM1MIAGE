package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class GetVotedTopicsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession s = req.getSession();
		Object cu = s.getAttribute("currentUser");
		String user = String.valueOf(cu);
		
		resp.getWriter().println("Topics sur lequels "+ user +" a voté");
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		/*
		Filter propertyFilter = new FilterPredicate("voters", FilterOperator.IN, user);
		Query q = new Query("Topic").setFilter(propertyFilter);*/
		
		Query q = new Query("Topic");
		PreparedQuery pq= ds.prepare(q);
	    List<Entity> results=pq.asList(FetchOptions.Builder.withLimit(1000));
		for (Entity r : results) {
			ArrayList<String> votes = (ArrayList<String>) r.getProperty("voters");
			if (votes.contains(user)) {
				resp.getWriter().println("<br>Sender : " + r.getProperty("sender")+"</br>");
				resp.getWriter().println("<br>Id : " + r.getProperty("id")+"</br>");
				resp.getWriter().println("<br>Title : " + r.getProperty("title")+"</br>");
				resp.getWriter().println("<br>Body : " + r.getProperty("body")+"</br>");
				resp.getWriter().println("<br>Voters : " + r.getProperty("voters")+"</br>");
				resp.getWriter().println("<br>Karma : " + r.getProperty("karma")+"</br>");
				resp.getWriter().println("<br>_________________________________</br>");
			}
		}
		
	}
}
