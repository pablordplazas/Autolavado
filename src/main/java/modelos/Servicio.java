package modelos;

public class Servicio {
    private int servicioid;
    private String nombre;
    private double precio;

    public Servicio() {}

    public Servicio(int servicioid, String nombre, double precio) {
        this.servicioid = servicioid;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getServicioid() { return servicioid; }
    public void setServicioid(int servicioid) { this.servicioid = servicioid; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
