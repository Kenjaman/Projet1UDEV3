package G7Netflix.web;

import java.io.IOException;
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
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;

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
		// TODO Auto-generated method stub
		episodeDAO = new DAOEpisode(bddMyNetflix);
		saisonDAO = new DAOSaison(bddMyNetflix);
		publicDAO = new DAOPublic(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		serieDAO = new DAOSerie(bddMyNetflix);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("entiteTraiter", "episodes");
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
			if(req.getParameter("action")!=null) {
				//req.getServletContext().setAttribute("episodes", episodeDAO.getepisode(saisonDAO.getSerie()));
				//req.getServletContext().setAttribute("saisons", saisonDAO.getSaisons());
				if(req.getParameter("action").equals("ajouter")) {
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
				}else if(req.getParameter("action").equals("modifier")) {//Transfere vers la page de modification 
					req.setAttribute("serie", serieDAO.getSerie(Integer.valueOf(req.getParameter("id"))));
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_EPISODE).forward(req, resp);
				}else if(req.getParameter("action").equals("supprimer")) {
					this.doPost(req, resp);
				}
			}else {
				req.setAttribute("liste", serieDAO.getSeries());
				req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
			}
		} catch (SQLException | NumberFormatException | DonneesInvalidesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		DAOSerie serie = new DAOSerie(bddMyNetflix);
//		if(req.getParameter("action").equals("supprimer")) {
//			serie.supprimerSerie(Integer.valueOf(req.getParameter("id")));
//		}else {
//			serie.updateSerie(Integer.valueOf(req.getParameter("id")));
//		}
//	}

}

