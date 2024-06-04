package dev.formation.repository.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.formation.Application;
import dev.formation.model.Adresse;
import dev.formation.model.Civilite;
import dev.formation.model.Client;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;
import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.IPersonneRepository;

public class PersonneRepositoryCsv implements IPersonneRepository {

	private File file;

	private IAdresseRepository adresseRepository = Application.getInstance().getAdresseRepository();

	public PersonneRepositoryCsv(String chemin) {
		super();
		this.file = new File(chemin);
	}

	@Override
	public List<Personne> findAll() {
		return readAll();
	}

	@Override
	public Optional<Personne> findById(Long id) {
		Personne personne = readAll().stream().filter(p -> p.getId() == id).findFirst().get();

		return Optional.of(personne);
	}

	@Override
	public Personne save(Personne obj) {
		List<Personne> personnes = readAll();

		if (obj.getId() != null) {
			int i;
			boolean find = false;
			for (i = 0; i < personnes.size(); i++) {
				if (personnes.get(i).getId() == obj.getId()) {
					find = true;
					break;
				}
				if (find) {
					personnes.set(i, obj);
				}
			}
		} else {
			Long max = 0L;

			for (Personne p : personnes) {
				if (p.getId() > max) {
					max = p.getId();
				}
			}

			obj.setId(++max);

			personnes.add(obj);
		}

		writeAll(personnes);

		return obj;
	}

	@Override
	public void deleteById(Long id) {
		List<Personne> personnes = readAll();

		int i;
		boolean find = false;
		for (i = 0; i < personnes.size(); i++) {
			if (personnes.get(i).getId() == id) {
				find = true;
				break;
			}
			if (find) {
				personnes.remove(i);
			}
		}

		writeAll(personnes);
	}

	@Override
	public List<Client> findAllClient() {
		return readAll().stream().filter(p -> p instanceof Client).map(p -> (Client) p).collect(Collectors.toList());
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		return readAll().stream().filter(p -> p instanceof Fournisseur).map(p -> (Fournisseur) p)
				.collect(Collectors.toList());
	}

	private List<Personne> readAll() {
		List<Personne> personnes = new ArrayList<>();

		if (this.file.exists()) {
			try {
				List<String> lignes = Files.readAllLines(this.file.toPath(), StandardCharsets.UTF_8);

				for (String ligne : lignes) {
					String[] items = ligne.split(";");

					Personne personne = null;

					if (items[4].equals("client")) {
						personne = new Client();

						Client client = (Client) personne;

						client.setPrenom(items[5]);
						if (!items[6].isBlank()) {
							client.setDtNaissance(LocalDate.parse(items[6]));
						}
						if (!items[7].isBlank()) {
							Adresse adresse = adresseRepository.findById(Long.valueOf(items[7])).orElse(null);

							client.setAdresse(adresse);
						}
					} else {
						personne = new Fournisseur();

						Fournisseur fournisseur = (Fournisseur) personne;

						fournisseur.setResponsable(items[8]);
					}
					personne.setId(Long.valueOf(items[0]));
					if (!items[1].isBlank()) {
						personne.setCivilite(Civilite.valueOf(items[1]));
					}
					personne.setNom(items[2]);
					personne.setEmail(items[3]);

					personnes.add(personne);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return personnes;
	}

	private void writeAll(List<Personne> personnes) {
		List<String> lignes = new ArrayList<String>();
		final String SEPARATOR = ";";

		for (Personne personne : personnes) {
			StringBuilder ligneBuilder = new StringBuilder();
			ligneBuilder.append(personne.getId()).append(SEPARATOR);
			if (personne.getCivilite() != null) {
				ligneBuilder.append(personne.getCivilite().name());
			}
			ligneBuilder.append(SEPARATOR);
			ligneBuilder.append(personne.getNom()).append(SEPARATOR);
			ligneBuilder.append(personne.getEmail()).append(SEPARATOR);

			if (personne instanceof Client) {
				Client client = (Client) personne;
				ligneBuilder.append("client").append(SEPARATOR);
				ligneBuilder.append(client.getPrenom()).append(SEPARATOR);
				if (client.getDtNaissance() != null) {
					ligneBuilder.append(client.getDtNaissance());
				}
				ligneBuilder.append(SEPARATOR);
				if (client.getAdresse() != null && client.getAdresse().getId() != null) {
					ligneBuilder.append(client.getAdresse().getId());
				}
				ligneBuilder.append(SEPARATOR);

			} else {
				Fournisseur fournisseur = (Fournisseur) personne;
				ligneBuilder.append("fournisseur").append(SEPARATOR);
				ligneBuilder.append(SEPARATOR);
				ligneBuilder.append(SEPARATOR);
				ligneBuilder.append(SEPARATOR);
				ligneBuilder.append(fournisseur.getResponsable());

			}
			lignes.add(ligneBuilder.toString());
		}

		try {
			Files.write(this.file.toPath(), lignes, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
