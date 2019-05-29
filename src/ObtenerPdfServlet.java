
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.darwinsys.spdf.MoveTo;
import com.darwinsys.spdf.PDF;
import com.darwinsys.spdf.Page;
import com.darwinsys.spdf.Text;

/**
 * Servlet implementation class ObtenerPdfServlet
 */
@WebServlet("/ObtenerPdfServlet")
public class ObtenerPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private String nombreFichero = "";
	String usuario2 = "";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		nombreFichero = getInitParameter("nombreFichero");

		usuario2 = getServletContext().getInitParameter("usuario2");

	}

	String id = null;
	String nombre = null;
	String precio = null;

	public ObtenerPdfServlet() {
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
		usuario2 = (String) sesion.getAttribute("usuario");
		id = (String) sesion.getAttribute("id");
		nombre = (String) sesion.getAttribute("nombre");
		precio = (String) sesion.getAttribute("precio");

		PrintWriter out = response.getWriter();
		response.setContentType("application/pdf");

		response.setHeader("Content-disposition", "inline; filename=" + usuario2 + id + ".pdf");

		PDF p = new PDF(out);
		Page p1 = new Page(p);
		p1.add(new MoveTo(p, 200, 700));

		p1.add(new Text(p, "IdReserva: " + id));
		p1.add(new Text(p, "Nombre del Usuario: " + usuario2));
		p1.add(new Text(p, "Bicicleta: " + nombre));
		p1.add(new Text(p, "precio: " + precio + " euros"));
		p1.add(new Text(p, "Fecha: " + new Date()));

		p.add(p1);

		p.writePDF();

		subirFichero();
	}

	private void subirFichero() throws SocketException, IOException {
		// TODO Auto-generated method stub

		File directorio = new File("tickets");
		directorio.mkdirs();
		nombreFichero = "tickets/ticket" + usuario2 + ".txt";
		FileWriter archivo = new FileWriter(nombreFichero);

		BufferedWriter bw = new BufferedWriter(archivo);

		PrintWriter salArch = new PrintWriter(bw);
		salArch.print("IdReserva: " + id);
		salArch.println();
		salArch.print("Nombre del Usuario: " + usuario2);
		salArch.println();
		salArch.print("Bicicleta: " + nombre);
		salArch.println();
		salArch.print("precio: " + precio + " euros");
		salArch.println();
		salArch.println();
		salArch.print("Fecha: " + new Date());
		salArch.println();
		salArch.close();

		bw.close();
		FTPClient cliente = new FTPClient();

		String servFTP = "files.000webhost.com";

		System.out.println("Nos conectamos a: " + servFTP);
		String usuario = "rssperiodicos";
		String clave = "*********";

		cliente.connect(servFTP);
		cliente.enterLocalPassiveMode();
		boolean login = cliente.login(usuario, clave);

		if (login) {
			System.out.println("Login correcto...");
		} else {

			System.out.println("Login incorrecto");
			cliente.disconnect();
			System.exit(1);
		}

		System.out.println("Directorio actual:" + cliente.printWorkingDirectory());

		FTPFile[] files = cliente.listFiles();
		System.out.println("Ficheros en el directorio actual:" + files.length);

		String tipos[] = { "Fichero", "Directorio", "Enlace simb." };

		for (int i = 0; i < files.length; i++) {
			System.out.println("\t" + files[i].getName() + "=>" + tipos[files[i].getType()]);
		}

		String direc = "TicketReservas";
		if (cliente.makeDirectory(direc)) {
			System.out.println("Directorio creado");
			cliente.changeWorkingDirectory(direc);
		}
		cliente.changeWorkingDirectory(direc);

		cliente.setFileType(FTP.BINARY_FILE_TYPE);

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(nombreFichero));

		cliente.storeFile("ticket" + usuario2 + ".txt", in);
		////////////////////////////////////
		System.out.println("Fichero Subido con exito");
		////////////////////////////////////
		in.close();

		boolean logout = cliente.logout();
		if (logout) {
			System.out.println("Logout del servidor FTP...");
		} else {
			System.out.println("Error al hacer Logout...");
		}

		cliente.disconnect();

		System.out.println("Conexi�n finalizada");
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
