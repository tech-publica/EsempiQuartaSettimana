package it.epicode.quarta.jdbc.esercitazione;

public class Tirocinante {
	private long id;
	private String matricola;
	private String nome;
	private String cognome;
	private String classe;
	private Azienda azienda;
	private Tutor tutor;

	public Tirocinante(long id, String matricola, String nome, String cognome, String classe, Azienda azienda,
			Tutor tutor) {
		this.id = id;
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.classe = classe;
		this.azienda = azienda;
		this.tutor = tutor;
	}

	public Tirocinante(long id, String matricola, String nome, String cognome, String classe) {
		this.id = id;
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.classe = classe;
	}

	public long getId() {
		return id;
	}

	public String getMatricola() {
		return matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getClasse() {
		return classe;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	
	

}
