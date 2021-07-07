package it.epicode.quarta.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class EsempiBase {

	static {
		// hack
		// Driver d = new Driver();
		// DriverManager.registerDriver(d);
		// System.out.println("cucu");

	}

	static {
		// System.out.println("pippo");

	}

	public static void main(String[] args) {
		
		try {
			Class.forName("org.postgresql.Driver"); // hack!!! (necessaria)
			//org.postgresql.Driver d = new org.postgresql.Driver(); NO!!!!
			System.out.println("driver caricato con successo");
			
		    Connection con =  DriverManager
			
				.getConnection("jdbc:postgresql://localhost:5432/be_epicode?currentSchema=epic_schema&user=postgresMaster&password=goPostgresGo");
			
			System.out.println("connessione stabilita con successo");
			System.out.println(con.getClass().getName());
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	

	}

}
