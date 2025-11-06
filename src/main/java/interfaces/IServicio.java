package interfaces;

import modelos.Servicio;
import java.util.List;

public interface IServicio {
    boolean agregarServicio(Servicio servicio);
    boolean actualizarServicio(Servicio servicio);
    boolean eliminarServicio(int servicioid);
    Servicio obtenerServicio(int servicioid);
    List<Servicio> listarServicios();
}
