package dev.formation.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("<title>Servlet Hello</title>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		if(nom!=null && !nom.isEmpty() || prenom!=null && !prenom.isEmpty()) {
			resp.getWriter().write("<h1>Bonjour : " + prenom + " " + nom + "!</h1>");
		}else {
			resp.getWriter().write("<form action=\"http://localhost:8080/eshop-web/hi\" method=\"post\">");
			resp.getWriter().write("Nom : <input type=\"text\" name=\"nom\"/><br/>");
			resp.getWriter().write("Prénom <input type=\"text\" name=\"prenom\"/><br/>");
			resp.getWriter().write("<button type=\"submit\">Valider</button>");
		}
		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	

}
