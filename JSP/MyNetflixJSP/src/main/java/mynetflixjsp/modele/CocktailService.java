package mynetflixjsp.modele;

import java.util.ArrayList;
import java.util.List;

public class CocktailService {
	
	private List<Saison> listeCocktails;
	
	public CocktailService() {
		creerListeCocktails();
	}
	
	private void creerListeCocktails() {
		listeCocktails = new ArrayList<Saison>();
		listeCocktails.add(new Saison(1, "Blue Lagoon", "Vodka, curaçao bleu, jus de citron"));
		listeCocktails.add(new Saison(2, "Caipirinha", "Cachaça, citron vert, sucre"));
		listeCocktails.add(new Saison(3, "Margarita", "Tequila, cointreau, jus de citrons verts"));
		listeCocktails.add(new Saison(4, "Mojito", "Rhum cubain, jus de citrons verts, feuilles de menthe, eau gazeuse, sirop de sucre de canne"));
		listeCocktails.add(new Saison(5, "Piña Colada", "Rhum blanc, rhum ambré, jus d'ananas, lait de coco"));
	}

	public List<Saison> getListeCocktails() {
		return listeCocktails;
	}
	
	public Saison getCocktail(int numero) {
		for (Saison cocktail : listeCocktails) {
			if (cocktail.getId() == numero) {
				return cocktail;
			}
		}
		return null;
	}
	
}
