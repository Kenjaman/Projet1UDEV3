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
import G7Netflix.jdbc.DAOPays;
import G7Netflix.jdbc.DAOSaison;
import G7Netflix.jdbc.DAOSerie;
import G7Netflix.jdbc.DAOStatut;
import G7Netflix.modele.DonneesInvalidesException;
import G7Netflix.modele.Pays;
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

@WebServlet("/saisons")
public class SaisonControleur extends HttpServlet {
	private static final String VUE_FORMULAIRE_SAISON = "/WEB-INF/jsp/ajoutSaison.jsp";
	private static final String VUE_AFFICHAGE = "/WEB-INF/jsp/affichageListeSaisons.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;

	private DAOSerie serieDAO;
	private DAOSaison saisonDAO;
	private DAOStatut statutDAO;
	private DAOAffectation affDAO;
	private Serie serieSaison;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		serieDAO = new DAOSerie(bddMyNetflix);
		saisonDAO = new DAOSaison(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		affDAO = new DAOAffectation(bddMyNetflix);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("on est dans la /saison servlet");
		req.setAttribute("entiteeTraiter", "saisons");
		try {
			List<Saison> saisons = new ArrayList<Saison>();
			if(req.getParameter("idserie")!=null || req.getServletContext().getAttribute("serie")!= null) { // Si l'on viens d'une série
				System.out.println("req.getAttribut :"+req.getAttribute("idSerie"));
				System.out.println("req.getServletContext().getAttribut :"+req.getServletContext().getAttribute("serie"));
				serieSaison = (Serie) req.getServletContext().getAttribute("serie");
				//				req.getServletContext().setAttribute("serie", serieSaison);
				saisons = saisonDAO.getSaisons(serieSaison);
				req.setAttribute("liste", saisons);
				if(req.getParameter("idsaison")!=null) { //Si on clique sur une saison
					req.setAttribute("entiteeTraiter", "episodes");
					System.out.println("Envoie vers /episodes");
					req.getServletContext().setAttribute("saison", saisonDAO.getSaison(Integer.valueOf(req.getParameter("idsaison")),serieSaison));
					req.getServletContext().getRequestDispatcher("/episodes").forward(req, resp); // Envoie vers episodeControlleur
				}
			}else { // Si l'on viens de la navigation 
				saisons=saisonDAO.getAllSaisons();
				req.setAttribute("liste", saisons);
				req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
			}
			if(req.getParameter("action")!=null) { // Si on a appuyer sur un bouton
				req.getServletContext().setAttribute("statuts", statutDAO.getStatuts(affDAO.getAffectation("saison")));
				if(req.getParameter("action").equals("ajouter")) { // Appui sur ajouter
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SAISON).forward(req, resp);
				}else {
					serieSaison=serieDAO.getSerie(Integer.valueOf(req.getParameter("idserie")));
					req.getServletContext().setAttribute("serie", serieSaison);
					req.getServletContext().setAttribute("saison", saisonDAO.getSaison(Integer.valueOf(req.getParameter("idsaison")),serieSaison));
					if(req.getParameter("action").equals("modifier")) {//Transfert vers la page de modification
						req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SAISON).forward(req, resp);
					}else if(req.getParameter("action").equals("supprimer")) {// Appui sur supprimer
						this.doDelete(req, resp);
						req.setAttribute("liste", serieDAO.getSeries());
						req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
					}
				}
			}else {
				System.out.println("Saison action vide");
				req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
			}
		} catch (SQLException | NumberFormatException | DonneesInvalidesException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer idSerie = Integer.valueOf(req.getParameter("idSerie"));
			Serie serieSaison = serieDAO.getSerie(idSerie);
			Integer numSaison = Integer.valueOf(req.getParameter("numeroSaison"));
			Integer anneDif = Integer.valueOf(req.getParameter("anneeDiffusion"));
			String resume = req.getParameter("resume");
			// BESOIN DE RECUPERER LE STATUT
			Statut statut = statutDAO.getStatut(req.getParameter("statutSaison"));
			if (req.getParameter("action").equals("modifier")) {
				Integer id = Integer.valueOf(req.getParameter("idSaison"));
				Saison saisonMaj =new Saison (id,numSaison, resume,anneDif,statut,serieSaison);
				saisonDAO.updateSaison(saisonMaj);
				resp.sendRedirect("saisons");
			}
			else {
				Saison saisonAj = new Saison(numSaison, resume, anneDif, statut, serieSaison);
				System.out.println("ajout "+ saisonAj );
				saisonDAO.addSaison(saisonAj);
				resp.sendRedirect("saisons");
			}
		}
		catch (DonneesInvalidesException err) {
			req.setAttribute("erreurs", err.getErreurs());
			req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SAISON).forward(req, resp);			
			err.printStackTrace();
		}
		catch (SQLException | NumberFormatException e) {
			req.setAttribute("erreurs", e.getMessage());
			req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SAISON).forward(req, resp);			
			e.printStackTrace();		
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Saison saisonSupp = (Saison) req.getServletContext().getAttribute("saison");
		try {
			saisonDAO.deleteSaison(saisonSupp);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}


}

