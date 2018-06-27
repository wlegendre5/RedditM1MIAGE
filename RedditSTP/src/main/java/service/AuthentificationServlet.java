package service;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class AuthentificationServlet extends HttpServlet {
	 @Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {

		  String currentUser = req.getParameter("userID");
		  HttpSession s = req.getSession();
		  s.setAttribute("currentUser", currentUser);
		  
		  resp.getWriter().println("<a href=\"reddit.html\">Authentification user " + s.getAttribute("currentUser") +"  successfull ! Go to Reddit </a>");	
		  
	  }
	 

}

