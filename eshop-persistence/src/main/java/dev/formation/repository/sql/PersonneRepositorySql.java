package dev.formation.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.Application;
import dev.formation.model.Adresse;
import dev.formation.model.Civilite;
import dev.formation.model.Client;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;
import dev.formation.model.Utilisateur;
import dev.formation.repository.IPersonneRepository;

public class PersonneRepositorySql implements IPersonneRepository {

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList<Personne>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement(
					"select type, id, civilite, email, nom, dt_naissance, prenom, responsable, utilisateur_id, adresse_id from personne");
			rs = ps.executeQuery();

			while (rs.next()) {
				String type = rs.getString("type");

				Personne personne = null;

				if (type.equals("client")) {
					personne = new Client();

					Client client = (Client) personne;

					LocalDate dtNaissance = rs.getDate("dt_naissance") != null
							? rs.getDate("dt_naissance").toLocalDate()
							: null;
					String prenom = rs.getString("prenom");

					Long adresseId = rs.getLong("adresse_id");
					if (!rs.wasNull()) {
						Adresse adresse = Application.getInstance().getAdresseRepository().findById(adresseId)
								.orElse(null);
						client.setAdresse(adresse);
					}

					client.setDtNaissance(dtNaissance);
					client.setPrenom(prenom);

				} else {
					personne = new Fournisseur();

					String responsable = rs.getString("responsable");

					((Fournisseur) personne).setResponsable(responsable);
				}

				Long id = rs.getLong("id");
				Civilite civilite = rs.getString("civilite") != null ? Civilite.valueOf(rs.getString("civilite"))
						: null;
				String email = rs.getString("email");
				String nom = rs.getString("nom");

				Long utilisateurId = rs.getLong("utilisateur_id");
				if (!rs.wasNull()) {
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setId(utilisateurId);

					personne.setUtilisateur(utilisateur);
				}

				personne.setId(id);
				personne.setCivilite(civilite);
				personne.setEmail(email);
				personne.setNom(nom);

				personnes.add(personne);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return personnes;
	}

	@Override
	public Optional<Personne> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public Personne save(Personne obj) {

		return obj;
	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public List<Client> findAllClient() {
		List<Client> clients = new ArrayList<Client>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement(
					"select id, civilite, email, nom, dt_naissance, prenom, utilisateur_id, adresse_id from personne where type = ?");
			ps.setString(1, "client");
			rs = ps.executeQuery();

			while (rs.next()) {

				Client client = new Client();

				Long id = rs.getLong("id");
				Civilite civilite = rs.getString("civilite") != null ? Civilite.valueOf(rs.getString("civilite"))
						: null;
				String email = rs.getString("email");
				String nom = rs.getString("nom");

				Long utilisateurId = rs.getLong("utilisateur_id");
				if (!rs.wasNull()) {
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setId(utilisateurId);

					client.setUtilisateur(utilisateur);
				}

				client.setId(id);
				client.setCivilite(civilite);
				client.setEmail(email);
				client.setNom(nom);

				LocalDate dtNaissance = rs.getDate("dt_naissance") != null ? rs.getDate("dt_naissance").toLocalDate()
						: null;
				String prenom = rs.getString("prenom");

				Long adresseId = rs.getLong("adresse_id");
				if (!rs.wasNull()) {
					Adresse adresse = Application.getInstance().getAdresseRepository().findById(adresseId).orElse(null);
					client.setAdresse(adresse);
				}

				client.setDtNaissance(dtNaissance);
				client.setPrenom(prenom);

				clients.add(client);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return clients;
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		// TODO Auto-generated method stub
		return null;
	}

}
