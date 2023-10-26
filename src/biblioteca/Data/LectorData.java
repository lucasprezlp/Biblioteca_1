
package biblioteca.Data;

import biblioteca.Entidades.Lector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class LectorData {
    private Connection con=null;
    
    public LectorData(){
        con=conexion.getConexion();
    }
    
    PreparedStatement ps;
    
    public void activarLector(int idLector){
        String sql = "UPDATE lector SET estado = 1 WHERE idLector=?";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idLector);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Se activo el lector");
            }
   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la BD lector");        }
        
    }
    
    public void desactivarLector(int idLector){
        String sql = "UPDATE lector SET estado = 0 WHERE idLector=?";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idLector);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Se DESactivo el lector");
            } 
   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la BD lector");        }
        
    } 
    
    public void guardarLector(Lector lector){
        String sql = "INSERT INTO lector(nroSocio, nombreCompleto, domicilio, mail, dni, telefono, estado)"
                + " VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lector.getNroSocio());
            ps.setString(2, lector.getNombreCompleto());
            ps.setString(3, lector.getDomicilio());
            ps.setString(4, lector.getMail());
            ps.setInt(5, lector.getDni());
            ps.setInt(6, lector.getTelefono());
            ps.setBoolean(7, lector.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lector.setIdLector(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Lector agregado con exito");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un id correcto/diferente");
        }
    }  
    
    public void modificarLector(Lector lector){
       String sql= "UPDATE lector SET nroSocio=?,nombreCompleto=?,domicilio=?,mail=?,dni=?,telefono=?,estado=?"
               + " WHERE idLector=?";
               try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lector.getNroSocio());
            ps.setString(2, lector.getNombreCompleto());
            ps.setString(3, lector.getDomicilio());
            ps.setString(4, lector.getMail());
            ps.setInt(5, lector.getDni());
            ps.setInt(6, lector.getTelefono());
            ps.setBoolean(7, lector.isEstado());
            ps.setInt(8, lector.getIdLector());
            ps.executeUpdate();
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Lector modificado con éxito");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al intentar modificar el lector " + ex);
        }
       
    }
    
    ////falta listar lector???????????????????????
}
