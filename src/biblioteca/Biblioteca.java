/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.Data.LectorData;
import biblioteca.Data.LibroData;
import biblioteca.Data.EjemplarData;
import biblioteca.Data.PrestamoData;
import biblioteca.Entidades.Ejemplar;
import biblioteca.Entidades.EstadosEjemplar;
import static biblioteca.Entidades.EstadosEjemplar.*;
import biblioteca.Entidades.Lector;
import biblioteca.Entidades.Libro;
import biblioteca.Entidades.Prestamo;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Biblioteca {

    public static void main(String[] args) {
      
        ///PRUEBAS DE LA CLASE LECTORDATA
        
        // Lector lector = new Lector(14, "Juan Perez", "Jose Marmol 78", "juancito@yahoo.com.ar", 4587584, 111111, true);//metodo guardar y eliminar
//         Lector lec1 =new Lector(58, "Maria Robledo", "Rivadavia 789", "mariaro@gmail.com", 25665895, 22222, true);/// metodo modificar
//         Lector lector2 = new Lector(16, "Jose armendo", "pasaje 45", "juancito@yahoo.com.ar", 45647584, 111111, true);
//         Lector lector3 = new Lector(17, "Veronica acosta", "Ribadavia", "juancito@yahoo.com.ar", 7737584, 111111, true);
          Lector lector = new Lector(2, 14, "Juan Perez", "Jose Marmol 78", "juancito@yahoo.com.ar", 4587584, 111111, true);
            LectorData ld = new LectorData();
        // ld.desactivarLector(1);
        // ld.activarLector(1);    
//        ld.guardarLector(lector);
//        ld.guardarLector(lec1);
//        ld.guardarLector(lector2);
//        ld.guardarLector(lector3);
        //ld.modificarLector(lec1); /////((((para cambiar y hacer upgrade, usamos el constructor con id)))///
        
        ///PRUEBAS DE LA CLASE LIBRODATA
        
  //      Libro lib = new Libro(1, "Caperucita roja", "Pedrito Gómez", 1985, "Cuento infantil", "Ed Planeta", DISPONIBLE, 5);
        Libro lib1 = new Libro(1, 0, "Cien años de soledad", "Gabriel García Márquez", 1967, "Ficción", "Editorial XYZ", DISPONIBLE, 3);  //bd Veronica
 //       Libro lib1 = new Libro(10,9,"El principito","Juancito Botaro",1974,"Novela","Ediciones ULP",REPARACION,2);
//           Libro lib1 = new Libro(2, 1, "1974", "George Orwell", 1974, "Novela", "Ediciones ULP", DISPONIBLE, 2); //bd Veronica
//        LibroData lbd = new LibroData();  
//        Libro lib = new Libro (9,PRESTADO);
        //lbd.guardarLibro(lib);
//        lbd.modificarLibro(lib);   
        //lbd.eliminarLibro(1);
        
//        for(Libro lib1: lbd.listarLibrosXautor("Gabriel García Márquez")){
//            System.out.println("titulo "+ lib1.getTitulo());
//            System.out.println("numEjemplares "+ lib1.getNumEjemplares());
//        }
        
        
       
       ///PRUEBAS DE LA CLASE EJEMPLARDATA
       
//        Ejemplar ej = new Ejemplar(2,EstadosEjemplar.DISPONIBLE); //se usa para modificar
        //Ejemplar ej = new Ejemplar(32, lib1, EstadosEjemplar.DISPONIBLE); //se usa para guardar, està en la base de datos de Lucas
       // Ejemplar ej = new Ejemplar();
        //Ejemplar ej = new Ejemplar(1, 20, lib1, EstadosEjemplar.DISPONIBLE); // se usa para prestar ejemplares, está en las base de Veronica
        Ejemplar ej = new Ejemplar(2, 21, lib1, EstadosEjemplar.DISPONIBLE); // se usa para prestar ejemplares, está en las base de Veronica
        EjemplarData ejeD = new EjemplarData();
        ejeD.modificarEjemplar(20,EstadosEjemplar.DISPONIBLE);
//          ejeD.guardarEjemplar(ej, 2);
//        Ejemplar ej = new Ejemplar(0, lib, EstadosEjemplar.DISPONIBLE);
//        EjemplarData eje = new EjemplarData();
//        ejeD.stock(10);
//        ejeD.eliminarEjemplar(1);


        ///PRUEBAS DE LA CLASE PRESTAMODATA   ///(Veronica: usar lo de date de abajo es para cuando queres convertir de date a localdate
        
        //Date date1= new Date();       
        //Instant instant= date1.toInstant();
        //ZonedDateTime zdt= instant.atZone(ZoneId.systemDefault());
       // dato = zdt.toLocalDate().atTime(2023, 05, 05);
        //dato = zdt.toLocalDate().compareTo();
        //dato = zdt.toLocalDate().format("2023, 05, 05")     
       // Date date2= new Date();       
        //Instant instant1= date2.toInstant();
       // ZonedDateTime zdt1= instant.atZone(ZoneId.systemDefault());
       // dato1 = zdt1.toLocalDate();    
        
    //    LocalDate dato=LocalDate.of(2023, 8, 05); ///FECHAS PARA INICIAR PRESTAMO
    //    LocalDate dato1=LocalDate.of(2023, 10, 15); ///FECHAS PARA DEVOLVER PRESTAMO
       // boolean a =dato.isAfter(dato1);
      
//        Prestamo prestamo1 = new Prestamo(LocalDate.of(2023, 8, 05), LocalDate.of(2023, 10, 15), ej, lector, true); //se usa para prestar ejemplar
//         Prestamo prestamo2 = new Prestamo(24,LocalDate.of(2023, 8, 05), LocalDate.of(2023, 10, 15), ej, lector, true); // se usa para devolver ejemplar
      Libro libro = new Libro ("Cien años de soledad");
Ejemplar ej3 = new Ejemplar(21);
Lector lec = new Lector ("Juan Perez");
Prestamo prestamo2 = new Prestamo(ej3,lec); // se usa para devolver ejemplar

        PrestamoData pres = new PrestamoData();
        //       pres.prestarEjemplar(prestamo1);
        pres.devolucion(prestamo2);

//for(Lector lectores : pres.lectoresMorosos()){
//    System.out.println(lectores.toString());
//}

//
//for(Prestamo m : pres.lectoresXPrestamoDeLibros()){
//    System.out.println(m.toString());
//}
//librosxFecha(Date fecha)


//for(Prestamo m : pres.librosxFecha(LocalDate.of(2023, 8, 5))){
//    System.out.println(m.getFechaInicio()+" " +m.getEjemplar().getLibro().getTitulo());
//    System.out.println("\n");
}
    } 
    
    
    

