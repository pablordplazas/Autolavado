package implementaciones;

import conexion.Conexion;
import interfaces.IRegistroLavado;
import modelos.RegistroLavado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroLavadoImpl implements IRegistroLavado {

    @Override
    public boolean agregarRegistro(RegistroLavado registro) {
        String sql = "INSERT INTO registroslavado (vehiculoid, servicioid, fechalavado, horainicio, horafin, preciototal) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, registro.getVehiculoid());
            ps.setInt(2, registro.getServicioid());
            ps.setDate(3, registro.getFechalavado());
            ps.setTime(4, registro.getHorainicio());
            ps.setTime(5, registro.getHorafin());
            ps.setDouble(6, registro.getPreciototal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al agregar registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarRegistro(RegistroLavado registro) {
        String sql = "UPDATE registroslavado SET vehiculoid=?, servicioid=?, fechalavado=?, horainicio=?, horafin=?, preciototal=? WHERE registroid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, registro.getVehiculoid());
            ps.setInt(2, registro.getServicioid());
            ps.setDate(3, registro.getFechalavado());
            ps.setTime(4, registro.getHorainicio());
            ps.setTime(5, registro.getHorafin());
            ps.setDouble(6, registro.getPreciototal());
            ps.setInt(7, registro.getRegistroid());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarRegistro(int registroid) {
        String sql = "DELETE FROM registroslavado WHERE registroid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, registroid);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public RegistroLavado obtenerRegistro(int registroid) {
        String sql = "SELECT * FROM registroslavado WHERE registroid=?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, registroid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new RegistroLavado(
                        rs.getInt("registroid"),
                        rs.getInt("vehiculoid"),
                        rs.getInt("servicioid"),
                        rs.getDate("fechalavado"),
                        rs.getTime("horainicio"),
                        rs.getTime("horafin"),
                        rs.getDouble("preciototal")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener registro: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RegistroLavado> listarRegistros() {
        List<RegistroLavado> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroslavado";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new RegistroLavado(
                        rs.getInt("registroid"),
                        rs.getInt("vehiculoid"),
                        rs.getInt("servicioid"),
                        rs.getDate("fechalavado"),
                        rs.getTime("horainicio"),
                        rs.getTime("horafin"),
                        rs.getDouble("preciototal")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar registros: " + e.getMessage());
        }
        return lista;
    }
}
