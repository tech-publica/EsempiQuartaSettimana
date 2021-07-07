package it.epicode.quarta.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EsempiBase {

	public static void main(String[] args) throws SQLException {
		List<Student> ls = goodReadStudentsBySurnameLike("pe");
		ls.forEach(System.out::println);
	}

	static {
		// hack
		// Driver d = new Driver();
		// DriverManager.registerDriver(d);
		// System.out.println("cucu");

	}

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static final String ALL_STUDENTS = "SELECT ID, NAME, SURNAME, DOB FROM epic_schema.student";

	public static final String ALL_STUDENTS_BY_SURNAME_LIKE = "SELECT ID, NAME, SURNAME, DOB FROM epic_schema.student WHERE surname LIKE ";

	public static final String ALL_STUDENTS_BY_SURNAME_LIKE_WITH_PARAM = "SELECT ID, NAME, SURNAME, DOB FROM epic_schema.student WHERE surname LIKE ?";

	public static List<Student> badReadStudents() throws SQLException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {

			// org.postgresql.Driver d = new org.postgresql.Driver(); NO!!!!
			System.out.println("driver caricato con successo");

			con = DriverManager

					.getConnection(
							"jdbc:postgresql://localhost:5432/be_epicode?currentSchema=epic_schema&user=postgresMaster&password=goPostgresGo");

			System.out.println("connessione stabilita con successo");
			System.out.println(con.getClass().getName());
			st = con.createStatement();
			rs = st.executeQuery(ALL_STUDENTS);
			List<Student> studs = new ArrayList<>();
			while (rs.next()) {
				studs.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getDate("dob").toLocalDate()));
			}

//			studs.forEach(System.out::println); // method reference, scope resolution operator ::
//			studs.forEach(s -> System.out.println(s)); // . dereferencing operator
			return studs;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public static List<Student> goodReadStudents() throws SQLException {
		List<Student> studs = new ArrayList<>();
		// try with resources
		// le risorse create nelle tonde del try verranno automaticamente e
		// correttamente chiuse all uscita dalle graffe del try
		// nelle tonde si possono solo creare oggetti di classe che implementano
		// AutoCloseable
		try (Connection con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/be_epicode?currentSchema=epic_schema&user=postgresMaster&password=goPostgresGo");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(ALL_STUDENTS);) {

			while (rs.next()) {
				studs.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getDate("dob").toLocalDate()));
			}
			return studs;
		}
	}

	public static List<Student> badReadStudentsBySurnameLike(String part) throws SQLException {
		List<Student> studs = new ArrayList<>();
		// try with resources
		// le risorse create nelle tonde del try verranno automaticamente e
		// correttamente chiuse all uscita dalle graffe del try
		// nelle tonde si possono solo creare oggetti di classe che implementano
		// AutoCloseable
		String sql = ALL_STUDENTS_BY_SURNAME_LIKE + "'%" + part + "%'";
		try (Connection con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/be_epicode?currentSchema=epic_schema&user=postgresMaster&password=goPostgresGo");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {
				studs.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getDate("dob").toLocalDate()));
			}
			return studs;
		}
	}

	public static List<Student> goodReadStudentsBySurnameLike(String part) throws SQLException {
		List<Student> studs = new ArrayList<>();
		// try with resources
		// le risorse create nelle tonde del try verranno automaticamente e
		// correttamente chiuse all uscita dalle graffe del try
		// nelle tonde si possono solo creare oggetti di classe che implementano
		// AutoCloseable

		try (Connection con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/be_epicode?currentSchema=epic_schema&user=postgresMaster&password=goPostgresGo");
				PreparedStatement st = con.prepareStatement(ALL_STUDENTS_BY_SURNAME_LIKE_WITH_PARAM);) {
			st.setString(1, "%" + part + "%");

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					studs.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
							rs.getDate("dob").toLocalDate()));
				}
				return studs;
			}

		}
	}

}
