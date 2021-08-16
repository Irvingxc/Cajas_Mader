/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Elssy
 */
public class DataBase {

    private static base.Conexion con = new Conexion();
    private static Connection conexion = con.getConexion();
    private static PreparedStatement ps = null;

    conectar cc = new conectar();
    Connection cn = cc.conexion();
    DefaultTableModel modelopedidos;
    TableRowSorter rowSorter;
    int IDBUSQUEDA = 1;
    static javax.swing.JTable tbpedidos;

    public static Object[][] cargarcajas() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement sql = conectar.createStatement();
            ResultSet res = sql.executeQuery("select * from registroCajas");
            ArrayList<Object[]> filas = new ArrayList<>();

            while (res.next()) {
                filas.add(new Object[]{res.getString("id_caja"),
                    res.getString("marca"),
                    res.getString("vitola"),
                    //res.getString("nombres_padre"),
                    res.getString("nombre"),
                    res.getString("capa"),
                    res.getString("tipo_empaque"),
                    res.getString("invent_inicial")});
            }

            Object[][] tabla = new Object[filas.size()][];
            filas.toArray(tabla);

            return tabla;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //ID AUTOINCREMENTABLE PARA CAJAS
    public static String extraerIdMaxCajas() {

        Connection conectar = null;
        String sql = "SELECT MAX(id_caja)FROM registrocajas";
        int id_caja = 0;

        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id_caja = rs.getInt(1);
            }
            if (id_caja == 0) {
                id_caja = 1;
            } else {
                id_caja = id_caja + 1;

            }

            return String.valueOf(id_caja);
        } catch (SQLException ex) {
            return null;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static Object[][] cargarpedidos() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement sql = conectar.createStatement();
            ResultSet res = sql.executeQuery("select * from pedidosNuevos");
            ArrayList<Object[]> filas = new ArrayList<>();

            while (res.next()) {
                filas.add(new Object[]{res.getString("id_pedido"),
                    //res.getString("id_caja"),
                    res.getString("marca_pedido"),
                    //res.getString("nombres_padre"),
                    res.getString("vitola_pedido"),
                    res.getString("nombre_pedido"),
                    res.getString("capa_pedido"),
                    res.getString("tipo_empaque_pedido"),
                    res.getString("invent_inicial_pedido"),
                    res.getString("enero"),
                    res.getString("febrero"),
                    res.getString("marzo"),
                    res.getString("abril"),
                    res.getString("mayo"),
                    res.getString("junio"),
                    res.getString("julio"),
                    res.getString("agosto"),
                    res.getString("septiembre"),
                    res.getString("octubre"),
                    res.getString("noviembre"),
                    res.getString("diciembre"),
                    res.getString("observacion_orden"),
                    res.getString("total_orden")});
            }

            Object[][] tabla = new Object[filas.size()][];
            filas.toArray(tabla);

            return tabla;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //ID AUTOINCREMENTABLE PARA PEDIDOS
    public static String extraerIdMaxPedidos() {

        Connection conectar = null;
        String sql = "SELECT MAX(id_pedido)FROM pedidosnuevos";
        int id_pedido = 0;

        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id_pedido = rs.getInt(1);
            }
            if (id_pedido == 0) {
                id_pedido = 1;
            } else {
                id_pedido = id_pedido + 1;

            }

            return String.valueOf(id_pedido);
        } catch (SQLException ex) {
            return null;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
// CARGAR DATOS A LA TABLA DE INVENTARIO AL INICIAR - CREO QUE NO LO USO

    public static Object[][] cargarDatosInventario() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement sql = conectar.createStatement();
            ResultSet res = sql.executeQuery("SELECT * FROM inventario");
            ArrayList<Object[]> filas = new ArrayList<>();

            while (res.next()) {
                filas.add(new Object[]{res.getString("id_inventario"),
                    //res.getString("id_caja"),
                    res.getString("id_caja"),
                    //res.getString("nombres_padre"),
                    res.getString("marca_inventario"),
                    res.getString("vitola_inventario"),
                    res.getString("nombre_inventario"),
                    res.getString("capa_inventario"),
                    res.getString("tipo_empaque_inventario"),
                    res.getString("invent_inicial_inventario"),
                    res.getString("total_entradas_inventario"),
                    res.getString("total_salidas_inventario"),
                    res.getString("total_orden_inventario"),
                    res.getString("total_invent"),
                    res.getString("fecha_inventario")
                });
            }

            Object[][] tabla = new Object[filas.size()][];
            filas.toArray(tabla);

            return tabla;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //ID AUTOINCREMENTABLE PARA PEDIDOS
    public static String extraerIdMaxInventario() {

        Connection conectar = null;
        String sql = "SELECT MAX(id_inventario)FROM inventario";
        int id_pedido = 0;

        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id_pedido = rs.getInt(1);
            }
            if (id_pedido == 0) {
                id_pedido = 1;
            } else {
                id_pedido = id_pedido + 1;

            }

            return String.valueOf(id_pedido);
        } catch (SQLException ex) {
            return null;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    //METODO PARA MOSTRAR LOS REGISTROS DE USUARIO
     public static Object[][] cargarUsuarios() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement sql = conectar.createStatement();
            ResultSet res = sql.executeQuery("select * from usuarios");
            ArrayList<Object[]> filas = new ArrayList<>();

            while (res.next()) {
                filas.add(new Object[]{res.getString("usuario"),
                    res.getString("nombre"),
                    res.getString("correo"),
                    //res.getString("nombres_padre"),
                    res.getString("last_session")});
            }

            Object[][] tabla = new Object[filas.size()][];
            filas.toArray(tabla);

            return tabla;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // GRIXEL 
    /**
     * ********************
     * METODOS PARA REMISIONES ********************
     */
    //ID AUTOINCREMENTABLE PARA REMISIONES
    public static String extraerIdMaxRemisiones() {

        Connection conectar = null;
        String sql = "SELECT MAX(id_remision)FROM remisiones";
        int id_remision = 0;

        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id_remision = rs.getInt(1);
            }
            if (id_remision == 0) {
                id_remision = 1;
            } else {
                id_remision = id_remision + 1;

            }

            return String.valueOf(id_remision);
        } catch (SQLException ex) {
            return null;

        }

    }

    //MOSTRAR REMISIONES SEGUN EL ID
    /*public static void setListarRemisiones(String buscar) {
        DefaultTableModel modelo = (DefaultTableModel) crearNuevaRemision.jTablenuevaremision.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);

        }

        String sql = "";
        if (buscar.equals("")) {
            sql = "select *from remisiones";

        } else {

            sql = "select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque, rem.id_remision, rem.num_remision, rem.fecha_remision, rem.cant_remision, rem.precio_unitario "
                    + "from registrocajas as caj "
                    + "join remisiones as rem "
                    + "on caj.id_caja = rem.id_caja "
                    + "where caj.id_caja= '" + buscar + "'";
        }

        String datos[] = new String[11];

        try {

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString("caj.id_caja");
                datos[1] = rs.getString("caj.marca");
                datos[2] = rs.getString("caj.vitola");
                datos[3] = rs.getString("caj.nombre");
                datos[4] = rs.getString("caj.capa");
                datos[5] = rs.getString("caj.tipo_empaque");
                datos[6] = rs.getString("rem.id_remision");
                datos[7] = rs.getString("rem.num_remision");
                datos[8] = rs.getString("rem.fecha_remision");
                datos[9] = rs.getString("rem.cant_remision");
                datos[10] = rs.getString("rem.precio_unitario");

                modelo.addRow(datos);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);

        }

    }*/

    public static Object[][] cargarRemisionById(String id) {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement sql = c.prepareStatement("select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n"
                    + "rem.num_remision, rem.fecha_remision, rem.cant_remision, rem.precio_unitario, \n"
                    + "tot.total_cant_remision from registrocajas as caj join remisiones as rem join remisiontotales as tot\n"
                    + "on caj.id_caja = rem.id_caja and caj.id_caja = tot.id_caja where caj.id_caja= ?");
            ArrayList<Object[]> filas = new ArrayList<>();
            sql.setString(1, id);
            ResultSet res = sql.executeQuery();
            System.out.println("el id es as  " + id);
            while (res.next()) {
                filas.add(new Object[]{res.getString("id_caja"),
                    res.getString("marca"),
                    res.getString("vitola"),
                    res.getString("nombre"),
                    res.getString("capa"),
                    res.getString("tipo_empaque"),
                    res.getString("num_remision"),
                    res.getString("fecha_remision"),
                    res.getString("cant_remision"),
                    res.getString("total_cant_remision"),
                    res.getString("precio_unitario")});
            }

            Object[][] tabla = new Object[filas.size()][];
            filas.toArray(tabla);

            return tabla;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "2");
            return null;
        }
    }

    public static void ActualizarRemisiones(String id_remision, String id_caja, String num_remision, String fecha_remision, String cant_remision, String precio_unitario) {
        Connection c = null;
        try {
            String sql = ("update remisiones set id_caja= ?, num_remision= ?, fecha_remision= ?, cant_remision= ?, precio_unitario= ? where id_remision= ?");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement query = c.prepareStatement(sql);

            query.setString(1, id_caja);
            query.setString(2, num_remision);
            query.setString(3, fecha_remision);
            query.setString(4, cant_remision);
            query.setString(5, precio_unitario);
            query.setString(6, id_remision);

            query.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //VALIDAR ID PARA BOTON AÑADIR
    public static int validarID(String id) {
        int resultado = 0;
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement st = c.prepareStatement("select * from remisiones where id_remision=? ");
            st.setString(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                resultado = 1;
            }

        } catch (Exception e) {

        } finally {
            try {

            } catch (Exception e) {

            }
        }
        return resultado;
    }

    public static void ActualizarRemisionesTotales(String id_caja, String total_cant_remision) {
        Connection c = null;
        try {
            String sql = ("update remisiontotales set total_cant_remision= ? where id_caja= ?");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement query = c.prepareStatement(sql);

            query.setString(1, total_cant_remision);
            query.setString(2, id_caja);

            query.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // CARGAR REGISTRO DE REMISIONES
    public static Object[][] cargarRemisones() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement sql = c.createStatement();
            ResultSet res = sql.executeQuery("select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n"
                    + "rem.id_remision, rem.num_remision, rem.fecha_remision, rem.cant_remision, rem.precio_unitario\n"
                    + "from registrocajas as caj join remisiones as rem\n"
                    + "on caj.id_caja = rem.id_caja");
            ArrayList<Object[]> filas = new ArrayList<>();

            while (res.next()) {
                filas.add(new Object[]{res.getString("id_caja"),
                    res.getString("marca"),
                    res.getString("vitola"),
                    res.getString("nombre"),
                    res.getString("capa"),
                    res.getString("tipo_empaque"),
                    res.getString("id_remision"),
                    res.getString("num_remision"),
                    res.getString("fecha_remision"),
                    res.getString("cant_remision"),
                    res.getString("precio_unitario")});
            }

            Object[][] tabla = new Object[filas.size()][];
            filas.toArray(tabla);

            return tabla;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * ******************
     * METODOS PARA ENTRADAS ******************
     */
    //ID AUTOINCREMENTABLE PARA ENTRADAS
    public static String extraerIdMaxEntradas() {

        Connection conectar = null;
        String sql = "SELECT MAX(id_entrada)FROM entradas";
        int id_entrada = 0;

        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id_entrada = rs.getInt(1);
            }
            if (id_entrada == 0) {
                id_entrada = 1;
            } else {
                id_entrada = id_entrada + 1;

            }

            return String.valueOf(id_entrada);
        } catch (SQLException ex) {
            return null;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static void ActualizarEntradas(String id_caja, String total_remision, String total_entradas) {
        Connection c = null;
        try {
            String sql = ("update entradas set total_remision= ?, total_entradas= ? where id_caja= ?");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement query = c.prepareStatement(sql);

            query.setString(1, total_remision);
            query.setString(2, total_entradas);
            query.setString(3, id_caja);

            query.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * *****************
     * METODOS PARA SALIDAS ******************
     */
    //ID AUTOINCREMENTABLE PARA SALIDAS
    public static String extraerIdMaxSalidas() {

        Connection conectar = null;
        String sql = "SELECT MAX(id_salida)FROM salidas";
        int id_salida = 0;

        try {
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            Statement st = conectar.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id_salida = rs.getInt(1);
            }
            if (id_salida == 0) {
                id_salida = 1;
            } else {
                id_salida = id_salida + 1;

            }

            return String.valueOf(id_salida);
        } catch (SQLException ex) {
            return null;
//            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    //VALIDAR ID PARA BOTON AÑADIR
    public static int validarIDSalida(String id) {
        int resultado = 0;
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement st = c.prepareStatement("select * from salidas where id_salida=?");
            st.setString(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                resultado = 1;
            }

        } catch (Exception e) {

        } finally {
            try {

            } catch (Exception e) {

            }
        }
        return resultado;
    }

    public static void ActualizarSalidas(String id_salida, String id_caja, String num_contenedor, String cant_contenedor, String fecha_contenedor) {
        Connection c = null;
        try {
            String sql = ("update salidas set id_caja= ?, num_contenedor= ?, cant_contenedor= ?, fecha_contenedor= ? where id_salida= ?");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement query = c.prepareStatement(sql);

            query.setString(1, id_caja);
            query.setString(2, num_contenedor);
            query.setString(3, cant_contenedor);
            query.setString(4, fecha_contenedor);
            query.setString(5, id_salida);

            query.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void ActualizarSalidasTotales(String id_caja, String total_salidas) {
        Connection c = null;
        try {
            String sql = ("update salidastotales set total_salidas= ? where id_caja= ?");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pCajasMadera?serverTimezone=UTC", "root", "");
            PreparedStatement query = c.prepareStatement(sql);

            query.setString(1, total_salidas);
            query.setString(2, id_caja);

            query.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
