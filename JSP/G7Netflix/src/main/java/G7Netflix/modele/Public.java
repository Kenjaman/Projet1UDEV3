package G7Netflix.modele;

public class Public extends Correspondance{

	private Integer limiteAge;
	
	public Public(Integer id, String libelle, Integer limiteAge){
		super(id, libelle);
		this.limiteAge = limiteAge;
	}
	
	public Integer getLimiteAge() {
		return limiteAge;
	}
	
	public void setLimiteAge(Integer limiteAge) {
		this.limiteAge = limiteAge;
	}
	
}
