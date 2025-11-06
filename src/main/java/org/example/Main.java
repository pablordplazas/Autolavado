package org.example;

import implementaciones.ClienteImpl;
import implementaciones.VehiculoImpl;
import implementaciones.ServicioImpl;
import implementaciones.RegistroLavadoImpl;

import modelos.Cliente;
import modelos.Vehiculo;
import modelos.Servicio;
import modelos.RegistroLavado;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Clientes");
            System.out.println("2. Vehículos");
            System.out.println("3. Servicios");
            System.out.println("4. Registros de Lavado");
            System.out.println("0. Salir");
            System.out.print("Selecciona una tabla: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1: menuClientes(); break;
                case 2: menuVehiculos(); break;
                case 3: menuServicios(); break;
                case 4: menuRegistros(); break;
                case 0: salir = true; System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida, intenta de nuevo."); break;
            }
        }
    }

    // ==================== MENU CLIENTES ====================
    private static void menuClientes() {
        ClienteImpl clienteDAO = new ClienteImpl();
        boolean back = false;

        while (!back) {
            System.out.println("\n--- MENÚ CLIENTES ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona opción: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre: "); String nombre = sc.nextLine();
                    System.out.print("Apellido: "); String apellido = sc.nextLine();
                    System.out.print("Teléfono: "); String telefono = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Dirección: "); String direccion = sc.nextLine();

                    Cliente nuevo = new Cliente(0, nombre, apellido, telefono, email, direccion);
                    if (clienteDAO.agregarCliente(nuevo)) System.out.println("✅ Cliente agregado correctamente");
                    break;

                case 2:
                    List<Cliente> clientes = clienteDAO.listarClientes();
                    System.out.println("\n📋 Lista de Clientes:");
                    for (Cliente c : clientes) {
                        System.out.println(c.getClienteid() + " - " + c.getNombre() + " " + c.getApellido());
                    }
                    break;

                case 3:
                    System.out.print("ID del cliente a actualizar: ");
                    int idUpd = sc.nextInt(); sc.nextLine();
                    Cliente cli = clienteDAO.obtenerCliente(idUpd);
                    if (cli != null) {
                        System.out.print("Nombre (" + cli.getNombre() + "): "); String n = sc.nextLine(); if(!n.isEmpty()) cli.setNombre(n);
                        System.out.print("Apellido (" + cli.getApellido() + "): "); String a = sc.nextLine(); if(!a.isEmpty()) cli.setApellido(a);
                        System.out.print("Teléfono (" + cli.getTelefono() + "): "); String t = sc.nextLine(); if(!t.isEmpty()) cli.setTelefono(t);
                        System.out.print("Email (" + cli.getEmail() + "): "); String e = sc.nextLine(); if(!e.isEmpty()) cli.setEmail(e);
                        System.out.print("Dirección (" + cli.getDireccion() + "): "); String d = sc.nextLine(); if(!d.isEmpty()) cli.setDireccion(d);

                        if (clienteDAO.actualizarCliente(cli)) System.out.println("✅ Cliente actualizado");
                    } else {
                        System.out.println("❌ Cliente no encontrado");
                    }
                    break;

                case 4:
                    System.out.print("ID del cliente a eliminar: ");
                    int idDel = sc.nextInt(); sc.nextLine();
                    if (clienteDAO.eliminarCliente(idDel)) System.out.println("✅ Cliente eliminado");
                    else System.out.println("❌ No se pudo eliminar");
                    break;

                case 0: back = true; break;
                default: System.out.println("Opción inválida"); break;
            }
        }
    }

    // ==================== MENU VEHÍCULOS ====================
    private static void menuVehiculos() {
        VehiculoImpl vehiculoDAO = new VehiculoImpl();
        boolean back = false;

        while (!back) {
            System.out.println("\n--- MENÚ VEHÍCULOS ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Actualizar vehículo");
            System.out.println("4. Eliminar vehículo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona opción: ");

            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("ClienteID: "); int cid = sc.nextInt(); sc.nextLine();
                    System.out.print("Marca: "); String marca = sc.nextLine();
                    System.out.print("Modelo: "); String modelo = sc.nextLine();
                    System.out.print("Placa: "); String placa = sc.nextLine();
                    System.out.print("Color: "); String color = sc.nextLine();
                    System.out.print("Tipo: "); String tipo = sc.nextLine();

                    Vehiculo v = new Vehiculo(0, cid, marca, modelo, placa, color, tipo);
                    if (vehiculoDAO.agregarVehiculo(v)) System.out.println("✅ Vehículo agregado");
                    break;

                case 2:
                    List<Vehiculo> vehiculos = vehiculoDAO.listarVehiculos();
                    System.out.println("\n📋 Lista de Vehículos:");
                    for (Vehiculo veh : vehiculos) {
                        System.out.println(veh.getVehiculoid() + " - " + veh.getMarca() + " " + veh.getModelo() + " - PropietarioID: " + veh.getClienteid());
                    }
                    break;

                case 3:
                    System.out.print("ID del vehículo a actualizar: "); int vid = sc.nextInt(); sc.nextLine();
                    Vehiculo veh = vehiculoDAO.obtenerVehiculo(vid);
                    if (veh != null) {
                        System.out.print("Marca (" + veh.getMarca() + "): "); String m = sc.nextLine(); if(!m.isEmpty()) veh.setMarca(m);
                        System.out.print("Modelo (" + veh.getModelo() + "): "); String mo = sc.nextLine(); if(!mo.isEmpty()) veh.setModelo(mo);
                        System.out.print("Placa (" + veh.getPlaca() + "): "); String p = sc.nextLine(); if(!p.isEmpty()) veh.setPlaca(p);
                        System.out.print("Color (" + veh.getColor() + "): "); String c = sc.nextLine(); if(!c.isEmpty()) veh.setColor(c);
                        System.out.print("Tipo (" + veh.getTipo() + "): "); String t = sc.nextLine(); if(!t.isEmpty()) veh.setTipo(t);
                        if (vehiculoDAO.actualizarVehiculo(veh)) System.out.println("✅ Vehículo actualizado");
                    } else System.out.println("❌ Vehículo no encontrado");
                    break;

                case 4:
                    System.out.print("ID del vehículo a eliminar: "); int vidDel = sc.nextInt(); sc.nextLine();
                    if (vehiculoDAO.eliminarVehiculo(vidDel)) System.out.println("✅ Vehículo eliminado");
                    else System.out.println("❌ No se pudo eliminar");
                    break;

                case 0: back = true; break;
                default: System.out.println("Opción inválida"); break;
            }
        }
    }

    // ==================== MENU SERVICIOS ====================
    private static void menuServicios() {
        ServicioImpl servicioDAO = new ServicioImpl();
        boolean back = false;

        while (!back) {
            System.out.println("\n--- MENÚ SERVICIOS ---");
            System.out.println("1. Agregar servicio");
            System.out.println("2. Listar servicios");
            System.out.println("3. Actualizar servicio");
            System.out.println("4. Eliminar servicio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona opción: ");

            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Nombre: "); String nombre = sc.nextLine();
                    System.out.print("Precio: "); double precio = sc.nextDouble(); sc.nextLine();
                    Servicio s = new Servicio(0, nombre, precio);
                    if (servicioDAO.agregarServicio(s)) System.out.println("✅ Servicio agregado");
                    break;

                case 2:
                    List<Servicio> servicios = servicioDAO.listarServicios();
                    System.out.println("\n📋 Lista de Servicios:");
                    for (Servicio se : servicios) {
                        System.out.println(se.getServicioid() + " - " + se.getNombre() + " - $" + se.getPrecio());
                    }
                    break;

                case 3:
                    System.out.print("ID del servicio a actualizar: "); int sid = sc.nextInt(); sc.nextLine();
                    Servicio serv = servicioDAO.obtenerServicio(sid);
                    if (serv != null) {
                        System.out.print("Nombre (" + serv.getNombre() + "): "); String n = sc.nextLine(); if(!n.isEmpty()) serv.setNombre(n);
                        System.out.print("Precio (" + serv.getPrecio() + "): "); String pStr = sc.nextLine(); if(!pStr.isEmpty()) serv.setPrecio(Double.parseDouble(pStr));
                        if (servicioDAO.actualizarServicio(serv)) System.out.println("✅ Servicio actualizado");
                    } else System.out.println("❌ Servicio no encontrado");
                    break;

                case 4:
                    System.out.print("ID del servicio a eliminar: "); int sidDel = sc.nextInt(); sc.nextLine();
                    if (servicioDAO.eliminarServicio(sidDel)) System.out.println("✅ Servicio eliminado");
                    else System.out.println("❌ No se pudo eliminar");
                    break;

                case 0: back = true; break;
                default: System.out.println("Opción inválida"); break;
            }
        }
    }

    // ==================== MENU REGISTROS ====================
    private static void menuRegistros() {
        RegistroLavadoImpl registroDAO = new RegistroLavadoImpl();
        boolean back = false;

        while (!back) {
            System.out.println("\n--- MENÚ REGISTROS DE LAVADO ---");
            System.out.println("1. Agregar registro");
            System.out.println("2. Listar registros");
            System.out.println("3. Actualizar registro");
            System.out.println("4. Eliminar registro");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona opción: ");

            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("VehículoID: "); int vid = sc.nextInt(); sc.nextLine();
                    System.out.print("ServicioID: "); int sid = sc.nextInt(); sc.nextLine();
                    Date fecha = new Date(System.currentTimeMillis());
                    Time horaInicio = new Time(System.currentTimeMillis());
                    Time horaFin = new Time(System.currentTimeMillis() + 3600000);
                    System.out.print("Precio total: "); double precio = sc.nextDouble(); sc.nextLine();

                    RegistroLavado r = new RegistroLavado(0, vid, sid, fecha, horaInicio, horaFin, precio);
                    if (registroDAO.agregarRegistro(r)) System.out.println("✅ Registro agregado");
                    break;

                case 2:
                    List<RegistroLavado> registros = registroDAO.listarRegistros();
                    System.out.println("\n📋 Lista de Registros:");
                    for (RegistroLavado re : registros) {
                        System.out.println(re.getRegistroid() + " - VehículoID: " + re.getVehiculoid() +
                                " - ServicioID: " + re.getServicioid() +
                                " - Precio: $" + re.getPreciototal() +
                                " - Fecha: " + re.getFechalavado());
                    }
                    break;

                case 3:
                    System.out.print("ID del registro a actualizar: "); int rid = sc.nextInt(); sc.nextLine();
                    RegistroLavado reg = registroDAO.obtenerRegistro(rid);
                    if (reg != null) {
                        System.out.print("Precio (" + reg.getPreciototal() + "): "); String prStr = sc.nextLine();
                        if(!prStr.isEmpty()) reg.setPreciototal(Double.parseDouble(prStr));
                        if (registroDAO.actualizarRegistro(reg)) System.out.println("✅ Registro actualizado");
                    } else System.out.println("❌ Registro no encontrado");
                    break;

                case 4:
                    System.out.print("ID del registro a eliminar: "); int ridDel = sc.nextInt(); sc.nextLine();
                    if (registroDAO.eliminarRegistro(ridDel)) System.out.println("✅ Registro eliminado");
                    else System.out.println("❌ No se pudo eliminar");
                    break;

                case 0: back = true; break;
                default: System.out.println("Opción inválida"); break;
            }
        }
    }
}
