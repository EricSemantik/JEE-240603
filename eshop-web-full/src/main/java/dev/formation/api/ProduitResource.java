package dev.formation.api;

import java.net.URI;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import dev.formation.api.views.Views;
import dev.formation.exception.EShopException;
import dev.formation.model.Produit;
import dev.formation.repository.IProduitRepository;
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

@Path("/produit")
public class ProduitResource {

	@Inject
	private IProduitRepository produitRepository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@JsonView(Views.ViewProduit.class)
	public Response getAll() {
		return Response.ok(produitRepository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(Views.ViewProduitCommentaires.class)
	public Response get(@PathParam("id") Long id) {
		Optional<Produit> optProduit = produitRepository.findProduitByIdWithCommentaires(id);

		if (optProduit.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(optProduit.get()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(Views.ViewProduit.class)
	public Response post(Produit produit, @Context UriInfo uriInfo) {
		try {
			produit = (Produit) produitRepository.save(produit);

			if (produit.getId() != null) {
				URI location = uriInfo.getRequestUriBuilder().path("" + produit.getId()).build();

				return Response.created(location).entity(produit).build();
			}
		} catch (EShopException ex) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(ex.getMessage()).build();
		}

		return Response.notModified().entity(produit).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(Views.ViewProduit.class)
	public Response put(Produit produit, @PathParam("id") Long id) {
		if (id.equals(produit.getId())) {
			produit = (Produit) produitRepository.save(produit);

			return Response.ok(produit).build();
		}

		return Response.notModified().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		if (produitRepository.deleteById(id)) {
			return Response.noContent().build();
		}

		return Response.notModified().build();
	}
}
