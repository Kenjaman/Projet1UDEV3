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

@WebServlet("/")
public class AccueilControleurServlet extends HttpServlet {
	private static final String VUE_CONNEXION = "/WEB-INF/jsp/accueil.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Context envContext;
		try {
			envContext = InitialContext.doLookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("BddMyNetflix");
			
			try (Connection connection = bddMyNetflix.getConnection()) {
				System.out.println("ploppppp");
				System.out.println("Connexion Reussie");
				DAOSaison testDAO = new DAOSaison(dataSource);
				for(Saison saison : testDAO.getSaisons()) {
					System.out.println(saison.getNumero());
				}
				getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// On récupère la source de données dans le contexte java:/comp/env


	}

}