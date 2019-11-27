package G7Netflix.modele;

public class Pays {

	private Integer id;
	private String nom;
	private Integer code;
	
	public Pays(int id, String nom, int code) {
		this.id = id;
		this.nom = nom;
		this.code = code;	
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
