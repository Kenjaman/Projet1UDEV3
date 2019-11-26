package G7Netflix.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
	private static final String VUE_CONNEXION = "/WEB-INF/jsp/connexion.jsp";

	private DataSource bddMyNetflix;

	//@Resource(name = "BddMyNetflix")
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try (Connection connection = bddMyNetflix.getConnection()) {
			System.out.println("Connexion Reussie");
			DAOSaison testDAO = new DAOSaison(connection);
			for(Saison saison : testDAO.getSaisons()) {
				System.out.println(saison.getNumero());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}