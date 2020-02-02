package G7Netflix.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import G7Netflix.jdbc.DAOAffectation;
import G7Netflix.jdbc.DAOEpisode;
import G7Netflix.jdbc.DAOPays;
import G7Netflix.jdbc.DAOPublic;
import G7Netflix.jdbc.DAOSaison;
import G7Netflix.jdbc.DAOSerie;
import G7Netflix.jdbc.DAOStatut;
import G7Netflix.modele.Affectation;
import G7Netflix.modele.DonneesInvalidesException;
import G7Netflix.modele.Episode;
import G7Netflix.modele.Public;
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

@WebServlet("/episodes")
public class EpisodeControleur extends HttpServlet {
	private static final String VUE_FORMULAIRE_EPISODE = "/WEB-INF/jsp/ajoutEpisode.jsp";
	private static final String VUE_AFFICHAGE = "/WEB-INF/jsp/affichageListeEpisodes.jsp";
	private static final String VUE_AFFICHAGE_EPISODE = "/WEB-INF/jsp/affichageEpisode.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;

	private DAOEpisode episodeDAO;
	private DAOSaison saisonDAO;
	private DAOPublic publicDAO;
	private DAOStatut statutDAO;
	private DAOSerie serieDAO;
	private DAOAffectation affDAO;
	private Saison saisonEpisode;
	private Serie serieSaison;

	@Override
	public void init() throws ServletException {
		episodeDAO = new DAOEpisode(bddMyNetflix);
		saisonDAO = new DAOSaison(bddMyNetflix);
		publicDAO = new DAOPublic(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		serieDAO = new DAOSerie(bddMyNetflix);
		affDAO = new DAOAffectation(bddMyNetflix);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("entiteeTraiter", "episodes");
		System.out.println("Nous somme dans /episodes");
		try {
			List<Episode> episodes = new ArrayList<Episode>();
			if(req.getServletContext().getAttribute("saison")!=null && req.getServletContext().getAttribute("serie")!=null){// on arrive d'un clique sur une serie puis d'une saison
				serieSaison = (Serie) req.getServletContext().getAttribute("serie");
				Saison saisonEpisode = (Saison) req.getServletContext().getAttribute("saison");
				System.out.println("getServletcontext :"+req.getServletContext().getAttribute("saison"));
				episodes = episodeDAO.getEpisodes(saisonEpisode);
				req.setAttribute("liste", episodes);
				if(req.getParameter("idepisode")!=null) { // Si on clique sur un episode
					Integer idEpisode = Integer.valueOf(req.getParameter("idepisode"));
					req.getServletContext().setAttribute("episode", episodeDAO.getEpisode(idEpisode,saisonEpisode));
					req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE_EPISODE).forward(req, resp);
				}else {
					req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
				}
			}else { // Si on arrive depuis le menu de navigation
				episodes=episodeDAO.getAllEpisodes();
				req.setAttribute("liste", episodes);
				if(req.getParameter("idsaison")!=null && req.getParameter("idserie")!=null) {
					Integer idSaison = Integer.valueOf(req.getParameter("idsaison"));
					Integer idSerie = Integer.valueOf(req.getParameter("idserie"));
					serieSaison = serieDAO.getSerie(idSerie);
					saisonEpisode = saisonDAO.getSaison(idSaison, serieSaison);
					req.getServletContext().setAttribute("saison", saisonEpisode);
					req.getServletContext().setAttribute("serie", serieSaison);
				}
			}
			if (req.getParameter("action")!=null) { 
				Affectation aff = affDAO.getAffectation("episode");
				List <Public> publics = publicDAO.getPublics();
				List <Statut> statuts = statutDAO.getStatuts(aff);
				List <Saison> saisons = saisonDAO.getSaisons(serieSaison);
				req.setAttribute("publics", publics);
				req.setAttribute("saisons", saisons);
				req.setAttribute("statuts", statuts);
				if (req.getParameter("action").equals("ajouter")) {
					if(serieSaison != null && saisonEpisode != null) {
						Integer numDernierEpisode = saisonDAO.getDernierEpisode(saisonEpisode);
						req.setAttribute("dernierEpisode", numDernierEpisode);
					}
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
				}else if (req.getParameter("action").equals("modifier")) {
					req.getServletContext().setAttribute("episode",episodeDAO.getEpisode(Integer.valueOf(req.getParameter("idepisode")),saisonEpisode));
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
				}
				else if (req.getParameter("action").equals("supprimer")) {
					System.out.println("todo Suppression");
				}
			}else {
				req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
			}

		}
		catch (SQLException | NumberFormatException | DonneesInvalidesException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Serie serieSaison = (Serie) req.getServletContext().getAttribute("serie");
			Integer idSaison = Integer.valueOf(req.getParameter("idsaison"));
			Saison saisonEpisode = saisonDAO.getSaison(idSaison, serieSaison);
			Integer numEpisode = Integer.valueOf(req.getParameter("numeroEpisode"));
			String titre = req.getParameter("titreEpisode");
			String titreOriginal = req.getParameter("titreEpisodeOriginal");
			Integer duree = Integer.valueOf(req.getParameter("dureeEpisode"));
			Date dateReal = Date.valueOf(req.getParameter("dateRealisation"));
			Date dateDiff = Date.valueOf(req.getParameter("datePremiereDiffusion"));
			String resume = req.getParameter("resume");
			Public publics = publicDAO.getPublic(req.getParameter("public"));
			Statut statut = statutDAO.getStatut((Integer.valueOf(req.getParameter("statutSaison"))));
			if (req.getParameter("action").equals("modifier")) {
				Integer id = Integer.valueOf(req.getParameter("idepisode"));
				Episode episodeMaj = new Episode(numEpisode, titre, titreOriginal,duree, resume, dateReal, dateDiff, publics, statut, saisonEpisode);
				episodeMaj.setId(id);
				episodeDAO.updateEpisode(episodeMaj);
				resp.sendRedirect("episodes");
			}
			else {
				Episode episodeAj = new Episode(numEpisode, titre, titreOriginal,duree, resume, dateReal, dateDiff, publics, statut, saisonEpisode);
				System.out.println("ajout " + episodeAj);
				episodeDAO.addEpisode(episodeAj);
				resp.sendRedirect("episodes");
			}
		}
		catch (DonneesInvalidesException err) {
			req.setAttribute("erreurs", err.getErreurs());
			req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);			
			err.printStackTrace();
		}
		catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

}

