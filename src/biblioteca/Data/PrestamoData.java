

package biblioteca.Data;

import biblioteca.Entidades.Ejemplar;
import biblioteca.Entidades.EstadosEjemplar;
import biblioteca.Entidades.Lector;
import biblioteca.Entidades.Libro;
import biblioteca.Entidades.Prestamo;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;



public class PrestamoData {
     private Connection con=null;
    
    public PrestamoData(){
        con=conexion.getConexion();
    }
    
    PreparedStatement ps;
    
    
    
    
    public String buscartituloConElCodigo(int codigo){
        String titulo="";
 
        String sql6="SELECT titulo FROM `libro` \n" +
"JOIN `ejemplar` ON libro.idLibro= ejemplar.idLibro\n" +
"WHERE codigo=?;";
         try {
         ps=con.prepareStatement(sql6,Statement.RETURN_GENERATED_KEYS);
         ps.setInt(1, codigo);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
             titulo=rs.getString("titulo");
         } else {
             JOptionPane.showMessageDialog(null, "Libro no encontrado");
             
         }
             } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al consultar el titulo");

             
        
    } 
         
    return titulo;
    
    }
    

    
    
    public void prestarEjemplar(Prestamo prestamo){
        
           try {
            String sql = "SELECT idLector FROM lector WHERE nombreCompleto = ?;";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prestamo.getLector().getNombreCompleto());

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Este lector no está registrado");
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar si el lector está registrado");
        }

        
//consulto si hay libros disponibles que corresponda al código
        String sqla = "SELECT idEjemplar, codigo, ejemplar.idLibro, ejemplar.estado FROM ejemplar\n" +
"JOIN libro ON (ejemplar.idLibro = libro.idLibro)\n" +
"WHERE titulo =? and ejemplar.estado = 'DISPONIBLE' and ejemplar.codigo=?";
        Ejemplar ejemplar = null;
         try {
             ps=con.prepareStatement(sqla,Statement.RETURN_GENERATED_KEYS);
             ps.setString(1,prestamo.getEjemplar().getLibro().getTitulo());
             ps.setInt(2, prestamo.getEjemplar().getCodigo());
             ResultSet rs = ps.executeQuery();

             if (rs.next()){
                 
                 ejemplar = new Ejemplar();
                 Libro libro = new Libro();
                 ejemplar.setIdEjemplar(rs.getInt("idEjemplar"));
                 ejemplar.setCodigo(rs.getInt("codigo"));
                 libro.setIdLibro(rs.getInt("idLibro"));
                 ejemplar.setLibro(libro);
                 ejemplar.setEstado(EstadosEjemplar.DISPONIBLE);
//                 JOptionPane.showMessageDialog(null, "se creo el ejemplar"+ ejemplar);
             } else {
                 JOptionPane.showMessageDialog(null, "No hay libros disponibles");
                 return;
             }
//             JOptionPane.showMessageDialog(null, ejemplar.toString());
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al consultar si hay libros disponibles");
         }
         
//chequeamos que el lector no sea moroso


        String sql3 = "SELECT DISTINCT l.idLector\n"
                + "FROM lector l\n"
                + "WHERE l.nombreCompleto = ?\n"
                + "AND NOT EXISTS (\n"
                + "    SELECT 1\n"
                + "    FROM prestamo p\n"
                + "    WHERE p.idLector = l.idLector\n"
                + "    AND (\n"
                + "        (p.estado = 1 AND p.FechaFin < CURRENT_DATE())\n"
                + "    )\n"
                + ");";
        Lector lector = new Lector();
         try {
             ps = con.prepareStatement(sql3);
             ps.setString(1,prestamo.getLector().getNombreCompleto());
             ResultSet rs= ps.executeQuery();
             if(rs.next()){
                lector = new Lector(rs.getInt("idLector"));
//                 JOptionPane.showMessageDialog(null, "lectores no morosos "+ lector);
             } else {
                 JOptionPane.showMessageDialog(null, "Lectore morosos ");
                 return;
             }
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error en chequeamos que el lector no sea moroso");
         }

// creamos el prestamos en el registro prestamos
        String sql = "INSERT INTO prestamo(FechaInicio, FechaFin, idEjemplar, idLector, estado) "
                + "VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, java.sql.Date.valueOf(prestamo.getFechaInicio()));
            ps.setDate(2, java.sql.Date.valueOf(prestamo.getFechaFin()));
            ps.setInt(3, ejemplar.getIdEjemplar()); // viene de la primera consulta
            ps.setInt(4, lector.getIdLector()); // viene de la segunda consulta
            ps.setBoolean(5, prestamo.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                prestamo.setIdPrestamo(rs.getInt(1));
//                JOptionPane.showMessageDialog(null, "prestamo agregado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "ver el error");
            }
// //Actualizamos el estado del ejemplar
             String sql2 = "UPDATE ejemplar SET estado='PRESTADO' WHERE idEjemplar=?";
              ps = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
              ps.setInt(1, ejemplar.getIdEjemplar()); // primera consulta
              
              int exito = ps.executeUpdate();
              if (exito ==1 ){
//                JOptionPane.showMessageDialog(null, "Ejemplar modificado con éxito");
              }

         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error : Actualizamos el estado del ejemplar");
         }
              JOptionPane.showMessageDialog(null, "Prestamo exitoso.");
    }


// como hacer para que el metodo de transformar a moroso sea automatico

//devolucion (cambair el estado a 0)

    public void devolucion(Prestamo prestamo) {

        try {
            //verificamos si exise el pestamo
            String sql7 = "SELECT prestamo.idPrestamo FROM `prestamo` "
                    + "JOIN ejemplar ON prestamo.idEjemplar=ejemplar.idEjemplar "
                    + "JOIN lector ON prestamo.idLector=lector.idLector "
                    + "WHERE ejemplar.codigo=? AND lector.nombreCompleto=? AND prestamo.estado=1;";

            ps = con.prepareStatement(sql7);
            ps.setInt(1, prestamo.getEjemplar().getCodigo());
            ps.setString(2, prestamo.getLector().getNombreCompleto());
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                        //modificamos el ejemplar a diponible
                        String sql = "UPDATE ejemplar SET estado='DISPONIBLE' WHERE codigo=?";
                        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, prestamo.getEjemplar().getCodigo());

                        int exito = ps.executeUpdate();
                        if (exito == 1) {
                            //                JOptionPane.showMessageDialog(null, "Ejemplar modificado con éxito");
                        }
                        // modificamos prestamo colancado 0 en su estado
                        String sql1 = "UPDATE prestamo\n"
                                + "JOIN lector ON prestamo.idLector = lector.idLector\n"
                                + "JOIN ejemplar ON prestamo.idEjemplar = ejemplar.idEjemplar\n"
                                + "SET prestamo.estado = 0\n"
                                + "WHERE lector.nombreCompleto =?  AND ejemplar.codigo = ?";
                        ps = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, prestamo.getLector().getNombreCompleto());
                        ps.setInt(2, prestamo.getEjemplar().getCodigo());

                        int exito1 = ps.executeUpdate();
                        if (exito1 == 1) {
        //                JOptionPane.showMessageDialog(null, "Prestamo modificado con éxito");
                        }

                        JOptionPane.showMessageDialog(null, "Devolución Exitosa");
                } else {
                         JOptionPane.showMessageDialog(null, "No hay evolución pendiente con este nombre y libro. Por favor verifique los datos");
                }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex);
        }
    }

// lectoresMorosos (fecha actual menos fecha de fin)
    public ArrayList<Lector> lectoresMorosos(){
        ArrayList<Lector> lectoresMorosos = new ArrayList<>();
        String sql = "SELECT nombreCompleto\n" +
"FROM prestamo\n" +
"JOIN lector ON prestamo.idLector = lector.idLector\n" +
"WHERE prestamo.estado = 1 AND prestamo.FechaFin < CURRENT_DATE();";
         try {
             ps = con.prepareStatement(sql);
             ResultSet rs= ps.executeQuery();
             while(rs.next()){
                 Lector lector = new Lector();
                 lector.setNombreCompleto(rs.getString("nombreCompleto"));
                 lectoresMorosos.add(lector);
             }
             JOptionPane.showMessageDialog(null, "lectores morosos " + lectoresMorosos);
         } catch (SQLException ex) {
             Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        
        return lectoresMorosos;
        
        
        //////////////////////////////// hacer 2 actualizaciones pasar el lector a estado 1, pasar el ejemplar como retraso
        
    }

//libroprestadoXFechaEnunaFechaDeterminada
        
      public ArrayList<Prestamo> librosxFecha(LocalDate fecha){
        ArrayList<Prestamo> librosxFecha = new ArrayList<>();
        String sql = "SELECT prestamo.FechaInicio, libro.titulo FROM prestamo \n" +
"JOIN ejemplar ON prestamo.idEjemplar = ejemplar.idEjemplar\n" +
"JOIN libro ON ejemplar.idLibro = libro.idLibro\n" +
"WHERE prestamo.FechaInicio=?";
         try {
             ps = con.prepareStatement(sql);
             ps.setDate(1,java.sql.Date.valueOf(fecha.toString()));
             ResultSet rs= ps.executeQuery();
             
             while(rs.next()){
                 Libro libroxFecha = new Libro(rs.getString("titulo"));
                 Ejemplar ejemplar = new Ejemplar(libroxFecha);
                 Prestamo prestamo = new Prestamo(rs.getDate("FechaInicio").toLocalDate(),ejemplar);
                 librosxFecha.add(prestamo);
             }
             JOptionPane.showMessageDialog(null, "libros x Fecha " + librosxFecha);
         } catch (SQLException ex) {
             Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
         }
        return librosxFecha;
    }

// listar lectores que pidieron prestamos y los los libros que fueron prestados
 public ArrayList<Prestamo> lectoresXPrestamoDeLibros(){
        ArrayList<Prestamo> lectoresXPrestamoDeLibros = new ArrayList<>();
        String sql = "SELECT lector.nombreCompleto, libro.titulo  FROM prestamo \n" +
"JOIN lector ON prestamo.idLector = lector.idLector\n" +
"JOIN ejemplar ON prestamo.idEjemplar = ejemplar.idEjemplar\n" +
"JOIN libro ON ejemplar.idLibro = libro.idLibro\n" +
"WHERE prestamo.estado=1";
         try {
             ps = con.prepareStatement(sql);
             ResultSet rs= ps.executeQuery();
             while(rs.next()){
                 Lector lector = new Lector(rs.getString("nombreCompleto"));
                 Libro libro = new Libro(rs.getString("titulo"));
                 Ejemplar ejemplar = new Ejemplar(libro);
                 Prestamo prestamo = new Prestamo(ejemplar,lector);
                 lectoresXPrestamoDeLibros.add(prestamo);
             }
            
             JOptionPane.showMessageDialog(null, "lectores " + lectoresXPrestamoDeLibros);
         } catch (SQLException ex) {
             Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        
        return lectoresXPrestamoDeLibros;
    }
}

