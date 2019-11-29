package G7Netflix.web;


import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import G7Netflix.jdbc.DAOAffectation;
import G7Netflix.jdbc.DAOPays;
import G7Netflix.jdbc.DAOSerie;
import G7Netflix.jdbc.DAOStatut;
import G7Netflix.modele.Affectation;
import G7Netflix.modele.DonneesInvalidesException;
import G7Netflix.modele.Pays;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

@WebServlet("/series")
public class SerieControleur extends HttpServlet {
	private static final String VUE_FORMULAIRE_SERIE = "/WEB-INF/jsp/ajoutSerie.jsp";
	private static final String VUE_AFFICHAGE = "/WEB-INF/jsp/affichageListeSerie.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;
	
	private DAOSerie serieDAO;
	private DAOStatut statutDAO;
	private DAOAffectation affDAO;
	private DAOPays paysDAO;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		serieDAO = new DAOSerie(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		affDAO = new DAOAffectation(bddMyNetflix);
		paysDAO = new DAOPays(bddMyNetflix);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("entiteeTraiter", "series");
		try {
			req.setAttribute("liste", serieDAO.getSeries());
			if(req.getParameter("action")!=null) { // Si on a appuyer sur un bouton
				req.getServletContext().setAttribute("series", serieDAO.getSeries()); //<----- POURQUOI j'ai fait ca ?? Où est ce que je m'en sert ??
				req.getServletContext().setAttribute("statuts", statutDAO.getStatuts(affDAO.getAffectation("serie")));
				req.getServletContext().setAttribute("paysOrigines", paysDAO.getPays());
				if(req.getParameter("action").equals("ajouter")) { // Appui sur ajouter
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SERIE).forward(req, resp);
				}else if(req.getParameter("action").equals("modifier")) {//Transfert vers la page de modification 
					req.setAttribute("serie", serieDAO.getSerie(Integer.valueOf(req.getParameter("id"))));
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SERIE).forward(req, resp);
				}else if(req.getParameter("action").equals("supprimer")) {// Appui sur supprimer
					this.doDelete(req, resp);
					req.setAttribute("liste", serieDAO.getSeries());
					req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
				}
			}else if(req.getParameter("id")!=null) { // Si on clique sur une serie
				req.getServletContext().setAttribute("idSerie", req.getParameter("id"));
				req.getServletContext().getRequestDispatcher("/saisons").forward(req, resp);
			}else {
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
			String nom = req.getParameter("nom");
			String nomOriginal = req.getParameter("nomOriginal");
			String anneeParution = req.getParameter("anneeParution");
			String synopsys = req.getParameter("synopsys");
			Statut statut = statutDAO.getStatut((Integer.valueOf(req.getParameter("statut"))));
			Pays paysOrigine = paysDAO.getPays(Integer.valueOf(req.getParameter("paysOrigine")));
			if(req.getParameter("action").equals("modifier")){
				Integer idSerie = Integer.valueOf(req.getParameter("id"));
				Serie serieAupdate =new Serie(nom,nomOriginal,anneeParution,synopsys,statut,paysOrigine);
				serieAupdate.setId(idSerie);
				serieDAO.updateSerie(serieAupdate);
			}else {
				Serie serie = new Serie(nom,nomOriginal,anneeParution,synopsys,statut,paysOrigine);
				System.out.println("ajout "+serie);
				serieDAO.addSerie(serie);
				resp.sendRedirect("series");
			}
		} catch (DonneesInvalidesException e) {
			req.setAttribute("erreursSerie", e.getErreurs());
//			req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SERIE).forward(req, resp);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Serie serieAsupp;
		try {
			serieAsupp = serieDAO.getSerie(Integer.valueOf(req.getParameter("id")));
			serieDAO.deleteSerie(serieAsupp);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

