package it.epicode.quarta.jdbc.esercitazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {
	public  static final String QUERY_TUTORS_BY_SUBJECT_LIKE 
	= "SELECT id, nome, cognome, materia from epic_schema.tutor WHERE materia LIKE ?";
	private ConnectionHandler connHandler;

	public TutorRepository(ConnectionHandler handler) { // iniezione delle dipendenze
		connHandler = handler;
	}
	
	
	public List<Tutor> getTutorsByMateriaLike(String mat) throws SQLException {
		List<Tutor> all = new ArrayList<Tutor>();
		try (Connection con = connHandler.getConnection();
				PreparedStatement pst= connHandler.getPreparedStatement(QUERY_TUTORS_BY_SUBJECT_LIKE);
				) {
			
			pst.setString(1, mat+"%");
			try(ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					all.add(new Tutor(rs.getLong("id"), rs.getString("nome"),
							rs.getString("cognome"), rs.getString("materia")));

				}
				return all;
			}
			
		}
	}
}
