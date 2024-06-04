package dev.formation.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.Application;
import dev.formation.model.Adresse;
import dev.formation.repository.IAdresseRepository;

public class AdresseRepositorySql implements IAdresseRepository {

	@Override
	public List<Adresse> findAll() {
		List<Adresse> adresses = new ArrayList<Adresse>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("select id, rue, code_postal, ville from adresse");

			rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");

				Adresse adresse = new Adresse(rue, codePostal, ville);
				adresse.setId(id);

				adresses.add(adresse);
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

		return adresses;
	}

	@Override
	public Optional<Adresse> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Adresse save(Adresse obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Adresse> findAllByVille(String ville) {
		// TODO Auto-generated method stub
		return null;
	}

}
