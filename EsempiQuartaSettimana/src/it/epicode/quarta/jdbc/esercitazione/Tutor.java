package it.epicode.quarta.jdbc.esercitazione;

public class Tutor {
	private long id;
    private String nome;
    private String cognome;
    private String materia;
	public Tutor(long id, String nome, String cognome, String materia) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.materia = materia;
	}
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getMateria() {
		return materia;
	}
    
    
}
