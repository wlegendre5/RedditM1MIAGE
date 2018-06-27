package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class RedditServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		HttpSession s = req.getSession();
		resp.getWriter().println("coucou " + s.getAttribute("currentUser") + " jtm");	
		
	}
}