package it.epicode.quarta.jdbc.esercitazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionHandler {
	
	private final String DB_URL;
	private final int PORT;
	private final String DB_NAME;
	private final String SCHEMA;
	private final String USERNAME;
	private final String PASSWORD;
	private final String JDBC_URL;
	private Connection connection;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public ConnectionHandler(String dbUrl, int port, String dbName, String schema, String username, String password) {
		DB_URL = dbUrl;
		PORT = port;
		DB_NAME = dbName;
		SCHEMA = schema;
		USERNAME = username;
		PASSWORD = password;
		JDBC_URL = String.format("jdbc:postgresql://%s:%d/%s?currentSchema=%s&user=%s&password=%s", DB_URL, PORT, DB_NAME, SCHEMA, USERNAME, PASSWORD);
	}
	
	
	public Connection getConnection() throws SQLException {
		if( connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(JDBC_URL);
		}
		return connection;
	}
	
	
	public void closeConnection() throws SQLException {
		if( connection != null || !connection.isClosed()) {
			connection.close();
		}
	}
	
	public PreparedStatement getPreparedStatement(String parametricQuery) throws SQLException {
		if(connection == null) {
			throw new IllegalStateException("non si puo' creare un prepared statement senza aver prima creato la connessione");
		}
		return connection.prepareStatement(parametricQuery);
	}
	
}
