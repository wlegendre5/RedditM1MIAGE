package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import entity.TopicEntity;
import entity.UserEntity;

public class TopicsGeneration {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Start topics generation");
		
		Random r=new Random();
		resp.getWriter().println("creating topics");
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		int max = 400;
		ArrayList<Entity> listUsers = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			
			ArrayList<Integer> listVoters = new ArrayList<>();
		
		}
		
	}
		
		
}
