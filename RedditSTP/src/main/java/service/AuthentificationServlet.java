package service;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/*
 * Servlet d'authentification
 * Une variable de session contenant l'ID de l'user est initialisée
 * 
 */
@SuppressWarnings("serial")
public class AuthentificationServlet extends HttpServlet {
	 @Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {

		  String currentUser = req.getParameter("userID");
		  HttpSession s = req.getSession();
		  s.setAttribute("currentUser", currentUser);
		  this.getServletContext().getRequestDispatcher("/WEB-INF/reddit.jsp").forward(req, resp);
		  //resp.getWriter().println("<a href=\"reddit.html\">Authentification user " + s.getAttribute("currentUser") +"  successfull ! Go to Reddit </a>");	  
	  }
	 /*
	  * Commentaire à destination de l'enseignant :
	  * 
	  * Pour être honnête avec vous nous ne sommes pas satisfaits de l'implémentation de l'authentification qui a été faite
	  * Il aurait été préférable de se baser sur le mécanisme d'authentification via un compte Google plutôt que sur une simple variable de session
	  * 
	  */

}

