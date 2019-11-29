package G7Netflix.web;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import G7Netflix.jdbc.DAOSaison;
import G7Netflix.jdbc.DAOSerie;
import G7Netflix.modele.DonneesInvalidesException;
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;

@WebServlet("/saisons")
public class SaisonControleur extends HttpServlet {
	private static final String VUE_FORMULAIRE_SAISON = "/WEB-INF/jsp/ajoutSaison.jsp";
	private static final String VUE_AFFICHAGE = "/WEB-INF/jsp/affichageListe.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			req.setAttribute("entiteTraiter", "saisons");
			DAOSerie serieDAO = new DAOSerie(bddMyNetflix);
			Serie serie = serieDAO.getSerie(Integer.valueOf(req.getParameter("id")));
			DAOSaison saisonDAO = new DAOSaison(bddMyNetflix);
			if(req.getParameter("action")!=null) {
				if(req.getParameter("action").equals("ajouter")) {
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SAISON).forward(req, resp);
				}else if(req.getParameter("action").equals("modifier")) {//Transfere vers la page de modification 
					req.setAttribute("serie", serieDAO.getSerie(Integer.valueOf(req.getParameter("id"))));
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SAISON).forward(req, resp);
				}else if(req.getParameter("action").equals("supprimer")) {
					this.doPost(req, resp);
				}
			}else {
				req.setAttribute("liste", saisonDAO.getSaisons(serie));
				req.getServletContext().getRequestDispatcher(VUE_AFFICHAGE).forward(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DonneesInvalidesException e) {
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

