package it.epicode.quarta.jdbc.esercitazione;

public class Azienda {
	private long id;
	private String nome;
	private String luogo;
	private String settore;
	private String tipologia;

	public Azienda(long id, String nome, String luogo, String settore, String tipologia) {
		this.id = id;
		this.nome = nome;
		this.luogo = luogo;
		this.settore = settore;
		this.tipologia = tipologia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getSettore() {
		return settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	
	

}
