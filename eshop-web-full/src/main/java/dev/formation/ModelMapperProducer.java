package dev.formation;

import org.modelmapper.ModelMapper;

import dev.formation.api.dto.AdresseDto;
import dev.formation.model.Adresse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class ModelMapperProducer {

	@Produces
	@ApplicationScoped
	public ModelMapper create() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(Adresse.class, AdresseDto.class).addMappings(mapper -> {
			mapper.map(src -> src.getRue(),
					AdresseDto::setStreet);
			mapper.map(src -> src.getCodePostal(),
					AdresseDto::setZipcode);
			mapper.map(src -> src.getVille(),
					AdresseDto::setCity);
			});
		
		return modelMapper;
	}

}
