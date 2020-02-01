package G7Netflix.modele;

import java.util.ArrayList;
import java.util.List;

public class Saison {
	
	private Integer id;
	private Integer numero;
	private String resume;
	private Integer anneeDiffusion;
	private Statut statut;
	private Serie serie;
	
	
	public Saison(Integer numSaison, String resume, Integer anneDif,Statut statut, Serie serieSaison) throws DonneesInvalidesException {
		List<Erreur> errSaison = new ArrayList<Erreur>();
		if(numSaison==0) {
			errSaison.add(new Erreur("Numero Saison ","N'oubliez pas de renseigner un numero de saison"));
		}
		if(statut.getId()==0) {
			errSaison.add(new Erreur("Statut","Statut non renseigné"));
		}
		if(serieSaison.getId() == 0 ) {
			errSaison.add(new Erreur("serie","Veuillez rensigner la série que vous souhaitez ajouter"));
		}
		this.serie = serieSaison;
		this.statut = statut;
		this.numero = numSaison;
		this.resume = resume;
		this.anneeDiffusion = anneDif;
		if(!errSaison.isEmpty())
			throw new DonneesInvalidesException(errSaison);
		}
	
	//Saison depuis BDD
	public Saison(Integer id, Integer numero, String resume, Integer anneeDiffusion, Statut statut, Serie serie) throws DonneesInvalidesException {
		this(numero,resume,anneeDiffusion,statut,serie);
		this.id = id;
		
	}
	
	//Saison pour affichage de toute les saisons
	public Saison(Integer id, Integer numero,Serie serie) throws DonneesInvalidesException{
		this.id=id;
		this.numero=numero;
		this.serie=serie;
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
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Integer getAnneeDiffusion() {
		return anneeDiffusion;
	}
	public void setAnneeDiffusion(Integer anneeDiffusion) {
		this.anneeDiffusion = anneeDiffusion;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	
}
