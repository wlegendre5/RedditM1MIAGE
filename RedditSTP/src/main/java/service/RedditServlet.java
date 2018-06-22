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
		resp.getWriter().println("Hello, world");
		
		Random r=new Random();
		resp.getWriter().println("creating friends");


		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		int max=250;
		for (int i = 0; i < max; i++) {
			Entity msg = new Entity("rmsg", "rm" + i);
			msg.setProperty("sender", "u" + r.nextInt(100));
			ArrayList<String> voters = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				String Suser = "u"+r.nextInt(max+1);
				if(!voters.contains(Suser)){
					voters.add(Suser);
				}
			}
			msg.setProperty("voters", voters);
			datastore.put(msg);
		}
		resp.getWriter().println("done");
		
		
		
		
	}
}