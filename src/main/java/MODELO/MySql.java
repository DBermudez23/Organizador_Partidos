/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author danie
 */
public class MySql {
    
    Connection Conexion;
      
    public Connection base_datos() throws SQLException{   
        try {
            Class.forName("com.mysql.jdbc.Driver"); // ESTABLECE LA CONEXION BASE DE DATOS 
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/partidos?useSSL=false$serverTimezone=UTC","root","");// CONEXION A LA BASE DE DATOS ABIERTA                       
          } catch (ClassNotFoundException ex) {
              JOptionPane.showMessageDialog(null, "Error de  conexión con el servidor ");
          } return Conexion;
    }
    
}
