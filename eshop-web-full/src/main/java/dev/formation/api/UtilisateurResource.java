package dev.formation.api;

import java.net.URI;
import java.util.Optional;

import dev.formation.exception.EShopException;
import dev.formation.model.Utilisateur;
import dev.formation.repository.IUtilisateurRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("/utilisateur")
public class UtilisateurResource {

	@Inject
	private IUtilisateurRepository utilisateurRepository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAll() {
		return Response.ok(utilisateurRepository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		Optional<Utilisateur> optUtilisateur = utilisateurRepository.findById(id);

		if (optUtilisateur.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(optUtilisateur.get()).build();
	}

	@GET
	@Path("/searchByIdentifiant")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByIdentifiant(@QueryParam("identifiant") String identifiant) {
		if (identifiant.isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.ok(utilisateurRepository.findByIdentifiant(identifiant)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Utilisateur utilisateur, @Context UriInfo uriInfo) {
		try {
			utilisateur = utilisateurRepository.save(utilisateur);

			if (utilisateur.getId() != null) {
				URI location = uriInfo.getRequestUriBuilder().path("" + utilisateur.getId()).build();

				return Response.created(location).entity(utilisateur).build();
			}
		} catch (EShopException ex) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(ex.getMessage()).build();
		}

		return Response.notModified().entity(utilisateur).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Utilisateur utilisateur, @PathParam("id") Long id) {
		if (id.equals(utilisateur.getId())) {
			utilisateur = utilisateurRepository.save(utilisateur);

			return Response.ok(utilisateur).build();
		}

		return Response.notModified().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		if (utilisateurRepository.deleteById(id)) {
			return Response.noContent().build();
		}

		return Response.notModified().build();
	}
}
