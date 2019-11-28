package G7Netflix.modele;

public class Civilite extends Correspondance{

	private String abbr;
	
	public Civilite(Integer id, String libelle, String abbr) {
		super(id, libelle);
		this.abbr = abbr;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

}
