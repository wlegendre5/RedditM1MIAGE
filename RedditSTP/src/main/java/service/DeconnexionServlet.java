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
 * Servlet de deconnexion
 * Supprime la variable de session si elle existe
 * 
 */
@SuppressWarnings("serial")
public class DeconnexionServlet extends HttpServlet {
	 @Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {

		  HttpSession s = req.getSession();
		  s.invalidate();
		  this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		  //resp.getWriter().println("<a href=\"reddit.html\">Authentification user " + s.getAttribute("currentUser") +"  successfull ! Go to Reddit </a>");	  
	  }

}