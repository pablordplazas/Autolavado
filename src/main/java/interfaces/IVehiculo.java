package interfaces;

import modelos.Vehiculo;
import java.util.List;

public interface IVehiculo {
    boolean agregarVehiculo(Vehiculo vehiculo);
    boolean actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculo(int vehiculoid);
    Vehiculo obtenerVehiculo(int vehiculoid);
    List<Vehiculo> listarVehiculos();
}
