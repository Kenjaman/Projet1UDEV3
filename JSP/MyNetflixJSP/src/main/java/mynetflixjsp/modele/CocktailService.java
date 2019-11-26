package mynetflixjsp.modele;

import java.util.ArrayList;
import java.util.List;

public class CocktailService {
	
	private List<Cocktail> listeCocktails;
	
	public CocktailService() {
		creerListeCocktails();
	}
	
	private void creerListeCocktails() {
		listeCocktails = new ArrayList<Cocktail>();
		listeCocktails.add(new Cocktail(1, "Blue Lagoon", "Vodka, curaçao bleu, jus de citron"));
		listeCocktails.add(new Cocktail(2, "Caipirinha", "Cachaça, citron vert, sucre"));
		listeCocktails.add(new Cocktail(3, "Margarita", "Tequila, cointreau, jus de citrons verts"));
		listeCocktails.add(new Cocktail(4, "Mojito", "Rhum cubain, jus de citrons verts, feuilles de menthe, eau gazeuse, sirop de sucre de canne"));
		listeCocktails.add(new Cocktail(5, "Piña Colada", "Rhum blanc, rhum ambré, jus d'ananas, lait de coco"));
	}

	public List<Cocktail> getListeCocktails() {
		return listeCocktails;
	}
	
	public Cocktail getCocktail(int numero) {
		for (Cocktail cocktail : listeCocktails) {
			if (cocktail.getId() == numero) {
				return cocktail;
			}
		}
		return null;
	}
	
}
