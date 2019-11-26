package G7Netflix.modele;

public class Personne {

	private Integer id;
	private String nom;
	private String prenom;
	private Integer idCivilite;
	
	public Personne(Integer id, String nom, String prenom, Integer civilite) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.idCivilite = civilite;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Integer getIdCivilite() {
		return idCivilite;
	}
	public void setIdCivilite(Integer civilite) {
		this.idCivilite = civilite;
	}
	
	
}
