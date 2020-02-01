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
			List<Episode> episodes = new ArrayList<Episode>();
			if(req.getParameter("idsaison")!=null && req.getParameter("idserie")!= null) {
				Integer idSerie = Integer.valueOf(req.getParameter("idserie"));
				Serie serieSaison = serieDAO.getSerie(idSerie);
				req.setAttribute("serie", serieSaison);
				Integer idSaison = Integer.valueOf(req.getParameter("idsaison"));
				Saison saisonEpisode = saisonDAO.getSaison(idSaison, serieSaison);
				req.setAttribute("saison", saisonEpisode);
				episodes = episodeDAO.getEpisodes(saisonEpisode);
				req.setAttribute("liste", episodes);
				if (req.getParameter("action")!=null) { 
					if (req.getParameter("action").equals("ajouter")) {
						List <Public> publics = publicDAO.getPublics();
						req.setAttribute("publics", publics);
						Affectation aff = affDAO.getAffectation("episode"); // <--- NIQUES TA MERE TOI !!! 
						System.out.println(aff);
						List <Statut> statuts = statutDAO.getStatuts(aff);
						req.setAttribute("statuts", statuts);
						if(idSerie != 0 && idSaison != 0) {
							Integer numDernierEpisode = saisonDAO.getDernierEpisode(saisonEpisode);
							req.setAttribute("dernierEpisode", numDernierEpisode);
						}
						req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
					}
					else if (req.getParameter("action").equals("modifier")) {
						//req.setAttribute("serie", serieDAO.getSerie(Integer.valueOf(req.getParameter("idserie"))));
						//req.setAttribute("saison", saisonDAO.getSaison(Integer.valueOf(req.getParameter("idsaison")), serieSaison));
						req.setAttribute("episode",episodeDAO.getEpisode(Integer.valueOf(req.getParameter("idepisode")),saisonEpisode));
						req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
					}
					//				else if (req.getParameter("action").equals("supprimer")) {
					//					this.doPost(req, resp);
					//				}
				}
				else if(req.getParameter("idepisode")!=null) { // Si on clique sur un episode
					Integer idEpisode = Integer.valueOf(req.getParameter("idepisode"));
					req.setAttribute("episode", episodeDAO.getEpisode(idEpisode,saisonEpisode));
					req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE_EPISODE).forward(req, resp);
				}else {
					req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
				}
			}else { // Si on arrive depuis le menu de navigation
				episodes=episodeDAO.getAllEpisodes();
				req.setAttribute("liste", episodes);
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
			Integer idSerie = req.getParameter("idserie") != null?Integer.valueOf(req.getParameter("idserie")):0;
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

