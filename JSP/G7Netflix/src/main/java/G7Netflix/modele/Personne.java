package G7Netflix.modele;

import java.util.ArrayList;
import java.util.List;

public class Personne {

	private Integer id;
	private String nom;
	private String prenom;
	private Civilite civilite;
	
	public Personne(Integer id, String nom, String prenom, Civilite civilite) {
		super();
		List<Erreur> errPersonne = new ArrayList<Erreur>();
		this.id = id;
		errPersonne.add(new Erreur("id","Id Inexistant"));
		this.nom = nom;
		errPersonne.add(new Erreur("nom","Veuillez rentrer un nom"));
		this.prenom = prenom;
		errPersonne.add(new Erreur("prenom","Veuillez rentrer un prenom"));
		this.civilite = civilite;
		errPersonne.add(new Erreur("civilite","Oubliez pas de rensigner une civilite"));
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
