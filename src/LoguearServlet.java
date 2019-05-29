
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoguearServlet
 */
@WebServlet("/LoguearServlet")
public class LoguearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String usuario2 = "";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		usuario2 = getServletContext().getInitParameter("usuario2");

	}

	public LoguearServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession sesion = request.getSession(true);

		// String nombre = (String) sesion.getAttribute(name);
		response.getWriter().append("<!doctype html>\n" + "<html lang=\"en\">\n" + "  <head>\n"
				+ "    <!-- Required meta tags -->\n" + "    <meta charset=\"utf-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
				+ "\n" + "    <!-- Bootstrap CSS -->\n"
				+ "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n"
				+ "\n" + "    <title>Hello, world!</title>\n" + "  </head>\n" + "  <body>\n" + "  <h1>Bienvenido "
				+ sesion.getAttribute("usuario") + "</h1>\n" + "   <form action=\"logueo\" method=get>\n"
				+ "  <a href=\"reservaBici\" class=\"btn btn-info\" role=\"button\">Ver Bicicletas</a>\n" + "  \n"
				+ "  <a href=\"puntosAlq\" class=\"btn btn-info\" role=\"button\">Libro de visitas</a>\n" + "  \n"
				+ "  			<a href=\"cerrarSes\" class=\"btn btn-info\" role=\"button\">Volver</a>\n"
				+ "</form> \n" + "  \n" + "  </body>\n" + "</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
