package service;

import dao.InventarioPiezaDAO;
import dao.MovimientoPiezaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Conexion;
import model.InventarioPieza;
import model.MovimientoPieza;

public class InventarioService {

    private InventarioPiezaDAO piezaDAO = new InventarioPiezaDAO();
    private MovimientoPiezaDAO movDAO = new MovimientoPiezaDAO();

    // LISTAR INVENTARIO
    public List<InventarioPieza> listar() {
        try (Connection con = Conexion.getConnection()) {
            return piezaDAO.listar(con);
        } catch (SQLException e) {
            System.out.println("Error listar inventario: " + e.getMessage());
            return null;
        }
    }

    // REGISTRAR PIEZA (entrada inicial)
    public boolean registrarPieza(InventarioPieza p) {
        try (Connection con = Conexion.getConnection()) {

            int idPieza = piezaDAO.insertar(p, con);

            MovimientoPieza m = new MovimientoPieza();
            m.setIdPieza(idPieza);
            m.setTipoMovimiento("ENTRADA");
            m.setCantidad(p.getCantidad());
            m.setDescripcion("Ingreso inicial");

            return movDAO.insertar(m, con);

        } catch (SQLException e) {
            System.out.println("Error registrar pieza: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR PIEZA
    public boolean eliminar(int idPieza) {
        try (Connection con = Conexion.getConnection()) {
            return piezaDAO.eliminar(idPieza, con);
        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }

    // SALIDA (restar stock)
    public boolean registrarSalida(int idPieza, int cantidad) {

        try (Connection con = Conexion.getConnection()) {

            int stock = piezaDAO.obtenerCantidad(idPieza, con);

            if (stock < cantidad) {
                return false;
            }

            int nuevoStock = stock - cantidad;
            piezaDAO.actualizarCantidad(idPieza, nuevoStock, con);

            MovimientoPieza m = new MovimientoPieza();
            m.setIdPieza(idPieza);
            m.setTipoMovimiento("SALIDA");
            m.setCantidad(cantidad);
            m.setDescripcion("Salida de piezas");

            return movDAO.insertar(m, con);

        } catch (SQLException e) {
            System.out.println("Error salida: " + e.getMessage());
            return false;
        }
    }

    // ENTRADA (sumar stock)
    public boolean registrarEntrada(int idPieza, int cantidad) {

        try (Connection con = Conexion.getConnection()) {

            int stock = piezaDAO.obtenerCantidad(idPieza, con);

            int nuevoStock = stock + cantidad;
            piezaDAO.actualizarCantidad(idPieza, nuevoStock, con);

            MovimientoPieza m = new MovimientoPieza();
            m.setIdPieza(idPieza);
            m.setTipoMovimiento("ENTRADA");
            m.setCantidad(cantidad);
            m.setDescripcion("Ingreso adicional");

            return movDAO.insertar(m, con);

        } catch (SQLException e) {
            System.out.println("Error entrada: " + e.getMessage());
            return false;
        }
    }

    // LISTAR MOVIMIENTOS
    public List<Object[]> listarMovimientos(int idPieza) {
        try (Connection con = Conexion.getConnection()) {
            return movDAO.listarPorPieza(idPieza, con);
        } catch (SQLException e) {
            System.out.println("Error listar movimientos: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizar(InventarioPieza p) {
        try (Connection con = Conexion.getConnection()) {
            return piezaDAO.actualizar(p, con);
        } catch (SQLException e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    public List<InventarioPieza> buscar(String texto) {
        try (Connection con = Conexion.getConnection()) {
            return piezaDAO.buscar(texto, con);
        } catch (SQLException e) {
            System.out.println("Error buscar: " + e.getMessage());
            return null;
        }
    }

}
