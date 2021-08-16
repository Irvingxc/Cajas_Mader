/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author grixe
 */
public class Conexion {
    public static Connection conection = null;
    public Connection getConexion() {
		try {
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			                 
                                             conection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC","root","");
                                            // conection=DriverManager.getConnection("jdbc:sqlserver://;database=PROYECTO_MP;integratedSecurity=true;");
                                               return conection;
                        

		} catch (ClassNotFoundException | SQLException ex) {
			return null;
			
		}
                
    
}
}
