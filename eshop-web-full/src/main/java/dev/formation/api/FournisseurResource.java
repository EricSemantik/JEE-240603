package dev.formation.api;

import java.net.URI;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import dev.formation.api.views.Views;
import dev.formation.exception.EShopException;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;
import dev.formation.repository.IPersonneRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("/fournisseur")
public class FournisseurResource {

	@Inject
	private IPersonneRepository personneRepository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@JsonView(Views.ViewFournisseur.class)
	public Response getAll() {
		return Response.ok(personneRepository.findAllFournisseur()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(Views.ViewFournisseurProduits.class)
	public Response get(@PathParam("id") Long id) {
		Optional<Fournisseur> optFournisseur = personneRepository.findFournisseurByIdWithProduits(id);

		if (optFournisseur.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(optFournisseur.get()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Fournisseur fournisseur, @Context UriInfo uriInfo) {
		try {
			fournisseur = (Fournisseur) personneRepository.save(fournisseur);

			if (fournisseur.getId() != null) {
				URI location = uriInfo.getRequestUriBuilder().path("" + fournisseur.getId()).build();

				return Response.created(location).entity(fournisseur).build();
			}
		} catch (EShopException ex) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(ex.getMessage()).build();
		}

		return Response.notModified().entity(fournisseur).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Fournisseur fournisseur, @PathParam("id") Long id) {
		if (id.equals(fournisseur.getId())) {
			fournisseur = (Fournisseur) personneRepository.save(fournisseur);

			return Response.ok(fournisseur).build();
		}

		return Response.notModified().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		if (personneRepository.deleteById(id)) {
			return Response.noContent().build();
		}

		return Response.notModified().build();
	}
}
