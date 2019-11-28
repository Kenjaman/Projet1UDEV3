package G7Netflix.modele;

import java.util.ArrayList;
import java.util.List;

public class Erreur {

	private String champs;
	private String message;
	
	public Erreur(String champs, String message){
		this.champs=champs;
		this.message=message;
	}

	public String getChamps() {
		return champs;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
