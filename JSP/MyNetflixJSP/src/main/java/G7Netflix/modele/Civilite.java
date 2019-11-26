package G7Netflix.modele;

public class Civilite {

	private Integer id;
	private String libelle;
	private String abbr;
	
	public Civilite(Integer id, String libelle, String abbr) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.abbr = abbr;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
