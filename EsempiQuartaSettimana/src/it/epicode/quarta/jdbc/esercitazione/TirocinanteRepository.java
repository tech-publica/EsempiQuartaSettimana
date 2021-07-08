package it.epicode.quarta.jdbc.esercitazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TirocinanteRepository {
	
	int visPackage = 9;
	
	public static final String QUERY_ALL = "SELECT id, matricola, nome, cognome, classe from epic_schema.tirocinante";

	public static final String INSERT_TIROCINANTE = "INSERT INTO epic_schema.tirocinante (id, matricola, nome, cognome, classe, id_azienda, id_tutor) values (?,?,?,?,?,?,?)";

	public static final String UPDATE_TIROCINANTE = "UPDATE epic_schema.tirocinante SET matricola = ?, nome = ?, cognome = ?, classe = ? , id_azienda = ?, id_tutor = ? WHERE id = ?";

	public static final String DELETE_TIROCINANTE = "DELETE FROM epic_schema.tirocinante WHERE id = ?";

	private static final String QUERY_TIROCINANTE_PER_AZIENDA = "SELECT t.id as tir_id, t.matricola, t.nome as tir_nome, t.cognome, t.classe, a.id as az_id, a.nome as az_nome, a.luogo , a.settore, a.tipologia from epic_schema.tirocinante t  "
			+ "INNER JOIN epic_schema.azienda a on t.id_azienda = a.id WHERE a.nome = ?";

	private ConnectionHandler connHandler;

	public TirocinanteRepository(ConnectionHandler handler) { // iniezione delle dipendenze
		connHandler = handler;
	}

	public List<Tirocinante> leggi() throws SQLException {
		List<Tirocinante> all = new ArrayList<Tirocinante>();
		try (Connection con = connHandler.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(QUERY_ALL)) {
			while (rs.next()) {
				all.add(new Tirocinante(rs.getLong("id"), rs.getString("matricola"), rs.getString("nome"),
						rs.getString("cognome"), rs.getString("classe")));

			}
			return all;
		}
	}

	public List<Tirocinante> leggiPerAzienda(String nomeAzienda) throws SQLException {
		List<Tirocinante> all = new ArrayList<Tirocinante>();
		try (Connection con = connHandler.getConnection();
				PreparedStatement pst = connHandler.getPreparedStatement(QUERY_TIROCINANTE_PER_AZIENDA);

		) {
			pst.setString(1, nomeAzienda);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					Tirocinante t = new Tirocinante(rs.getLong("tir_id"), rs.getString("matricola"),
							rs.getString("tir_nome"), rs.getString("cognome"), rs.getString("classe"));
					Azienda a = new Azienda(rs.getLong("az_id"), rs.getString("az_nome"), rs.getString("luogo"),
							rs.getString("settore"), rs.getString("tipologia"));
					t.setAzienda(a);
				}
				return all;
			}
		}
	}

	public void insert(Tirocinante tiro) throws SQLException {
		try (Connection con = connHandler.getConnection();
				PreparedStatement ps = connHandler.getPreparedStatement(INSERT_TIROCINANTE)) {
			ps.setLong(1, tiro.getId());
			ps.setString(2, tiro.getMatricola());
			ps.setString(3, tiro.getNome());
			ps.setString(4, tiro.getCognome());
			ps.setString(5, tiro.getClasse());
			ps.setLong(6, tiro.getAzienda().getId());
			ps.setLong(7, tiro.getTutor().getId());

			ps.executeUpdate();
		}
	}

	public void update(Tirocinante updated) throws SQLException {
		try (Connection con = connHandler.getConnection();
				PreparedStatement ps = connHandler.getPreparedStatement(UPDATE_TIROCINANTE)) {

			ps.setString(1, updated.getMatricola());
			ps.setString(2, updated.getNome());
			ps.setString(3, updated.getCognome());
			ps.setString(4, updated.getClasse());
			ps.setLong(5, updated.getAzienda().getId());
			ps.setLong(6, updated.getTutor().getId());

			ps.executeUpdate();
		}
	}

	public void delete(long id) throws SQLException {
		try (Connection con = connHandler.getConnection();
				PreparedStatement ps = connHandler.getPreparedStatement(DELETE_TIROCINANTE)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		}
	}

}
