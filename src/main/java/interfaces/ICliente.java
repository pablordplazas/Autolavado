package interfaces;

import modelos.Cliente;
import java.util.List;

public interface ICliente {
    boolean agregarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int clienteid);
    Cliente obtenerCliente(int clienteid);
    List<Cliente> listarClientes();
}
