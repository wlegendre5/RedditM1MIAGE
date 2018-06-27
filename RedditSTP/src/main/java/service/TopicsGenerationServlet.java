package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class TopicsGenerationServlet  extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Start topics generation");
		
		Random r=new Random();
		resp.getWriter().println("creating Topics");


		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		int max=50;
		for (int i = 0; i < max; i++) {
			Entity msg = new Entity("Topic", "t" + i);
			int r_tmp = r.nextInt(100);
			msg.setProperty("sender", "u" + r_tmp);
			msg.setProperty("id", "t" + i);
			msg.setProperty("title", "wrote by "+"u" + r_tmp);
			ArrayList<String> voters = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				String Suser = "u"+r.nextInt(max+1);
				if(!voters.contains(Suser)){
					voters.add(Suser);
				}
			}
			
			msg.setProperty("body", "");
			msg.setProperty("voters", voters);
			msg.setProperty("karma", r.nextInt(100));
			msg.setProperty("lien", "");
			datastore.put(msg);
		}
		resp.getWriter().println("done");
		
		
	}
		
		
}
