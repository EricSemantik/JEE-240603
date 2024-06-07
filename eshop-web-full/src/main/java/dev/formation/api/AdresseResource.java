package dev.formation.api;

import java.net.URI;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import dev.formation.api.views.Views;
import dev.formation.exception.EShopException;
import dev.formation.model.Adresse;
import dev.formation.repository.IAdresseRepository;
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

@Path("/adresse")
public class AdresseResource {

	@Inject
	private IAdresseRepository adresseRepository;

	@GET
	@Produces({MediaType.APPLICATION_XML})
	@JsonView(Views.ViewAdresse.class)
	public Response getAll() {
		return Response.ok(adresseRepository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		Optional<Adresse> optAdresse = adresseRepository.findById(id);

		if(optAdresse.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(optAdresse.get()).build();
	}

	@GET
	@Path("/searchByVille")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByVille(@QueryParam("ville") String ville) {
		if(ville.isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.ok(adresseRepository.findAllByVille(ville)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Adresse adresse, @Context UriInfo uriInfo) {
		try {
			adresse = adresseRepository.save(adresse);
			
			if(adresse.getId() != null) {
				 URI location = uriInfo.getRequestUriBuilder()
	                     .path(""+adresse.getId())
	                     .build();
				
				return Response.created(location).entity(adresse).build();
			}
		} catch(EShopException ex) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
	                   .entity(ex.getMessage())
	                   .build();
		}
		
		return Response.notModified().entity(adresse).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Adresse adresse, @PathParam("id") Long id) {
		if(id.equals(adresse.getId())) {
			adresse = adresseRepository.save(adresse);
		
			return Response.ok(adresse).build();
		} 
		
		return Response.notModified().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		if(adresseRepository.deleteById(id)) {
			return Response.noContent().build();
		}
		
		return Response.notModified().build();
	}
}
