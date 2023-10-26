
package biblioteca.Entidades;

public class Libro {
    public int idLibro;
   public int isbn;
   public String titulo;
   public String autor;
   public int anio;
   public String tipo;
   public String Editor;
   public EstadosEjemplar estado;
   public int numEjemplares;

    public Libro() {
    }

    public Libro(int idLibro, int isbn, String titulo, String autor, int anio, String tipo, String Editor, EstadosEjemplar estado, int numEjemplares) {
        this.idLibro = idLibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.tipo = tipo;
        this.Editor = Editor;
        this.estado = estado;
        this.numEjemplares = numEjemplares;
    }

    public Libro(int isbn, String titulo, String autor, int anio, String tipo, String Editor, EstadosEjemplar estado, int numEjemplares) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.tipo = tipo;
        this.Editor = Editor;
        this.estado = estado;
        this.numEjemplares = numEjemplares;
    }

    
    
    // solo para prueba
    public Libro(int idLibro, EstadosEjemplar estado) {
        this.idLibro = idLibro;
        this.estado = estado;
    }

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    
    
    
    public Libro(int idLibro) {
        this.idLibro = idLibro;
    }


    
    
    //se creo solo para el metodo listarlibroXAutor
    public Libro(String titulo, int numEjemplares) { 
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
    }
    
    

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }

    public EstadosEjemplar isEstado() {
        return estado;
    }

    public void setEstado(EstadosEjemplar estado) {
        this.estado = estado;
    }

    public int getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(int numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    @Override
    public String toString() {
        return "Libro{" + "idLibro=" + idLibro + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + ", tipo=" + tipo + ", Editor=" + Editor + ", estado=" + estado + ", numEjemplares=" + numEjemplares + '}';
    }


   
   
   
}
