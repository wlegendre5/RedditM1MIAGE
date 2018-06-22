package service;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AuthentificationServlet extends HttpServlet {
	 @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {

	    PrintWriter out = resp.getWriter();

	    resp.getWriter().println(
	        "Article with the title: " + req.getParameter("login") + " with password "
	            + req.getParameter("passwd"));
	    
	    resp.getWriter().println("coucou");
	  }
	 

}

