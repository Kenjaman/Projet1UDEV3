package G7Netflix.modele;

public class Stream {

	private Integer id;
	private Integer idEpisode;
	private Integer idPlateforme;
	private Integer idQualite;
	private String url;
	
	public Stream(int id, int idEpisode, int idPlateforme, int idQualite, String url) {
		
		this.id = id;
		this.idEpisode = idEpisode;
		this.idPlateforme = idPlateforme;
		this.idQualite = idQualite;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEpisode() {
		return idEpisode;
	}

	public void setIdEpisode(Integer idEpisode) {
		this.idEpisode = idEpisode;
	}

	public Integer getIdPlateforme() {
		return idPlateforme;
	}

	public void setIdPlateforme(Integer idPlateforme) {
		this.idPlateforme = idPlateforme;
	}

	public Integer getIdQualite() {
		return idQualite;
	}

	public void setIdQualite(Integer idQualite) {
		this.idQualite = idQualite;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
