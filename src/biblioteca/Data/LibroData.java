/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Data;

import biblioteca.Entidades.Libro;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LibroData {

    private Connection con = null;

    public LibroData() {
        con = conexion.getConexion();
    }
    PreparedStatement ps;

    public void guardarLibro(Libro libro) {
        String sql = "INSERT INTO libro(idLibro, isbn, titulo, autor, anio, tipo, Editor, estado, numEjemplares)"
                + " VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, libro.getIdLibro());
            ps.setInt(2, libro.getIsbn());
            ps.setString(3, libro.getTitulo());
            ps.setString(4, libro.getAutor());
            ps.setInt(5, libro.getAnio());
            ps.setString(6, libro.getTipo());
            ps.setString(7, libro.getEditor());
            ps.setString(8, libro.estado.toString()); ///////////////// pasamos de boolean a String
            ps.setInt(9, libro.getNumEjemplares());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                libro.setIdLibro(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "libro agregado con exito");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un id correcto/diferente");
        }
    }

    public void modificarLibro(Libro libro) {
        String sql = "UPDATE libro SET isbn=?, titulo=?, autor=?, anio=?, tipo=?, editor=?, estado=?, numEjemplares=? "
                + "WHERE idLibro = ?";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setInt(4, libro.getAnio());
            ps.setString(5, libro.getTipo());
            ps.setString(6, libro.getEditor());
            ps.setString(7, libro.estado.toString());
            ps.setInt(8, libro.getNumEjemplares());
            ps.setInt(9, libro.getIdLibro());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "libro modificado con éxito");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al intentar modificar el libro");
        }
    }
     
    public ArrayList<Libro> listarLibrosXautor(String autor){
      String sql = "SELECT titulo, numEjemplares FROM libro WHERE autor like ?";  
       ArrayList<Libro> libros = new ArrayList<>(); 
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, autor);
            ResultSet rs = ps.executeQuery();
        
                while (rs.next()) {
                Libro libro = new Libro();
                libro.setTitulo(rs.getString("titulo"));
                libro.setNumEjemplares(rs.getInt("numEjemplares"));
                libros.add(libro);
                //JOptionPane.showMessageDialog(null, materias);
                ps.close();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el libro" + ex);
        }
        
     return libros;   
    }
        
    public void eliminarLibro(int idLibro) {
        String sql = "UPDATE libro SET estado = 0 WHERE idLibro = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLibro);           
            int exito = ps.executeUpdate();
           
            if (exito == 1) {

                JOptionPane.showMessageDialog(null, "Se desactivo el libro");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + ex);
        }
    }
}


