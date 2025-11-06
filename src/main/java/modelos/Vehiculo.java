package modelos;

public class Vehiculo {
    private int vehiculoid;
    private int clienteid;
    private String marca;
    private String modelo;
    private String placa;
    private String color;
    private String tipo;

    public Vehiculo() {}

    public Vehiculo(int vehiculoid, int clienteid, String marca, String modelo, String placa, String color, String tipo) {
        this.vehiculoid = vehiculoid;
        this.clienteid = clienteid;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.color = color;
        this.tipo = tipo;
    }

    public int getVehiculoid() { return vehiculoid; }
    public void setVehiculoid(int vehiculoid) { this.vehiculoid = vehiculoid; }

    public int getClienteid() { return clienteid; }
    public void setClienteid(int clienteid) { this.clienteid = clienteid; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
