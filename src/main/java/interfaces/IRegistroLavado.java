package interfaces;

import modelos.RegistroLavado;
import java.util.List;

public interface IRegistroLavado {
    boolean agregarRegistro(RegistroLavado registro);
    boolean actualizarRegistro(RegistroLavado registro);
    boolean eliminarRegistro(int registroid);
    RegistroLavado obtenerRegistro(int registroid);
    List<RegistroLavado> listarRegistros();
}
