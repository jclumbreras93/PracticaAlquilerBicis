
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EnviarCorreoServlet
 */
@WebServlet("/EnviarCorreoServlet")
public class EnviarCorreoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnviarCorreoServlet() {
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
		HttpSession sesion = request.getSession(false);

		response.getWriter().append("<html>\n" + "		  <head>\n" + "		    <title>RESERVAR</title>\n"
				+ "		        <meta charset=\"utf-8\">\n"
				+ "		  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
				+ "		  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n"
				+ "		  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n"
				+ "		  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n"
				+ "		  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n"
				+ "		  </head>\n" + "		  <body>\n" + "		    <div class=\"container\">\n"
				+ "		    <h1 align=\"center\"><font face=\"Arial\">ENVIAR EMAIL DE RESERVAS</font></h1>\n"
				+ "		    <form action=\"confirmarCorreo\"\n" + "		          method=post>\n"
				+ "		      <font face=\"Arial\">Email:</font><br>\n"
				+ "		      <input type=\"text\" name=\"email\" size=\"60\">\n" + "		      \n"
				+ "		      <br><br><input type=\"submit\" class=\"btn btn-info\" value=\"Enviar\">\n"
				+ "		     \n" + "		    </form>\n" + "		      </div>\n" + "		  </body>\n"
				+ "		</html>");

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
