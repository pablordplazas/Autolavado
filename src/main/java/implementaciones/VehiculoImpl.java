package implementaciones;

import conexion.Conexion;
import interfaces.IVehiculo;
import modelos.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoImpl implements IVehiculo {

    @Override
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (clienteid, marca, modelo, placa, color, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehiculo.getClienteid());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getPlaca());
            ps.setString(5, vehiculo.getColor());
            ps.setString(6, vehiculo.getTipo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al agregar vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET clienteid=?, marca=?, modelo=?, placa=?, color=?, tipo=? WHERE vehiculoid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehiculo.getClienteid());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getPlaca());
            ps.setString(5, vehiculo.getColor());
            ps.setString(6, vehiculo.getTipo());
            ps.setInt(7, vehiculo.getVehiculoid());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarVehiculo(int vehiculoid) {
        String sql = "DELETE FROM vehiculos WHERE vehiculoid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehiculoid);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Vehiculo obtenerVehiculo(int vehiculoid) {
        String sql = "SELECT * FROM vehiculos WHERE vehiculoid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehiculoid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Vehiculo(
                        rs.getInt("vehiculoid"),
                        rs.getInt("clienteid"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("color"),
                        rs.getString("tipo")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener vehículo: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Vehiculo(
                        rs.getInt("vehiculoid"),
                        rs.getInt("clienteid"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("color"),
                        rs.getString("tipo")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }
}
