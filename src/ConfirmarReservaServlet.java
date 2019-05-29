
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReservaServlet
 */
@WebServlet("/ConfirmarReservaServlet")
public class ConfirmarReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmarReservaServlet() {
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
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String precio = request.getParameter("precio");

		sesion.setAttribute("id", id);
		sesion.setAttribute("nombre", nombre);
		sesion.setAttribute("precio", precio);

		response.getWriter().append("<html>\n" + "		  <head>\n" + "		    <title>RESERVAR</title>\n"
				+ "		        <meta charset=\"utf-8\">\n"
				+ "		  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
				+ "		  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n"
				+ "		  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n"
				+ "		  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n"
				+ "		  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n"
				+ "		  </head>\n" + "  <body>\n" + "  <h3>Usuario " + sesion.getAttribute("usuario")
				+ "</h3>\n <div class=\"row\">\n" + "		    <div class=\"container\">\n"
				+ "		    <h1 align=\"center\"><font face=\"Arial\">CONFIRMACION RESERVA</font></h1>\n"
				+ "		    \n" + "		      <h2>RESERVA</h2>\n" + "		      <div class=\"card\">\n"
				+ "		        <div class=\"card-body\">" + "<p>El id de la reserva es: " + sesion.getAttribute("id")
				+ "</p><p>La bicicleta es: " + sesion.getAttribute("nombre") + "</p>" + "<p>El precio es: "
				+ sesion.getAttribute("precio") + " Euros</p>" + "		      </div>\n"
				+ "		        <a href=\"obtenerPdf\" class=\"btn btn-info\" role=\"button\">Obtener Justificante</a>\n"
				+ "		        <a href=\"enviarCorreo\" class=\"btn btn-info\" role=\"button\">Enviar por Correo</a>\n"
				+ "		      </div>\n" + "		  </body>\n"
				+ "<a href=\"reservaBici\" class=\"btn btn-info\" role=\"button\">Volver</a> </body>\n" + "</html>");

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
