package implementaciones;

import conexion.Conexion;
import interfaces.IServicio;
import modelos.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioImpl implements IServicio {

    @Override
    public boolean agregarServicio(Servicio servicio) {
        String sql = "INSERT INTO servicios (nombre, precio) VALUES (?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setDouble(2, servicio.getPrecio());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al agregar servicio: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarServicio(Servicio servicio) {
        String sql = "UPDATE servicios SET nombre=?, precio=? WHERE servicioid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setDouble(2, servicio.getPrecio());
            ps.setInt(3, servicio.getServicioid());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar servicio: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarServicio(int servicioid) {
        String sql = "DELETE FROM servicios WHERE servicioid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, servicioid);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar servicio: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Servicio obtenerServicio(int servicioid) {
        String sql = "SELECT * FROM servicios WHERE servicioid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, servicioid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Servicio(
                        rs.getInt("servicioid"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener servicio: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Servicio> listarServicios() {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Servicio(
                        rs.getInt("servicioid"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar servicios: " + e.getMessage());
        }
        return lista;
    }
}
