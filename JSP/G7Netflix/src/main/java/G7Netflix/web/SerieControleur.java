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
import G7Netflix.modele.Pays;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

@WebServlet("/series")
public class SerieControleur extends HttpServlet {
	private static final String VUE_FORMULAIRE_SERIE = "/WEB-INF/jsp/ajoutSerie.jsp";
	private static final String VUE_AFFICHAGE = "/WEB-INF/jsp/affichageListe.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;
	private DAOSerie serieDAO;
	private DAOStatut statutDAO;
	private DAOAffectation affDAO;
	private DAOPays pays;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		serieDAO = new DAOSerie(bddMyNetflix);
		statutDAO = new DAOStatut(bddMyNetflix);
		affDAO = new DAOAffectation(bddMyNetflix);
		pays = new DAOPays(bddMyNetflix);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			req.setAttribute("entiteTraiter", "series");
			if(req.getParameter("action")!=null) {
				req.getServletContext().setAttribute("statuts", statutDAO.getStatuts(affDAO.getAffectation("serie")));
				req.getServletContext().setAttribute("series", serieDAO.getSeries());
				req.getServletContext().setAttribute("paysOrigines", pays.getPays());
				if(req.getParameter("action").equals("ajouter")) {
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SERIE).forward(req, resp);
				}else if(req.getParameter("action").equals("modifier")) {//Transfert vers la page de modification 
					req.setAttribute("serie", serieDAO.getSerie(Integer.valueOf(req.getParameter("id"))));
					req.getServletContext().getRequestDispatcher(VUE_FORMULAIRE_SERIE).forward(req, resp);
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
			String nom = req.getParameter("nom");
			String nomOriginal = req.getParameter("nomOriginal");
			Integer anneeParution = Integer.valueOf(req.getParameter("anneeParution"));
			String synopsys = req.getParameter("synopsys");
			Statut statut = statutDAO.getStatut((Integer.valueOf(req.getParameter("statut"))));
			Pays paysOrigine = pays.getPays(Integer.valueOf(req.getParameter("paysOrigine")));
			Serie serie = new Serie(nom,nomOriginal,anneeParution,synopsys,statut,paysOrigine);
			//		System.out.println("Statut "+req.getParameter("statut"));
			//		System.out.println("Pays Orginine "+req.getParameter("paysOrigine"));

			//		Serie serieAjout = new Serie(nom,nomOriginal,anneeParution);
			
				serieDAO.addSerie(serie);
				System.out.println(serie);
			if(req.getParameter("action").equals("supprimer")){
				//			serie.updateSerie(Integer.valueOf(req.getParameter("id")));
			}else if(req.getParameter("action").equals("supprimer")) {
				//			serie.supprimerSerie(Integer.valueOf(req.getParameter("id")));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

