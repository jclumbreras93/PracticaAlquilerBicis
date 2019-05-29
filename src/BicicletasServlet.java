
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BicicletasServlet
 */
@WebServlet("/BicicletasServlet")
public class BicicletasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BicicletasServlet() {
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

		UUID idReserva = UUID.randomUUID();
		HttpSession sesion = request.getSession(true);
		// Pagina de reserva de bicicletas
		response.getWriter().append("<!doctype html>\n" + "<html lang=\"en\">\n" + "  <head>\n"
				+ "    <!-- Required meta tags -->\n" + "    <meta charset=\"utf-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
				+ "\n" + "    <!-- Bootstrap CSS -->\n"
				+ "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n"
				+ "\n" + "    <title>Hello, world!</title>\n" + "  </head>\n" + "  <body>\n" + "  <h3>Usuario "
				+ sesion.getAttribute("usuario") + "</h3>\n <div class=\"row\">\n" + "    <div class=\"col-md-4\">\n"
				+ "      <div class=\"thumbnail\">\n" + "        <a href=\"confirmarRes?id=" + idReserva
				+ "&nombre=ORBEA&precio=75\" >\n"
				+ "          <img src=\"https://tse4.mm.bing.net/th?id=OIP.RZBd6ZQUi453DM3s6BHN5gHaE5&pid=15.1&P=0&w=275&h=183\" alt=\"Lights\" style=\"width:70%\">\n"
				+ "          <div class=\"caption\">\n" + "            <p>ORBEA</p>\n" + "          </div>\n"
				+ "        </a>\n" + "      </div>\n" + "    </div>\n" + "    <div class=\"col-md-4\">\n"
				+ "      <div class=\"thumbnail\">\n" + "        <a href=\"confirmarRes?id=" + idReserva
				+ "&nombre=MMR&precio=50\">\n"
				+ "          <img src=\"https://tse4.mm.bing.net/th?id=OIP.qN3_4SPpG_0TNedEEm977AHaE_&pid=15.1&P=0&w=231&h=157\" alt=\"Nature\" style=\"width:70%\">\n"
				+ "          <div class=\"caption\">\n" + "            <p>MMR</p>\n" + "          </div>\n"
				+ "        </a>\n" + "      </div>\n" + "    </div>\n" + "    <div class=\"col-md-4\">\n"
				+ "      <div class=\"thumbnail\">\n" + "        <a href=\"confirmarRes?id=" + idReserva
				+ "&nombre=TREK&precio=30\" >\n"
				+ "          <img src=\"https://tse2.mm.bing.net/th?id=OIP.yQw6TcRclDZk0c_2XklCvAHaFj&pid=15.1&P=0&w=203&h=153\" alt=\"Fjords\" style=\"width:63%\">\n"
				+ "          <div class=\"caption\">\n" + "            <p>TREK</p>\n" + "          </div>\n"
				+ "        </a>\n" + "      </div>\n" + "    </div>\n" + "  </div>"
				+ "<a href=\"logueo\" class=\"btn btn-info\" role=\"button\">Volver</a> </body>\n" + "</html>");
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
