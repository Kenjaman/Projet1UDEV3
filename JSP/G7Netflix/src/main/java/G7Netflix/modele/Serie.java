package G7Netflix.modele;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Serie {

	private Integer id;
	private String nom;
	private String nomoriginal;
	private Integer anneeparution;	 
	private String synopsys;
	private Statut statut;
	private Pays paysOrigine;

	//Constructeur de séries depuis entrée utilisateur
	public Serie(String nom, String nomOriginal, String anneeParution, String synopsys, Statut statut,
			Pays paysOrigine) throws DonneesInvalidesException{
		List<Erreur> errSerie = new ArrayList<Erreur>();
		if(Utils.isBlank(nom))
			errSerie.add(new Erreur("nom","Veuillez rensigner un nom"));
		if(statut.getId()==0)
			errSerie.add(new Erreur("Statut","Statut non renseigné"));
		try {
			if(Integer.valueOf(anneeParution) == 0|| anneeParution == null) {
				errSerie.add(new Erreur("anneeParution","Année de parution non renseigné"));
			}else{
				this.anneeparution = Integer.valueOf(anneeParution);
			}
		}catch(NumberFormatException nb) {
			errSerie.add(new Erreur("anneeParution","Veuillez renseigner un chiffre "));
		}
		this.nom = nom;
		this.nomoriginal = nomOriginal;
		this.synopsys = synopsys;
		this.statut = statut;
		this.paysOrigine = paysOrigine;
		if(!errSerie.isEmpty())
			throw new DonneesInvalidesException(errSerie);
	}

	//Constructeur pour récupèrer les données de la BDD
	public Serie(Integer id, String nom, String nomOriginal, Integer anneeparution, String synopsys, Statut statut,
			Pays paysOrigine) {
		//		this(nom,nomoriginal,anneeparution,synopsys,statut,paysOrigine);
		this.id=id;
		this.nom = nom;
		this.anneeparution=anneeparution;
		this.nomoriginal = nomOriginal;
		this.synopsys = synopsys;
		this.statut = statut;
		this.paysOrigine = paysOrigine;
	}
	

	public Serie(Integer id, String nom) {
		this(id,nom,null,0,null,null,null);
		
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
