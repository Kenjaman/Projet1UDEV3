package G7Netflix.modele;

public class Serie {

	private Integer id;
	private String nom;
	private String nomoriginal;
	private Integer anneeparution;	
	private String synopsys;
	private Statut statut;
	private Pays paysOrigine;
	
	public Serie(Integer id, String nom, String nomoriginal, Integer anneeparution, String synopsys, Statut statut,
			Pays paysOrigine) {
		super(); 
		this.id = id;
		this.nom = nom;
		this.nomoriginal = nomoriginal;
		this.anneeparution = anneeparution;
		this.synopsys = synopsys;
		this.statut =statut;
		this.paysOrigine = paysOrigine;
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

	public String getNomOriginal() {
		return nomoriginal;
	}

	public void setNomOriginal(String nomoriginal) {
		this.nomoriginal = nomoriginal;
	}

	public Integer getAnneeParution() {
		return anneeparution;
	}

	public void setAnneeParution(Integer anneeparution) {
		this.anneeparution = anneeparution;
	}

	public String getSynopsys() {
		return synopsys;
	}

	public void setSynopsys(String synopsys) {
		this.synopsys = synopsys;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Pays getPaysOrigine() {
		return paysOrigine;
	}

	public void setPaysOrigine(Pays paysOrigine) {
		this.paysOrigine = paysOrigine;
	}
	
	
}
