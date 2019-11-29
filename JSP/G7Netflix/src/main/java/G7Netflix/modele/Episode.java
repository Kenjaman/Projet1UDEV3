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
			Date datePremiereDiffusion, Public publics, Statut statut, Saison saison) throws DonneesInvalidesException {
		
		List<Erreur> errEpisode = new ArrayList<Erreur>();
		
		this.id = id;
		this.numero = numero;
		if(statut.getId() ==0) {
			errEpisode.add(new Erreur("statut","Oubliez pas de renseigner un statut"));
		}
		this.titre = titre;
		if (Utils.isBlank(titre)) {
			errEpisode.add(new Erreur("titre","Le titre est vide !"));
		}
		this.titreOriginal = titreOriginal;
		this.duree = duree;
		this.resume = resume;
		this.dateRealisation = dateRealisation;
		this.datePremiereDiffusion = datePremiereDiffusion;
		
		this.publics = publics;
		if(publics.getId() ==0) {
			errEpisode.add(new Erreur("publics","Votre champs est vide, n'oubliez pas la limite d'âge"));
		}
		this.statut = statut;
		if(statut.getId()==0) {
			errEpisode.add(new Erreur("Statut","Statut non renseigné"));
		}
		this.saison = saison;
		if(saison.getId()==0) {
			errEpisode.add(new Erreur("saison","N'oubliez pas la saison ! "));
		}
		if(!errEpisode.isEmpty())
			throw new DonneesInvalidesException(errEpisode);
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
