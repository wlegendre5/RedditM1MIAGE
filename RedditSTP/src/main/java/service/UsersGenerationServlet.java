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

import entity.UserEntity;

public class UsersGenerationServlet  extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Start users generation");
		
		Random r=new Random();
		resp.getWriter().println("creating Users");


		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		int max=10;
		for (int i = 0; i < max; i++) {
			Entity msg = new Entity("User", "u" + i);
			int r_tmp = r.nextInt(100);
			msg.setProperty("name", "u" + Integer.toString(i));
			msg.setProperty("id", "u" + i);
			msg.setProperty("login", "u" + i);
			msg.setProperty("passwd", "123");
			msg.setProperty("mail", "");
			ArrayList<String> voters = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				String Suser = "u"+r.nextInt(max+1);
				if(!voters.contains(Suser)){
					voters.add(Suser);
				}
			}
			msg.setProperty("karma", 0);
			msg.setProperty("lien", "");
			datastore.put(msg);
		}
		resp.getWriter().println("done");
		
		
	}
}