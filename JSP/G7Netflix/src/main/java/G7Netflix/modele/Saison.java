package G7Netflix.modele;

public class Saison {
	
	private Integer id;
	private Integer numero;
	private String resume;
	private Integer anneeDiffusion;
	private Statut statut;
	private Serie serie;
	
	public Saison(Integer id, Integer numero, String resume, Integer anneeDiffusion, Statut statut, Serie serie) {
		this.numero = numero;
		this.resume = resume;
		this.anneeDiffusion = anneeDiffusion;
		this.statut = statut;
		this.serie = serie;
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
