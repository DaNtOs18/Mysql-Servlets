package utilidades;

import java.sql.Connection;
import modelo.Libro;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Conexion {
	private String host;
	private String bd;
	private String usr;
	private String clave;
	private static Connection conexion;

	public static Connection conectar(String host, String bd, String usr, String clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host + "/" + bd + "?useSSL=false";
			conexion = DriverManager.getConnection(url, usr, clave);
		} catch (SQLException e) {
			System.out.println("Error sql -> " + e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Error carga del driver -> " + e.getMessage());
		}

		return conexion;
	}

	public static ArrayList<Libro> getAllLibros() {
		ArrayList<Libro> resultado = new ArrayList<Libro>();
		// Statement, PreparedStatement, ResultSet, MetaData
		String sql = "SELECT * FROM books ";
		try {
			conexion = conectar("localhost:3306", "eshop", "root", "");
			Statement stmt = conexion.createStatement();
			ResultSet rS = stmt.executeQuery(sql);

			if (!rS.first())// no hay registros
				return null;

			while (rS.next()) {
				Libro libro = new Libro();
				libro.setId(rS.getInt(1));
				libro.setAutor(rS.getString("author"));
				libro.setTitulo(rS.getString(2));
				libro.setPrecio(rS.getDouble("price"));
				libro.setCat_id(rS.getInt(5));
				resultado.add(libro);

			}
			return resultado;

		} catch (SQLException e) {
			System.out.println("Error leyendo libros -> " + e.getMessage());
		}
		return resultado;
	}

	public static ArrayList<Object> getAllDB() {

		ArrayList<Object> resultado = new ArrayList<Object>();
		String sql = "SELECT schema_name FROM information_schema.schemata WHERE schema_name NOT IN ('mysql','information_schema','performance_schema','phpmyadmin');";

		try {
			conexion = conectar("localhost:3306", "information_schema", "root", "");
			Statement stmt = conexion.createStatement();
			ResultSet rS = stmt.executeQuery(sql);
			// ResultSetMetaData metaData = rS.getMetaData();

			//if (!rS.first())// no hay registros
				//return null;

			while (rS.next()) {
				Object obj = new Object();
				obj = rS.getString("SCHEMA_NAME");
				resultado.add(obj);

			}
			return resultado;
		} catch (SQLException e) {
			System.out.println("Error leyendo Bases de Datos -> " + e.getMessage());
		}
		return resultado;

		// "SELECT schema_name FROM information_schema.schemata WHERE schema_name NOT IN
		// ('mysql','information_schema','performance_schema');"

		// "SELECT * FROM Schemata;"
		// "SELECT * FROM Schemata WHERE SCHEMA_NAME NOT IN
		// ('information_schema','mysql','performance_schema');"
		// "SELECT table_name FROM tables WHERE table_schema='eshop';"
		// "SHOW tables FROM eshop;"
		// "SELECT * FROM eshop.books;"

	}

	public static ArrayList<Object> getAllTables(String dbName) {

		ArrayList<Object> resultado = new ArrayList<Object>();
		String sql = "SHOW tables FROM " + dbName + ";";

		try {
			conexion = conectar("localhost:3306", dbName, "root", "");
			Statement stmt = conexion.createStatement();
			ResultSet rS = stmt.executeQuery(sql);
			// ResultSetMetaData metaData = rS.getMetaData();

			//if (!rS.first())// no hay registros
				//return null;

			while (rS.next()) {
				Object table = new Object();
				table = rS.getString("Tables_in_" + dbName);
				resultado.add(table);

			}
			return resultado;
		} catch (SQLException e) {
			System.out.println("Error leyendo la Base de Datos -> " + e.getMessage());
		}
		return resultado;

	}

	public static ArrayList<HashMap<String, Object>> getAllParam(String dbName, String table) {

		ArrayList<HashMap<String, Object>> resultado = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT * FROM " + table + ";";

		try {
			conexion = conectar("localhost:3306", dbName, "root", "");
			Statement stmt = conexion.createStatement();
			ResultSet rS = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rS.getMetaData();

			//if (!rS.first())// no hay registros
				//return null;

			while (rS.next()) {

				HashMap<String, Object> registro = new HashMap<String, Object>();
				resultado.add(registro);

				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					registro.put(metaData.getColumnName(i), rS.getString(i));
					// System.out.println(metaData.getColumnName(i) + " => " + rS.getString(i));
				}

			}
			return resultado;
		} catch (SQLException e) {
			System.out.println("Error leyendo las tablas -> " + e.getMessage());
		}
		return resultado;

	}

}
