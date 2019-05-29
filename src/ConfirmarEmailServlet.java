
import java.io.IOException;
import java.io.Writer;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Properties;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient.AUTH_METHOD;

import com.darwinsys.spdf.Text;

/**
 * Servlet implementation class ConfirmarEmailServlet
 */
@WebServlet("/ConfirmarEmailServlet")
public class ConfirmarEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private String usuario2 = "";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		usuario2 = getServletContext().getInitParameter("usuario2");

	}

	String precio = null;

	String id = null;
	String nombre = null;

	public ConfirmarEmailServlet() {
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

		String destinatario = (String) request.getParameter("email");
		usuario2 = (String) sesion.getAttribute("usuario");
		id = (String) sesion.getAttribute("id");
		nombre = (String) sesion.getAttribute("nombre");
		precio = (String) sesion.getAttribute("precio");
		System.out.println(request.getParameter("email"));
		String asunto = "Confirmacion de reserva";
		String cuerpo = "Numero de reservas";
		if (enviarEmail(destinatario)) {
			response.getWriter().append("<html>\n" + "  <head>\n" + "    <title>RESERVAR</title>\n"
					+ "        <meta charset=\"utf-8\">\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n"
					+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n"
					+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n"
					+ "  </head>\n" + "  <body>\n" + "    <div class=\"container\">\n"
					+ "    <h1 align=\"center\"><font face=\"Arial\">CONFIRMACION JUSTIFICANTE CREADO</font></h1>"
					+ "</body>\n" + "</html>");

		}

	}

	private boolean enviarEmail(String destinatario) {
		// TODO Auto-generated method stub
		boolean envio = false;
		AuthenticatingSMTPClient cliente = new AuthenticatingSMTPClient();

		// datos del usuario y del servidor
		String servidor = "smtp.gmail.com";
		String usuario = "dam2.augustobriga@gmail.com"; // en este ejemplo usamos una cuenta gmail
		String pwd = "2dam2019";
		int puerto = 587;

		int respuesta;
		// Creaci�n de una clave para establecer un canal serguro
		try {
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(null, null);
			KeyManager km = kmf.getKeyManagers()[0];
			cliente.connect(servidor, puerto);
			System.out.println("1 - " + cliente.getReplyString());
			// se establece la clave para la comunicaci�n segura
			cliente.setKeyManager(km);

			respuesta = cliente.getReplyCode();
			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				cliente.disconnect();
				System.out.println("CONEXI�N RECHAZADA");
				System.exit(1);
			}

			// se env�a el comando EHLO
			cliente.ehlo(servidor);// necesario

			System.out.println("2 - " + cliente.getReplyString());

			// Se ejecuta el comando STARTTLS y se comprueba si es true
			if (cliente.execTLS()) {
				System.out.println("3-" + cliente.getReplyString());
				// se realiza la autentificaci�n con el servidor
				if (cliente.auth(AUTH_METHOD.PLAIN, usuario, pwd)) {
					System.out.println("4 -" + cliente.getReplyCode());
					String destino = destinatario;
					String asunto = "Reserva de Bicicleta";
					String mensaje = "IdReserva: " + id + "\nNombre del Usuario: " + usuario2 + "\nBicicleta: " + nombre
							+ "\nPrecio: " + precio + " Euros\nFecha: " + new Date();

					// Se crea la cabecera
					SimpleSMTPHeader cabecera = new SimpleSMTPHeader(usuario, destino, asunto);

					// el nombre de usuario y el email de origen coinciden
					cliente.setSender(usuario);
					cliente.addRecipient(destino);
					System.out.println("5 -" + cliente.getReplyString());

					// Se envia DATA
					Writer escritor = cliente.sendMessageData();
					if (escritor == null) {
						System.out.println("FALLO AL ENVIAR DATA");
						System.exit(1);
					}
					escritor.write(cabecera.toString());
					escritor.write(mensaje);
					escritor.close();
					System.out.println("6 - " + cliente.getReplyCode());

					boolean exito = cliente.completePendingCommand();
					System.out.println("7 -" + cliente.getReplyCode());

					if (!exito) { // fallo
						System.out.println("FALLO AL FINALIZAR TRANSACCI�N");
						System.exit(1);
					} else {
						envio = true;
					}

				} else // else del m�todo auth
					System.out.println("USUARIO NO AUTENTIFICADO");
			} else // else del m�todo execTLS
				System.out.println("FALLO AL EJECUTAR STARTTLS");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cliente.disconnect();
		} catch (IOException e) {
			// TODO: handle exception
		}

		System.out.println("Fin del env�o");

		// System.exit(0);
		return envio;
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
