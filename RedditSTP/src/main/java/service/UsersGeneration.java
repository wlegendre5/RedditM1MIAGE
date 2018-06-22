package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import entity.UserEntity;

public class UsersGeneration {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Start topics generation");
		
		Random r=new Random();
		resp.getWriter().println("creating topics");
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

		int max = 400;
		int karma = 0;
		ArrayList<Entity> listUsers = new ArrayList<>();
		for (int i = 1; i < max; i++) {

		}

		
}
