package G7Netflix.modele;

public class Saison {
	private Integer id;
	private Integer numero;
	private String resume;
	private Integer anneeDiffusion;
	private Integer idStatut;
	private Integer idSerie;
	
	
	
	public Saison(Integer id, Integer numero, String resume, Integer anneeDiffusion, Integer idStatut,
			Integer idSerie) {
		this(numero,resume,anneeDiffusion,idStatut,idSerie);
		this.id = id;

	}
	
	public Saison(Integer numero, String resume, Integer anneeDiffusion, Integer idStatut, Integer idSerie) {
		this.numero = numero;
		this.resume = resume;
		this.anneeDiffusion = anneeDiffusion;
		this.idStatut = idStatut;
		this.idSerie = idSerie;
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
	public Integer getIdStatut() {
		return idStatut;
	}
	public void setIdStatut(Integer idStatut) {
		this.idStatut = idStatut;
	}
	public Integer getIdSerie() {
		return idSerie;
	}
	public void setIdSerie(Integer idSerie) {
		this.idSerie = idSerie;
	}

	
}
