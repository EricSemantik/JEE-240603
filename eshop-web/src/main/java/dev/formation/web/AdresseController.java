package dev.formation.web;

import java.io.IOException;
import java.util.List;

import dev.formation.Application;
import dev.formation.model.Adresse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adresse")
public class AdresseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mode = request.getParameter("mode") != null ? request.getParameter("mode") : "list";

		// ETAPE 1 : Réception de la Request
		if (mode.contentEquals("list")) {
			list(request, response);
		} else if (mode.contentEquals("add")) {
			add(request, response);
		} else if (mode.contentEquals("edit")) {
			edit(request, response);
		} else if (mode.contentEquals("save")) {
			save(request, response);
		} else if (mode.contentEquals("cancel")) {
			cancel(request, response);
		} else if (mode.contentEquals("remove")) {
			remove(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ETAPE 2 : Récupérer les données
		List<Adresse> adresses = Application.getInstance().getAdresseRepository().findAll();
		
		// ETAPE 3 : Renseigner le Model
		request.setAttribute("mesAdresses", adresses);
		
		// ETAPE 4 : Appel de la View
		getServletContext().getRequestDispatcher("/WEB-INF/views/adresse/list.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {

	}

	private void save(HttpServletRequest request, HttpServletResponse response) {

	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) {

	}

	private void remove(HttpServletRequest request, HttpServletResponse response) {

	}
}
