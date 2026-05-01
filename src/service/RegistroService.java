
package service;

import dao.CamionDAO;
import dao.ConductorDAO;
import dao.UsuarioDAO;
import model.Camion;
import java.sql.Connection;
import model.Conductor;
import model.Conexion;
import model.Usuario;
import java.sql.SQLException;



public class RegistroService {
    
    public void registrarTodo(Usuario u, Conductor c, Camion cam) {

        Connection conexion = null;

        try {
            Conexion con = new Conexion();
            conexion = con.getConnection();

            conexion.setAutoCommit(false);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ConductorDAO conductorDAO = new ConductorDAO();
            CamionDAO camionDAO = new CamionDAO();

            int idUsuario = usuarioDAO.insertar(u, conexion);
            c.setIdUsuario(idUsuario);

            int idConductor = conductorDAO.insertar(c, conexion);
            cam.setIdConductor(idConductor);

            camionDAO.insertar(cam, conexion);

            conexion.commit();

        } catch (Exception e) {
            try {
                if (conexion != null) conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
