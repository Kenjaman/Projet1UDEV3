package mynetflixjsp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class AccueilControleurServlet extends HttpServlet {

	private static final String VUE_ACCUEIL = "/WEB-INF/jsp/accueil.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req, resp);
	}
}
