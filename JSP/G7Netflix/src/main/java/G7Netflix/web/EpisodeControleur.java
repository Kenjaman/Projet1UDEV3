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

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;
	
	private DAOEpisode episodeDAO;
	private DAOSaison saisonDAO;
	private DAOPublic publicDAO;
	private DAOStatut statutDAO;
	private DAOSerie serieDAO;

	@Override
	public void init() throws ServletException {
		episodeDAO = new DAOEpisode(bddMyNetflix);
		saisonDAO = new DAOSaison(bddMyNetflix);
		publicDAO = new DAOPublic(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		serieDAO = new DAOSerie(bddMyNetflix);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("entiteeTraiter", "episodes");
		try {
			Integer idSerie = Integer.valueOf(req.getServletContext().getAttribute("idSerie").toString());
			Serie serieSaison = serieDAO.getSerie(idSerie);
			req.setAttribute("serie", serieSaison);
			Integer idSaison = Integer.valueOf(req.getServletContext().getAttribute("idSaison").toString());
			Saison saisonEpisode = saisonDAO.getSaison(idSaison, serieSaison);
			req.setAttribute("saison", saisonEpisode);
			List<Episode> episodes = new ArrayList<Episode>();
			episodes = episodeDAO.getEpisodes(saisonEpisode);
			req.setAttribute("liste", episodes);
			if (req.getParameter("action")!=null) {
//				req.getServletContext().setAttribute("episodes", episodeDAO.getepisode(saisonDAO.getSerie()));
//				req.getServletContext().setAttribute("saisons", saisonDAO.getSaisons());
				if (req.getParameter("action").equals("ajouter")) {
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
				}
				else if (req.getParameter("action").equals("modifier")) {
					req.setAttribute("serie", serieDAO.getSerie(Integer.valueOf(req.getParameter("idserie"))));
					req.setAttribute("saison", saisonDAO.getSaison(Integer.valueOf(req.getParameter("idsaison")), serieSaison));
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
				}
//				else if (req.getParameter("action").equals("supprimer")) {
//					this.doPost(req, resp);
//				}
			}
			else {
				req.setAttribute("liste", serieDAO.getSeries());
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
			Integer idSerie = Integer.valueOf(req.getParameter("idserie"));
			Serie serieSaison = serieDAO.getSerie(idSerie);
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

