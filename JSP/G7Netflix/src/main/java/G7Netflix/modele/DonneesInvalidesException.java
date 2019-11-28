package G7Netflix.modele;

import java.util.List;

public class DonneesInvalidesException extends Exception {
	
	private List<Erreur> erreurs;

	public DonneesInvalidesException(List <Erreur> erreurs) {
		this.erreurs = erreurs;
	}
	
	public List<Erreur> getErreurs() {
		return erreurs;
	}

}