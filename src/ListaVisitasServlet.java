
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PuntosAlquilerServlet
 */
@WebServlet("/ListaVisitasServlet")
public class ListaVisitasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Persona> personas = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListaVisitasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);

		response.getWriter().append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
						+ "<html>\n" + "<head>\n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n"
						+ "<title></title>"
						+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">"
						+ "<script src=\"http://code.jquery.com/jquery-latest.js\">\n" + "\n" + "</script>\n"
						+ "<script>\n" + "	$(document).ready(function() {\n"
						+ "		$('#submit').click(function(event) {\n"
						+ "			var nombreVar = $('#nombre').val();\n"
						+ "			var apellidoVar = $('#apellido').val();\n"
						+ "			var edadVar = $('#edad').val();\n"
						+ "			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get\n"
						+ "			$.post('ListaVisitasServlet', {\n" + "				nombre : nombreVar,\n"
						+ "				apellido: apellidoVar,\n" + "				edad: edadVar\n"
						+ "			}, function(responseText) {\n" + "				$('#tabla').html(responseText);\n"
						+ "			});\n" + "		});\n" + "	});\n" + "</script>\n" + "</head>\n" + "<body>\n"
						+ "	<h2>Introduce tus datos para quedar registrado en el libro de visitas</h2>\n"
						+ "	<form id=\"form1\">\n" + "		Nombre:<input type=\"text\" id=\"nombre\" /> <br>\n"
						+ "		Apellido: <input type=\"text\" id=\"apellido\" /> <br>\n"
						+ "		Edad: <input type=\"text\" id=\"edad\" /> <br>\n"
						+ "		<input type=\"button\" id=\"submit\" value=\"Añadir\" /> \n" + "	</form>\n"
						+ "	<br>\n" + "	<!-- 	En este div metemos el contenido de la tabla con AJAX -->\n"
						+ "	<div id=\"tabla\"></div>\n"
						+ "<a href=\"logueo\" class=\"btn btn-info\" role=\"button\">Volver</a></body>\n" + "</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=iso-8859-1");
		PrintWriter out = response.getWriter();

		// Obtengo los datos de la peticion
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String edad = request.getParameter("edad");

		// Compruebo que los campos del formulario tienen datos para añadir a la tabla
		if (!nombre.equals("") && !apellido.equals("") && !edad.equals("")) {
			// Creo el objeto persona y lo añado al arrayList
			Persona persona = new Persona(nombre, apellido, edad);
			personas.add(persona);
		}

		out.println("<table style= cellspacing='1' bgcolor='#0099cc'>");
		out.println("<tr>");
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'> NOMBRE </td>");
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>APELLIDO</td>");
		out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>EDAD</td>");
		out.println("</tr>");
		for (int i = 0; i < personas.size(); i++) {
			Persona persona = personas.get(i);
			out.println("<tr>");
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + persona.getNombre() + "</td>");
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + persona.getApellido() + "</td>");
			out.println("<td style= rowspan='7' align='center' bgcolor='#f8f8f8'>" + persona.getEdad() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

}
