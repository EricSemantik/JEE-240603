package dev.formation.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dev.formation.model.Civilite;
import dev.formation.model.Client;
import dev.formation.model.Personne;
import dev.formation.repository.IPersonneRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/client")
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private IPersonneRepository personneRepository;

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
		List<Client> clients = personneRepository.findAllClient();

		// ETAPE 3 : Renseigner le Model
		request.setAttribute("clients", clients);

		// ETAPE 4 : Appel de la View
		getServletContext().getRequestDispatcher("/WEB-INF/views/client/list.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("civilites", Civilite.values());
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/client/form.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) : null;

		Optional<Personne> optPersonne = personneRepository.findById(id);

		if (optPersonne.isPresent() && optPersonne.get() instanceof Client) {
			request.setAttribute("client", optPersonne.get());
		}
		
		request.setAttribute("civilites", Civilite.values());

		getServletContext().getRequestDispatcher("/WEB-INF/views/client/form.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
				? Long.valueOf(request.getParameter("id"))
				: null;
		Civilite civilite = !request.getParameter("civilite").isEmpty()
				? Civilite.valueOf(request.getParameter("civilite"))
				: null;
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		LocalDate dtNaissance = !request.getParameter("dtNaissance").isEmpty()
				? LocalDate.parse(request.getParameter("dtNaissance"))
				: null;

		Client client = new Client();

		if (id != null) {
			Optional<Personne> optPersonne = personneRepository.findById(id);

			if (optPersonne.isPresent() && optPersonne.get() instanceof Client) {
				client = (Client) optPersonne.get();
			}
		}

		client.setCivilite(civilite);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setEmail(email);
		client.setDtNaissance(dtNaissance);

		personneRepository.save(client);

		getServletContext().getRequestDispatcher("/client?mode=list").forward(request, response);
	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/client?mode=list").forward(request, response);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) : null;

		if (id != null) {
			personneRepository.deleteById(id);
		}

		response.sendRedirect("client");
	}
}
