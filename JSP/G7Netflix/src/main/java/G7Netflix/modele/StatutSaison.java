package G7Netflix.modele;

public enum StatutSaison {

	Tournage(1, "En cours de tournage"),
	Annulée(4, "Annulée"),
	Preparation(13,"En préparation"),
    Production(10,"En production"),
    Terminée(7,"Terminée");

    private final Integer id;
    private final String libelle;

 StatutSaison(Integer id, String libelle) {
        this.id=id;
        this.libelle=libelle;
    }

public Integer getId() {
	return id;
}

public String getLibelle() {
	return libelle;
}
	
 
}