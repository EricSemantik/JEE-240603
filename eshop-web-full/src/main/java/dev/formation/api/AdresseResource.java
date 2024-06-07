package dev.formation.api;

import java.util.List;
import java.util.Optional;

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
import jakarta.ws.rs.core.MediaType;

@Path("/adresse")
public class AdresseResource {

	@Inject
	private IAdresseRepository adresseRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Adresse> getAll() {
		return adresseRepository.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Adresse get(@PathParam("id") Long id) {
		Optional<Adresse> optAdresse = adresseRepository.findById(id);

		return optAdresse.get();
	}

	@GET
	@Path("/searchByVille")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Adresse> getAllByVille(@QueryParam("ville") String ville) {
		return adresseRepository.findAllByVille(ville);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Adresse post(Adresse adresse) {
		adresse = adresseRepository.save(adresse);
		return adresse;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Adresse put(Adresse adresse, @PathParam("id") Long id) {
		if(id.equals(adresse.getId())) {
			adresse = adresseRepository.save(adresse);
		}
		
		return adresse;
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		adresseRepository.deleteById(id);
	}
}
