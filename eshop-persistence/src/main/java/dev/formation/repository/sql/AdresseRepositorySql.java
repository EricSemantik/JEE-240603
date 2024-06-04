package dev.formation.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		Optional<Adresse> optAdresse = Optional.empty();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("select rue, code_postal, ville from adresse where id = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");

				Adresse adresse = new Adresse(rue, codePostal, ville);
				adresse.setId(id);

				optAdresse = Optional.of(adresse);
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

		return optAdresse;
	}

	@Override
	public Adresse save(Adresse obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();

			if (obj.getId() != null) {
				ps = conn.prepareStatement("update adresse set rue = ?, code_postal = ?, ville = ? where id = ?");
				ps.setString(1, obj.getRue());
				ps.setString(2, obj.getCodePostal());
				ps.setString(3, obj.getVille());
				ps.setLong(4, obj.getId());

				ps.executeUpdate();
			} else {
				ps = conn.prepareStatement("insert into adresse (rue, code_postal, ville) values (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, obj.getRue());
				ps.setString(2, obj.getCodePostal());
				ps.setString(3, obj.getVille());

				int rows = ps.executeUpdate();

				if (rows == 1) {
					try (ResultSet rsKeys = ps.getGeneratedKeys()) {
						if (rsKeys.next()) {
							// On récupère la clé dans la première colonne
							Long id = rsKeys.getLong(1);
							obj.setId(id);
							// ...
						}
					}
				}
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
		return obj;
	}

	@Override
	public void deleteById(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("delete from adresse where id = ?");
			ps.setLong(1, id);

			ps.executeUpdate();

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

	}

	@Override
	public List<Adresse> findAllByVille(String ville) {
		List<Adresse> adresses = new ArrayList<Adresse>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("select id, rue, code_postal, ville from adresse where ville = ?");
			ps.setString(1, ville);

			rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");

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

}
