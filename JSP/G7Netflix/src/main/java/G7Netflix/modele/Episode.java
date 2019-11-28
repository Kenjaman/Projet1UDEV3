package G7Netflix.modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Episode {

	private Integer id;
	private Integer numero;
	private String titre;
	private String titreOriginal;
	private Integer duree;
	private String resume;
	private Date dateRealisation;
	private Date datePremiereDiffusion;
	private Public publics;
	private Statut statut;
	private Saison saison;



	public Episode(Integer id, Integer numero, String titre, String titreOriginal, Integer duree, String resume, Date dateRealisation,
			Date datePremiereDiffusion, Public publics, Statut statut, Saison saison) {
		this.numero = numero;
		this.titre = titre;
		this.titreOriginal = titreOriginal;
		this.duree = duree;
		this.resume = resume;
		this.dateRealisation = dateRealisation;
		this.datePremiereDiffusion = datePremiereDiffusion;
		this.publics = publics;
		this.statut = statut;
		this.saison = saison;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getTitreOriginal() {
		return titreOriginal;
	}
	public void setTitreOriginal(String titreOriginal) {
		this.titreOriginal = titreOriginal;
	}
	public Integer getDuree() {
		return duree;
	}
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Date getDateRealisation() {
		return dateRealisation;
	}
	public void setDateRealisation(Date dateRealisation) {
		this.dateRealisation = dateRealisation;
	}
	public Date getDatePremiereDiffusion() {
		return datePremiereDiffusion;
	}
	public void setDatePremiereDiffusion(Date datePremiereDiffusion) {
		this.datePremiereDiffusion = datePremiereDiffusion;
	}
	public Public getPublics() {
		return publics;
	}
	public void setPublics(Public publics) {
		this.publics = publics;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public Saison getSaison() {
		return saison;
	}
	public void setSaison(Saison saison) {
		this.saison = saison;
	}
}
