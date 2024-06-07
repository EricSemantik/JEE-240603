package dev.formation.api;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import dev.formation.api.dto.ClientDto;
import dev.formation.exception.EShopException;
import dev.formation.model.Adresse;
import dev.formation.model.Civilite;
import dev.formation.model.Client;
import dev.formation.model.Personne;
import dev.formation.model.Utilisateur;
import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.IPersonneRepository;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("/client")
public class ClientResource {

	@Inject
	private IAdresseRepository adresseRepository;
	
	@Inject
	private IPersonneRepository personneRepository;
	
	@Inject
	private IUtilisateurRepository utilisateurRepository;
	
	@Inject
	private ModelMapper modelMapper;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() {
//		modelMapper.typeMap(Client.class, ClientDto.class).addMappings(mapper -> {
//			mapper.map(src -> src.getCivilite().ordinal(),
//					ClientDto::setCiv);
//			mapper.map(src -> src.getDtNaissance().toString(),
//					ClientDto::setDateNaissance);
//			mapper.map(src -> src.getAdresse().getRue(),
//					ClientDto::setRue);
//			});
		
				
		List<ClientDto> clients = personneRepository.findAllClient().stream().map(client -> {
			ClientDto clientDto = modelMapper.map(client, ClientDto.class);
			clientDto.setCiv(client.getCivilite().ordinal());
			clientDto.setDateNaissance(client.getDtNaissance().toString());
			if(client.getUtilisateur() != null) {
				clientDto.setUtilisateurId(client.getUtilisateur().getId());
				clientDto.setUtilisateurLogin(client.getUtilisateur().getIdentifiant());
				clientDto.setUtilisateurActive(client.getUtilisateur().isActive());
			}
			if(client.getAdresse() != null) {
				clientDto.setRue(client.getAdresse().getRue());
				clientDto.setCodePostal(client.getAdresse().getCodePostal());
				clientDto.setVille(client.getAdresse().getVille());
			}
			
			return clientDto;
		}).collect(Collectors.toList());
		
		return Response.ok(clients).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		Optional<Personne> optClient = personneRepository.findById(id);

		if (optClient.isEmpty() && !(optClient.get() instanceof Client)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Client client = (Client) optClient.get();
		
		ClientDto clientDto = modelMapper.map(client, ClientDto.class);
		clientDto.setCiv(client.getCivilite().ordinal());
		clientDto.setDateNaissance(client.getDtNaissance().toString());
		if(client.getUtilisateur() != null) {
			clientDto.setUtilisateurId(client.getUtilisateur().getId());
			clientDto.setUtilisateurLogin(client.getUtilisateur().getIdentifiant());
			clientDto.setUtilisateurActive(client.getUtilisateur().isActive());
		}
		if(client.getAdresse() != null) {
			clientDto.setRue(client.getAdresse().getRue());
			clientDto.setCodePostal(client.getAdresse().getCodePostal());
			clientDto.setVille(client.getAdresse().getVille());
		}
		

		return Response.ok(clientDto).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(ClientDto clientDto, @Context UriInfo uriInfo) {
		try {
			Adresse adresse = modelMapper.map(clientDto, Adresse.class);
			
			adresse = adresseRepository.save(adresse);
			
			Utilisateur utilisateur = modelMapper.map(clientDto, Utilisateur.class);
			utilisateur.setIdentifiant(clientDto.getUtilisateurLogin());
			utilisateur.setMotDePasse(clientDto.getUtilisateurPassword());
			utilisateur.setActive(clientDto.isUtilisateurActive());
			
			utilisateur = utilisateurRepository.save(utilisateur);
			
			Client client = modelMapper.map(clientDto, Client.class);
			client.setCivilite(Civilite.values()[clientDto.getCiv()]);
			client.setDtNaissance(LocalDate.parse(clientDto.getDateNaissance()));
			client.setAdresse(adresse);
			client.setUtilisateur(utilisateur);
			
			client = (Client) personneRepository.save(client);

			if (client.getId() != null) {
				URI location = uriInfo.getRequestUriBuilder().path("" + client.getId()).build();

				return Response.created(location).entity(client).build();
			}
		} catch (EShopException ex) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).entity(ex.getMessage()).build();
		}

		return Response.notModified().entity(clientDto).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Client client, @PathParam("id") Long id) {
		if (id.equals(client.getId())) {
			client = (Client) personneRepository.save(client);

			return Response.ok(client).build();
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
