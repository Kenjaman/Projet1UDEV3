package G7Netflix.modele;

public class Statut extends Correspondance {

	private Integer idAffectation;
	
	public Statut(int id, String libelle,int idAffectation) {
		super(id,libelle);
		this.idAffectation = idAffectation;
	}

	public Integer getIdAffectation() {
		return idAffectation;
	}

	public void setIdAffectation(Integer idAffectation) {
		this.idAffectation = idAffectation;
	}
	
	
}
