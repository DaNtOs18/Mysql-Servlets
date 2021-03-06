package control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Libro;
import utilidades.Conexion;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/conecta")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String mensaje = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");
		String rutaJSP = "";
		//HttpSession se = request.getSession();
		//se.setAttribute("dbName", dbName);
		
		switch (opcion) {
		case "1":
			rutaJSP = "mensaje";
			opcion1(request);
			break;
		case "2":
			rutaJSP = "libros";			
			opcion2(request);
			break;
		case "3":
			rutaJSP = "bbdd";			
			opcion3(request);
			break;
		case "4":
			rutaJSP = "database";			
			opcion4(request);
			break;
		case "5":
			rutaJSP = "table";			
			opcion5(request);
			break;
		default:
			break;
		}
		request.getRequestDispatcher("jsp/" + rutaJSP + ".jsp").forward(request, response);
	}
	
	public void opcion5(HttpServletRequest request) {
		String table = request.getParameter("table");
		String dbName = (String) request.getServletContext().getAttribute("dbName");
		ArrayList<HashMap<String, Object>> tables = Conexion.getAllParam(dbName,table);
		request.setAttribute("tables", tables);
		
	}
	public void opcion4(HttpServletRequest request) {
		String dbName = request.getParameter("dbName");
		request.getServletContext().setAttribute("dbName", dbName);
		ArrayList<Object> database = Conexion.getAllTables(dbName);
		request.setAttribute("database", database);
		
	}
	public void opcion3(HttpServletRequest request) {
		ArrayList<Object> db = Conexion.getAllDB();
		request.setAttribute("db", db);
		
	}
	public void opcion2(HttpServletRequest request) {
		ArrayList<Libro> libros = Conexion.getAllLibros();
		request.setAttribute("libros", libros);
	}
	public void opcion1(HttpServletRequest request) {

		Connection cnx = Conexion.conectar("localhost:3306", "eshop", "root", "");
		if (cnx == null)
			mensaje = "Error de conexi�n...";
		else
			mensaje = "Conectado con exito...";
		request.setAttribute("mensaje", mensaje);
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
