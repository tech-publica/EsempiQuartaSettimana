package it.epicode.quarta.jdbc.esercitazione;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TirocinanteRepositoryTest {
	
	public static final String DB_URL = "localhost";
	public static final String DB_NAME = "be_epicode";
	public static final String SCHEMA = "epic_schema";
	public static final int PORT = 5432;
	public static final String USERNAME = "postgresMaster";
	public static final String PASSWORD = "goPostgresGo";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("before all");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("after all");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("before each");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("after each");
	}

	@Test
	void leggi_should_return_all_tirocinante() {
		ConnectionHandler handler = new ConnectionHandler(DB_URL, PORT, DB_NAME, SCHEMA, USERNAME, PASSWORD);
		TirocinanteRepository tiroRepo = new TirocinanteRepository(handler);
		try {
			List<Tirocinante> tirs = tiroRepo.leggi();
			assertEquals(2, tirs.size());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	@Test
	void test2() {
		
		System.out.println("test 2");
	}

}
