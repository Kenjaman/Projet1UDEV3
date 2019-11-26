package mynetflixjsp.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mynetflixjsp.modele.CocktailService;

@WebServlet(urlPatterns = "/commande", loadOnStartup = 0)
public class CommandeControleurServlet extends HttpServlet {

	private static final String VUE_COMMANDE = "/WEB-INF/jsp/commande.jsp";

	@Override
	public void init() throws ServletException {
		CocktailService cocktailService = new CocktailService();
		// on place le service en portée "application" pour qu'il puisse être récupéré plus tard.
		getServletContext().setAttribute("cocktailService", cocktailService);
		// on place la liste des cocktails en portée "application" pour que les vues puissent y accéder.
		getServletContext().setAttribute("listeCocktails", cocktailService.getListeCocktails());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(VUE_COMMANDE);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CocktailService cocktailService = getCocktailService();
		// TODO réaliser la commande
	}

	private CocktailService getCocktailService() {
		return (CocktailService) getServletContext().getAttribute("cocktailService");
	}
}
