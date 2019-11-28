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

@WebServlet("/accueil")
public class AccueilControleurServlet extends HttpServlet {
	private static final String VUE_CONNEXION = "/WEB-INF/jsp/accueil.jsp";

	@Resource(name = "BddMyNetflix")
	private DataSource bddMyNetflix;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(req, resp);
	}
	// On récupère la source de données dans le contexte java:/comp/env



}