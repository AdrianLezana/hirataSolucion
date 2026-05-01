package service;

import dao.EquiposDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Conexion;
import model.Equipo;
import model.EquipoCombo;

public class EquipoService {

    private EquiposDAO dao = new EquiposDAO();

    // REGISTRAR
    public boolean registrarEquipo(Equipo equipo) {
        Connection con = null;

        try {
            con = Conexion.getConnection();

            // Validación básica
            if (equipo.getNombre().isEmpty()) {
                System.out.println("El nombre no puede estar vacío");
                return false;
            }

            dao.insertar(equipo, con);
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar equipo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión");
            }
        }
    }

    // EDITAR
    public boolean actualizarEquipo(Equipo equipo) {
        Connection con = null;

        try {
            con = Conexion.getConnection();

            if (equipo.getIdEquipo() <= 0) {
                System.out.println("ID inválido");
                return false;
            }

            return dao.actualizar(equipo, con);

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión");
            }
        }
    }

    // ELIMINAR
    public boolean eliminarEquipo(int idEquipo) {
        Connection con = null;

        try {
            con = Conexion.getConnection();

            if (idEquipo <= 0) {
                System.out.println("ID inválido");
                return false;
            }

            return dao.eliminar(idEquipo, con);

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión");
            }
        }
    }

    public List<Equipo> listarEquipos() {
        List<Equipo> lista = new ArrayList<>();

        try (Connection con = Conexion.getConnection()) {
            lista = dao.listar(con);
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    public List<EquipoCombo> obtenerEquiposCombo() {
        try (Connection con = Conexion.getConnection()) {
            return dao.listarParaCombo(con);
        } catch (SQLException e) {
            System.out.println("Error listar equipos: " + e.getMessage());
            return null;
        }
    }

}
