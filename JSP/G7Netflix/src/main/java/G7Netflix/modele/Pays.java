package G7Netflix.modele;

public class Pays {

	private Integer id;
	private String nom;
	private String code;
	
	public Pays(int id, String nom, String string) {
		this.id = id;
		this.nom = nom;
		this.code = string;	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
