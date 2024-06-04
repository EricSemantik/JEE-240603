package dev.formation.repository.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.formation.model.Adresse;
import dev.formation.repository.IAdresseRepository;

public class AdresseRepositoryCsv implements IAdresseRepository {

	private File file;

	public AdresseRepositoryCsv(String chemin) {
		super();
		this.file = new File(chemin);
	}

	@Override
	public List<Adresse> findAll() {
		return readAll();
	}

	@Override
	public Optional<Adresse> findById(Long id) {
		Adresse adresse = readAll().stream().filter(a -> a.getId() == id).findFirst().get();

		return Optional.of(adresse);
	}

	@Override
	public Adresse save(Adresse obj) {
		List<Adresse> adresses = readAll();

		if (obj.getId() != null) {
			int i;
			boolean find = false;
			for (i = 0; i < adresses.size(); i++) {
				if (adresses.get(i).getId() == obj.getId()) {
					find = true;
					break;
				}
				if (find) {
					adresses.set(i, obj);
				}
			}
		} else {
			Long max = 0L;

			for (Adresse a : adresses) {
				if (a.getId() > max) {
					max = a.getId();
				}
			}

			obj.setId(++max);

			adresses.add(obj);
		}

		writeAll(adresses);

		return obj;
	}

	@Override
	public void deleteById(Long id) {
		List<Adresse> adresses = readAll();

		int i;
		boolean find = false;
		for (i = 0; i < adresses.size(); i++) {
			if (adresses.get(i).getId() == id) {
				find = true;
				break;
			}
			if (find) {
				adresses.remove(i);
			}
		}

		writeAll(adresses);
	}

	@Override
	public List<Adresse> findAllByVille(String ville) {
		return readAll().stream().filter(a -> a.getVille() == ville).collect(Collectors.toList());
	}

	private List<Adresse> readAll() {
		List<Adresse> adresses = new ArrayList<>();

		if (this.file.exists()) {
			try {
				List<String> lignes = Files.readAllLines(this.file.toPath(), StandardCharsets.UTF_8);

				for (String ligne : lignes) {
					String[] items = ligne.split(";");
					Adresse adresse = new Adresse(items[1], items[2], items[3]);
					adresse.setId(Long.valueOf(items[0]));
					adresses.add(adresse);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return adresses;
	}

	private void writeAll(List<Adresse> adresses) {
		List<String> lignes = new ArrayList<String>();
		final String SEPARATOR = ";";

		for (Adresse adresse : adresses) {
			String ligne = adresse.getId() + SEPARATOR + adresse.getRue() + SEPARATOR + adresse.getCodePostal() + SEPARATOR + adresse.getVille();
			lignes.add(ligne);
		}

		try {
			Files.write(this.file.toPath(), lignes, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
