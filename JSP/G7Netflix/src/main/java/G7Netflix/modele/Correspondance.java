package G7Netflix.modele;

public class Correspondance {

	private Integer id;
	private String libelle;
	
	public Correspondance(int id, String libelle){
		this.id = id;
		this.libelle = libelle;
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
