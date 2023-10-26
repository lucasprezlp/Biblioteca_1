package biblioteca.Entidades;



public class Ejemplar {

    public int idEjemplar;
    public int codigo;
    public Libro libro;
    public EstadosEjemplar estado;

    public Ejemplar() {
    }

    public Ejemplar(int codigo, Libro libro, EstadosEjemplar estado) {
        this.codigo = codigo;
        this.libro = libro;
        this.estado = estado;
    }

    public Ejemplar(int idEjemplar, int codigo, Libro libro, EstadosEjemplar estado) {
        this.idEjemplar = idEjemplar;
        this.codigo = codigo;
        this.libro = libro;
        this.estado = estado;
    }
    
    
     public Ejemplar(int idEjemplar, EstadosEjemplar estado) { // para hacer el metodo modificarejemplar
        this.idEjemplar = idEjemplar;
        this.estado = estado;
    }

    public Ejemplar(Libro libro) {
        this.libro = libro;
    }

    public Ejemplar(int codigo, Libro libro) {
        this.codigo = codigo;
        this.libro = libro;
    }
    
    
    
    

    public Ejemplar(int codigo) {
        this.codigo = codigo;
    }
     
     
       

    public int getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public EstadosEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadosEjemplar estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "idEjemplar=" + idEjemplar + ", codigo=" + codigo + ", libro=" + libro + ", estado=" + estado + '}';
    }

}
