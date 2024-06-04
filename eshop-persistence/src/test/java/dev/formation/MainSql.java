package dev.formation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainSql {
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();

			st = conn.createStatement();

			int rows = st.executeUpdate(
					"insert into adresse (rue, code_postal, ville) values ('1 rue de la Paix', '75008', 'Paris')");

			if (rows == 1) {
				System.out.println("Insertion OK en statement");
			}

			rs = st.executeQuery("select id, rue, code_postal, ville from adresse");

			while (rs.next()) {
				Long id = rs.getLong("id");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				System.out.println("id=" + id + "-rue=" + rue + "-codepostal=" + codePostal + "-ville=" + ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("######################################");

		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();

			ps = conn.prepareStatement("insert into adresse (rue, code_postal, ville) values (?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "1 rue de la paix");
			ps.setString(2, "75008");
			ps.setString(3, "Paris");

			int rows = ps.executeUpdate();

			Long id = null;

			if (rows == 1) {
				System.out.println("Insertion OK en prepareStatement");

				try (ResultSet rsKeys = ps.getGeneratedKeys()) {
					if (rsKeys.next()) {
						// On récupère la clé dans la première colonne
						id = rsKeys.getLong(1);
						// ...
					}
				}
			}

			ps = conn.prepareStatement("select rue, code_postal, ville from adresse where id = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				System.out.println("id=" + id + "-rue=" + rue + "-codepostal=" + codePostal + "-ville=" + ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			conn = Application.getInstance().getConnection();

			conn.setAutoCommit(false);
			
			
			
			
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
