package G7Netflix.modele;

public class Public {

	private Integer id;
	private String libelle;
	private Integer limiteAge;
	
	public Public(Integer id, String libelle, Integer limiteAge){
		this.id = id;
		this.libelle = libelle;
		this.limiteAge = limiteAge;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public Integer getLimiteAge() {
		return limiteAge;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public void setLimiteAge(Integer limiteAge) {
		this.limiteAge = limiteAge;
	}
	
}
