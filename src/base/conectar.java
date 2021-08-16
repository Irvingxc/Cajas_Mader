/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Elssy
 */
public class conectar {
    final static String URL = "jdbc:mysql://localhost:3306/pCajasMadera?useSSL=true";
	final static String USUARIO = "root";
	final static String CLAVE = "";
     Connection conectar=null;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC","root","");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }
    
   
}
