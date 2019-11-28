package G7Netflix.modele;

import java.util.ArrayList;
import java.util.List;

public class Personne {

	private Integer id;
	private String nom;
	private String prenom;
	private Civilite civilite;
	
	public Personne(Integer id, String nom, String prenom, Civilite civilite) throws DonneesInvalidesException {
		super();
		List<Erreur> errPersonne = new ArrayList<Erreur>();
		this.id = id;
		this.nom = nom;
		if (Utils.isBlank(nom)) {
			errPersonne.add(new Erreur("nom","Le nom est vide !"));
		}
		this.prenom = prenom;
		if(Utils.isBlank(prenom)) {
			errPersonne.add(new Erreur("prenom","Le prénom est vide !"));
		}
		this.civilite = civilite;
		if(civilite.getId() ==0) {
			errPersonne.add(new Erreur("civilite","Oubliez pas de rensigner une civilite"));
		}
		if(!errPersonne.isEmpty())
			throw new DonneesInvalidesException(errPersonne);
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
	public Civilite civilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	
	
}
