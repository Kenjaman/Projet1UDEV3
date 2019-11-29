package G7Netflix.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import G7Netflix.jdbc.DAOEpisode;
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

public class EpisodeControleur extends HttpServlet {
	private static final String VUE_FORMULAIRE_EPISODE = "/WEB-INF/jsp/ajoutEpisode.jsp";
	private static final String VUE_AFFICHAGE = "/WEB-INF/jsp/affichageListe.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;

	private DAOEpisode episodeDAO;
	private DAOPublic publicDAO;
	private DAOStatut statutDAO;
	private DAOSaison saisonDAO;
	
	@Override
	public void init() throws ServletException {
		episodeDAO = new DAOEpisode(bddMyNetflix);
		publicDAO = new DAOPublic(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		saisonDAO = new DAOSaison(bddMyNetflix);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			req.setAttribute("entiteTraiter", "episodes");
			DAOSerie serieDAO = new DAOSerie(bddMyNetflix);
			Serie serie = serieDAO.getSerie(Integer.valueOf(req.getParameter("id")));
			if(req.getParameter("action")!=null) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String numero = req.getParameter("numero");
			String titre = req.getParameter("titre");
			String titreOriginal = req.getParameter("titreoriginal");
			String duree = req.getParameter("duree");
			String resume = req.getParameter("resume");
			String dateRealisation = req.getParameter("daterealisation");
			String datePremiereDiffusion = req.getParameter("date_premiere_diffusion");
			Public publics = publicDAO.getPublics(Integer.valueOf(req.getParameter("public")));
			Statut statut = statutDAO.getStatut(Integer.valueOf(req.getParameter("statut")));
			Saison saison = saisonDAO.getSaison(Integer.valueOf(req.getParameter("saison")));
			if(req.getParameter("action").equals("modifier")) {
				Integer idEpisode = Integer.valueOf(req.getParameter("id"));
				Episode episodeAUpdate = new Episode(numero, titre, titreOriginal, duree, resume, dateRealisation, datePremiereDiffusion, publics, statut, saison);
				episodeAUpdate.setId(idEpisode);
				episodeDAO.updateEpisode(episodeAUpdate);
			}
			else {
				Episode episode = new Episode(numero, titre, titreOriginal, duree, resume, dateRealisation, datePremiereDiffusion, publics, statut, saison);
				System.out.println(episode + " ajouté");
				episodeDAO.addEpisode(episode);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

