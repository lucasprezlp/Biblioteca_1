
package biblioteca.Entidades;


public class Lector {
   public int idLector;
   public int nroSocio;
   public String nombreCompleto;
   public String domicilio;
   public String mail;
   public int dni;
   public int telefono;
   public boolean estado;
   
    public Lector() {
    }

    public Lector(int idLector, int nroSocio, String nombreCompleto, String domicilio, String mail, int dni, int telefono, boolean estado) {
        this.idLector = idLector;
        this.nroSocio = nroSocio;
        this.nombreCompleto = nombreCompleto;
        this.domicilio = domicilio;
        this.mail = mail;
        this.dni = dni;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Lector(int nroSocio, String nombreCompleto, String domicilio, String mail, int dni, int telefono, boolean estado) {
        this.nroSocio = nroSocio;
        this.nombreCompleto = nombreCompleto;
        this.domicilio = domicilio;
        this.mail = mail;
        this.dni = dni;
        this.telefono = telefono;
        this.estado = estado;
    }
    

    public Lector(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Lector(int idLector) {
        this.idLector = idLector;
    }

    
    
    
    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public int getNroSocio() {
        return nroSocio;
    }

    public void setNroSocio(int nroSocio) {
        this.nroSocio = nroSocio;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Lector{" + "idLector=" + idLector + ", nroSocio=" + nroSocio + ", nombreCompleto=" + nombreCompleto + ", domicilio=" + domicilio + ", mail=" + mail + ", dni=" + dni + ", telefono=" + telefono + ", estado=" + estado + '}';
    }

  
}
