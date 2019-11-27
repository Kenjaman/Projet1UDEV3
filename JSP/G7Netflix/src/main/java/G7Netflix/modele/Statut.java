package G7Netflix.modele;

public class Statut{
	private Integer id;
	private String libelle;

	public Statut(Integer id, String libelle) {
		this.id=id;
		this.libelle=libelle;
	}

	public Integer getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



}
