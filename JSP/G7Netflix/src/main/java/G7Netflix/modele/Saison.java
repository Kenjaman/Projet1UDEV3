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
	
	public Saison(Integer id, Integer numero, String resume, Integer anneeDiffusion, Statut statut, Serie serie) throws DonneesInvalidesException {
		
		List<Erreur> errSaison = new ArrayList<Erreur>();
		
		this.id = id;
		this.numero = numero;
		if(statut.getId() ==0) {
			errSaison.add(new Erreur("statut","Oubliez pas de renseigner un statut"));
		}
		this.serie = serie;
		if(serie.getId() == 0 ) {
			errSaison.add(new Erreur("serie","Veuillez rensigner la série que vous souhaitez ajouter"));
		}
		this.statut = statut;
		if(statut.getId()==0) {
			errSaison.add(new Erreur("Statut","Statut non renseigné"));
		}
		this.resume = resume;
		this.anneeDiffusion = anneeDiffusion;
		if(!errSaison.isEmpty())
			throw new DonneesInvalidesException(errSaison);
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
