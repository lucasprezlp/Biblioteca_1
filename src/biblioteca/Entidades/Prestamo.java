
package biblioteca.Entidades;

import java.util.Date;
import java.time.LocalDate;

    public class Prestamo {
    public int idPrestamo;
    public LocalDate FechaInicio;
    public LocalDate FechaFin;
    public Ejemplar Ejemplar;
    public Lector lector;
    public boolean estado;

    public Prestamo() {
    }
    
    public Prestamo(Ejemplar Ejemplar, Lector lector) {
        this.Ejemplar = Ejemplar;
        this.lector = lector;
    }
    
        public Prestamo(LocalDate FechaInicio, Ejemplar Ejemplar) {
        this.FechaInicio = FechaInicio;
        this.Ejemplar = Ejemplar;
    }
    
    public Prestamo(LocalDate FechaInicio, LocalDate FechaFin, Ejemplar Ejemplar, Lector lector, boolean estado) {
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.Ejemplar = Ejemplar;
        this.lector = lector;
        this.estado = estado;
    }
    public Prestamo(int idPrestamo, LocalDate FechaInicio, LocalDate FechaFin, Ejemplar Ejemplar, Lector lector, boolean estado) {
        this.idPrestamo = idPrestamo;
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.Ejemplar = Ejemplar;
        this.lector = lector;
        this.estado = estado;
    }

    




    
    

    public int getIdPrestamo() {
        return idPrestamo;
    }
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    public LocalDate getFechaInicio() {
        return FechaInicio;
    }
    public void setFechaInicio(LocalDate FechaInicio) {
        this.FechaInicio = FechaInicio;
    }
    public LocalDate getFechaFin() {
        return FechaFin;
    }
    public void setFechaFin(LocalDate FechaFin) {
        this.FechaFin = FechaFin;
    }
    public Ejemplar getEjemplar() {
        return Ejemplar;
    }
    public void setEjemplar(Ejemplar Ejemplar) {
        this.Ejemplar = Ejemplar;
    }
    public Lector getLector() {
        return lector;
    }
    public void setLector(Lector lector) {
        this.lector = lector;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", FechaInicio=" + FechaInicio + ", FechaFin=" + FechaFin + ", Ejemplar=" + Ejemplar + ", lector=" + lector + ", estado=" + estado + '}';
    }
    
    
    
}
