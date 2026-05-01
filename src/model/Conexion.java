package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String BASE_DATOS = "hiratatransporte";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BASE_DATOS;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        }
    }

    /*private Connection conexion = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa con " + baseDatos + ".");
        } catch (ClassNotFoundException e) {
            System.err.println("Error. No se encontró el driver" + e);
        } catch (SQLException e) {
            System.err.println("Error al conectar." + e);
        }
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar...");
        }
    }*/
}
