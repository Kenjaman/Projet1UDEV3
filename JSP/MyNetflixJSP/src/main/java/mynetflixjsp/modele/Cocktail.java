package mynetflixjsp.modele;

public class Cocktail {

	private int id;
	private String nom;
	private String composition;

	public Cocktail(int id, String nom, String composition) {
		this.id = id;
		this.nom = nom;
		this.composition = composition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getComposition() {
		return composition;
	}

}
