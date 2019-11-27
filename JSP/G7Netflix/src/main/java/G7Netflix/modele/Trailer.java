package G7Netflix.modele;

public class Trailer {

	private Integer id;
	private Integer idEpisode;
	private Integer idPlateforme;
	private String url;
	
	public Trailer(int id, int idEpisode, int idPlateforme, String url) {
		this.id = id;
		this.idEpisode = idEpisode;
		this.idPlateforme = idPlateforme;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
