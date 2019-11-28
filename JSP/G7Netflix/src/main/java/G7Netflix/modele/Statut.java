package G7Netflix.modele;

public class Statut extends Correspondance{

	private Affectation affectation;
	
	public Statut(Integer id, String libelle, Affectation affectation) {
		super(id, libelle);
		this.affectation = affectation;
	}
	
	public Affectation getAffectation() {
		return affectation;
	}
	
	public void setAffectation(Affectation affectation) {
		this.affectation = affectation;
	}

}