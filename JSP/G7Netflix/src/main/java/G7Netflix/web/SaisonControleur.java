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
import G7Netflix.modele.Saison;

@WebServlet("/saison")
public class SaisonControleur extends HttpServlet {
	private static final String VUE_AJOUT = "/WEB-INF/jsp/ajoutSaison.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;

	public void init() {
		Context envContext;
		try {
			envContext = InitialContext.doLookup("java:/comp/env");
			//On récupère la source de données dans le contexte java:/comp/env
			bddMyNetflix = (DataSource) envContext.lookup("BddMyNetflix");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DAOSaison saison = new DAOSaison(bddMyNetflix.getConnection());
			req.setAttribute("listSaison", saison.getSaisons());
//			req.getServletContext().getRequestDispatcher(VUE_AJOUT).forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
	
