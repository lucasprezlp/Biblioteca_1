
package biblioteca.Data;

import biblioteca.Entidades.Ejemplar;
import biblioteca.Entidades.EstadosEjemplar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class EjemplarData {
     private Connection con=null;
    
    public EjemplarData(){
        con=conexion.getConexion();
    }
 PreparedStatement ps;
 
     public int stock(int idLibro) {
        ///agregar el autor y nombre de la obra 
        int num = 0;
        String sql = "SELECT COUNT(*) FROM ejemplar WHERE estado = 'DISPONIBLE' AND idLibro = ?"; // mostrar solo los libros que figuren DISPONIBLES
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int disponibles = rs.getInt("COUNT(*)");
                num = disponibles;
                JOptionPane.showMessageDialog(null, "el stock es de: " + disponibles);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
        }
        return num;
    }

    public void guardarEjemplar(Ejemplar ejemplar, int cantidad) {
 //////////// terminar: setear el numero de ejemplares si el libro ya existe en la bd
        while (cantidad > 0) {
            String sql = "INSERT INTO ejemplar (codigo,idLibro,estado)"
                    + " VALUES (?,?,?)";
            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, ejemplar.getCodigo());
                ps.setInt(2, ejemplar.getLibro().getIdLibro());
                ps.setString(3, ejemplar.getEstado().toString()); /////////////////////// consulta tenemos problemas en el estado en la bd 
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    ejemplar.setIdEjemplar(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Ejemplar agregado con exito");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un id correcto/diferente" + ex);
            }
            cantidad = cantidad - 1;
        }
    }

    public void modificarEjemplar(int codigo, EstadosEjemplar eje) {  /// podemos usarlo como metodo de ELIMINAR modificando el estado
//        String sql = "UPDATE ejemplar SET estado=?"
//                + "WHERE idEjemplar = ?";
//        try {
//            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, ejemplar.getEstado().toString());/////////////////////// consulta tenemos problemas en el estado en la bd 
//            ps.setInt(2, ejemplar.getIdEjemplar());
            String sql = "UPDATE ejemplar SET estado=?"
                + "WHERE codigo = ?";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, eje.toString());/////////////////////// consulta tenemos problemas en el estado en la bd 
            ps.setInt(2, codigo);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Ejemplar modificado con éxito");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al intentar modificar el ejemplar");
        }
    }

    public void eliminarEjemplar(int idEjemplar) {
        String sql = "UPDATE ejemplar SET estado = 'NO_DISPONIBLE' WHERE idEjemplar = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEjemplar);
            int exito = ps.executeUpdate();

            if (exito == 1) {

                JOptionPane.showMessageDialog(null, "Ejemplar No disponible");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + ex);
        }
    }
    

     }
     

