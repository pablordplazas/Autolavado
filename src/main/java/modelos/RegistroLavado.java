package modelos;

import java.sql.Date;
import java.sql.Time;

public class RegistroLavado {
    private int registroid;
    private int vehiculoid;
    private int servicioid;
    private Date fechalavado;
    private Time horainicio;
    private Time horafin;
    private double preciototal;

    public RegistroLavado() {}

    public RegistroLavado(int registroid, int vehiculoid, int servicioid, Date fechalavado, Time horainicio, Time horafin, double preciototal) {
        this.registroid = registroid;
        this.vehiculoid = vehiculoid;
        this.servicioid = servicioid;
        this.fechalavado = fechalavado;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.preciototal = preciototal;
    }

    public int getRegistroid() { return registroid; }
    public void setRegistroid(int registroid) { this.registroid = registroid; }

    public int getVehiculoid() { return vehiculoid; }
    public void setVehiculoid(int vehiculoid) { this.vehiculoid = vehiculoid; }

    public int getServicioid() { return servicioid; }
    public void setServicioid(int servicioid) { this.servicioid = servicioid; }

    public Date getFechalavado() { return fechalavado; }
    public void setFechalavado(Date fechalavado) { this.fechalavado = fechalavado; }

    public Time getHorainicio() { return horainicio; }
    public void setHorainicio(Time horainicio) { this.horainicio = horainicio; }

    public Time getHorafin() { return horafin; }
    public void setHorafin(Time horafin) { this.horafin = horafin; }

    public double getPreciototal() { return preciototal; }
    public void setPreciototal(double preciototal) { this.preciototal = preciototal; }
}
