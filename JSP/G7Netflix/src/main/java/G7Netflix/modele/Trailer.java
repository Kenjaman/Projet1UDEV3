package G7Netflix.modele;

import java.util.ArrayList;
import java.util.List;

public class Trailer {

	private Integer id;
	private Integer idEpisode;
	private Integer idPlateforme;
	private String url;
	
	public Trailer(int id, int idEpisode, int idPlateforme, String url) {
		
		
		List<Erreur> errTrailer = new ArrayList<Erreur>();
		
		this.id = id;
		errTrailer.add(new Erreur("id","Id Inexistant"));
		this.idEpisode = idEpisode;
		errTrailer.add(new Erreur("id","Id de l'épisode Inexistant"));
		this.idPlateforme = idPlateforme;
		errTrailer.add(new Erreur("id","Id de la plateforme Inexistant"));
		this.url = url;
		errTrailer.add(new Erreur("url","Vous devez rentrer une URL"));
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
