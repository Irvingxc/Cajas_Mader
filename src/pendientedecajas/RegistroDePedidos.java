/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendientedecajas;

import base.DataBase;
import base.conectar;
import com.placeholder.PlaceHolder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static pendientedecajas.Inventario.fechaActual;

/**
 *
 * @author Elssy
 */
public class RegistroDePedidos extends javax.swing.JFrame {

    /**
     * Creates new form RegistroDePedidos
     */
    final static String URL = "jdbc:mysql://localhost:3306/pCajasMadera?useSSL=true";
    final static String USUARIO = "root";
    final static String CLAVE = "";
    static JTable tbla_registro = new JTable() {

        //Quitar edición de las tablas
        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }
    };

    //Variable de instancia   
    conectar cc = new conectar();
    Connection cn = cc.conexion();
    DefaultTableModel modelopedidos;
    TableRowSorter trs;
    //int IDBUSQUEDA = 2;
    int IDBUSQUEDA1 = 2;
    int IDBUSQUEDA2 = 5;
    ArrayList<Object[]> filas = new ArrayList<>();

    //Variable Place HOLDER
    PlaceHolder holder;

    private static RegistroDePedidos myInstance;

    public RegistroDePedidos() {
        initComponents();
        setLocationRelativeTo(null);
        mostrardatoscajas("");
        mostrardatospedidos("");
        //cargarPedidosEjemplo();
        cargarPedidosEjemplo();
        txt_id_res.setVisible(false);
        //btn_añadir_orden.setVisible(false);
        txt_id_pedidos.setText(DataBase.extraerIdMaxPedidos());

        holder = new PlaceHolder(txtBuscar_cajas1, "Marca, Vitola, Nombre, Capa");
        holder = new PlaceHolder(txt_buscar_pedidos, "Marca, Vitola, Nombre, Capa");
        //PARA AÑADIR EL FOCUS A OTRO COMPONENTE
        btn_añadir.requestFocus();

        lbl_año_pedidos.setText(añoActual());

    }

    //Metodo para el total de suma
    public int sumaorden() {
        int contar = tbpedidos.getRowCount();
        int suma = 0;
        //int sumatoria1=0;
        //int totalRow= tbpedidos.getRowCount();
        //totalRow-=1*/
        //int fila =0;
        //int total = 0;

        for (int i = 0; i <= contar; i++) {
            //int sumatoria1+= (Integer.parseInt((String.valueOf(tbpedidos.getValueAt(i,8)) + (Integer.parseInt((String.valueOf(tbpedidos.getValueAt(i,9))));
            //sumatoria1 += (Integer.valueOf((String) filas.get(i)[8].toString()) + (Integer.valueOf((String) filas.get(i)[9].toString())));
            //fila = Integer.parseInt(tbpedidos.getValueAt(i, 8).toString());
            //sumatoria1+=sumatoria;
            //.addColumn(sumatoria1,"";
            //total +=fila;
            suma = suma + Integer.parseInt(tbpedidos.getValueAt(i, 8).toString() + Integer.parseInt(tbpedidos.getValueAt(i, 9).toString()));

            //tbpedidos.setValueAt(sumaorden(), i, 21);
        }

        //modelopedidos.addColumn("Total Ordenes");
        return suma;

    }

    /*void mostrardatospedidos(String valor){
    DefaultTableModel modelopedidos = new DefaultTableModel(){

		//Quitar edición de las tablas
	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};	
    String sql="";
    if(valor.equals(""))
    {
        sql="SELECT * FROM registroCajas";
    }
    
   
    modelopedidos.addColumn("Id Pedido");
     modelopedidos.addColumn("Id Caja");
    modelopedidos.addColumn("Marca");
    modelopedidos.addColumn("Vitola");
    modelopedidos.addColumn("Nombre");
    modelopedidos.addColumn("Capa");
    modelopedidos.addColumn("Tipo de Empaque");
    modelopedidos.addColumn("Iventario Inicial");
    modelopedidos.addColumn("Enero");
      modelopedidos.addColumn("Febrero");
      modelopedidos.addColumn("Marzo");
      modelopedidos.addColumn("Abril");
      modelopedidos.addColumn("Mayo");
      modelopedidos.addColumn("Junio");
      modelopedidos.addColumn("Julio");
      modelopedidos.addColumn("Agosto");
      modelopedidos.addColumn("Septiembre");
      modelopedidos.addColumn("Octubre");
      modelopedidos.addColumn("Noviembre");
      modelopedidos.addColumn("Diciembre");
      modelopedidos.addColumn("Total Ordenes");
    tbpedidos.setModel(modelopedidos);
    
    modelopedidos = new DefaultTableModel(DataBase.cargarpedidos(), columnas_pedido);
    tbpedidos.setModel(modelopedidos);
  
    /*modelopedidos.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getType() == TableModelEvent.UPDATE){
            int columna = e.getColumn();
            int fila = e.getFirstRow();
            if(columna == 8){
                String  sql = "UPDATE pedidosNuevos set enero='"+tbpedidos.getValueAt(fila, columna)+"'WHERE id_pedido='"+tbpedidos.getValueAt(fila, 0)+"' ";
                
            }
        }
        
        }
    });*/
    void mostrardatospedidos(String valor) {
        //String sql1 = "SELECT id_pedido, enero, febrero FROM pedidosnuevos";
        DefaultTableModel modelopedidos = new DefaultTableModel() {

            //Quitar edición de las tablas
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        //tbpedidos.setModel(modelopedidos);

        String sql = "SELECT id_pedido, enero, febrero FROM pedidosnuevos";
        if (valor.equals("")) {
            sql = "SELECT * FROM pedidosnuevos";
        }

        modelopedidos.addColumn("Id Pedido");
        modelopedidos.addColumn("Id Caja");
        modelopedidos.addColumn("Marca");
        modelopedidos.addColumn("Vitola");
        modelopedidos.addColumn("Nombre");
        modelopedidos.addColumn("Capa");
        modelopedidos.addColumn("Tipo de Empaque");
        modelopedidos.addColumn("Iventario Inicial");
        modelopedidos.addColumn("Enero");
        modelopedidos.addColumn("Febrero");
        modelopedidos.addColumn("Marzo");
        modelopedidos.addColumn("Abril");
        modelopedidos.addColumn("Mayo");
        modelopedidos.addColumn("Junio");
        modelopedidos.addColumn("Julio");
        modelopedidos.addColumn("Agosto");
        modelopedidos.addColumn("Septiembre");
        modelopedidos.addColumn("Octubre");
        modelopedidos.addColumn("Noviembre");
        modelopedidos.addColumn("Diciembre");
        modelopedidos.addColumn("Total Ordenes");
        modelopedidos.addColumn("Año");
        tbpedidos.setModel(modelopedidos);

        modelopedidos = new DefaultTableModel(DataBase.cargarpedidos(), columnas_pedido);
        tbpedidos.setModel(modelopedidos);

        //NO MOVER LAS COLUMNAS DE LA TABLA
        //tbpedidos.getTableHeader().setReorderingAllowed(false);
        // tbpedidos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        /*modelopedidos.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    
                    if(e.getType() == TableModelEvent.UPDATE){                        
                        String colname = null;
                          
                        if(e.getColumn()==8){
                            colname = "enero";
                        }
                        else if(e.getColumn()==9){
                            colname = "febrero";
                        }
                //String  sql = "UPDATE pedidosNuevos set enero='"+tbpedidos.getValueAt(fila, columna)+"',febrero='"+tbpedidos.getValueAt(fila, columna)+ "'WHERE id_pedido='"+tbpedidos.getValueAt(fila, 0)+"' ";
                       
                       String sql = "UPDATE pedidosnuevos SET "+colname+"='"+modelopedidos.getValueAt(e.getFirstRow(), e.getColumn())+"' WHERE id_pedido="+modelopedidos.getValueAt(e.getFirstRow(), 0);
                        conectar cc= new conectar();
                          Connection cn= cc.conexion();
                        PreparedStatement pst;
                        
                        try {
                            pst = cn.prepareStatement(sql);
                            int rows = pst.executeUpdate();
                        } catch (SQLException ex) {
                            Logger.getLogger(JTable.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR"+ex);
                        }
                        }
                }
                
                
    });*/
    }

    public void cargarara() {
        String sql = "SELECT id_pedido, id_caja, marca_pedido, vitola_pedido, nombre_pedido, capa_pedido, tipo_empaque_pedido, invent_inicial_pedido, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, sum(enero+febrero+marzo+abril+mayo+junio+julio+agosto+septiembre+octubre+noviembre+diciembre) as total, observacion_orden, año_pedidos from pedidosnuevos group by id_caja; ";

        conectar cc = new conectar();
        Connection cn = cc.conexion();
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();

            modelopedidos = new DefaultTableModel(new Object[][]{}, new String[]{"Id Pedido", "Id Caja", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Observación", "Total Orden", "Año"}) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return (col != 0);
                }
            };
            int suma = 0;
            //int s=0;

            while (res.next()) {
                modelopedidos.addRow(new String[]{
                    res.getString("id_pedido"),
                    res.getString("id_caja"),
                    res.getString("marca_pedido"),
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
                    //res.getInt(8),
                    res.getString("observacion_orden"),
                    res.getString("total"),
                    res.getString("año_pedidos")}
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(JTable.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR" + ex);
        }
    }

    //METODO PARA HACER SUMA DE LOS MESES, ESCRIBIR Y ACTUALIZAR EN LA TABLA DE PEDIDOS
    void cargarPedidosEjemplo() {
        String sql = "SELECT id_pedido, id_caja, marca_pedido, vitola_pedido, nombre_pedido, capa_pedido, tipo_empaque_pedido, invent_inicial_pedido, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre, sum(enero+febrero+marzo+abril+mayo+junio+julio+agosto+septiembre+octubre+noviembre+diciembre) as total, observacion_orden, año_pedidos from pedidosnuevos group by id_pedido; ";

        //int row = 0;
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();

            modelopedidos = new DefaultTableModel(new Object[][]{}, new String[]{"Id Pedido", "Id Caja", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Observación", "Total Orden", "Año"}) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return (col != 0);
                }
            };
            int suma = 0;
            //int s=0;

            while (res.next()) {
                modelopedidos.addRow(new String[]{
                    res.getString("id_pedido"),
                    res.getString("id_caja"),
                    //res.getString("nombres_padre"),
                    res.getString("marca_pedido"),
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
                    //res.getInt(8),
                    res.getString("observacion_orden"),
                    res.getString("total"),
                    res.getString("año_pedidos")}
                );

            }

            //rowSorter = new TableRowSorter(modelopedidos);
            //tbpedidos.setRowSorter(rowSorter);
            //EVENTO DE LA TABLA PARA ACTUALIZAR LOS CAMPOS DE LOS MESES.
            modelopedidos.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        String colname = null;
                        if (e.getColumn() == 8) {
                            colname = "enero";
                        } else if (e.getColumn() == 9) {
                            colname = "febrero";
                        } else if (e.getColumn() == 10) {
                            colname = "marzo";
                        } else if (e.getColumn() == 11) {
                            colname = "abril";
                        } else if (e.getColumn() == 12) {
                            colname = "mayo";
                        } else if (e.getColumn() == 13) {
                            colname = "junio";
                        } else if (e.getColumn() == 14) {
                            colname = "julio";
                        } else if (e.getColumn() == 15) {
                            colname = "agosto";
                        } else if (e.getColumn() == 16) {
                            colname = "septiembre";
                        } else if (e.getColumn() == 17) {
                            colname = "octubre";
                        } else if (e.getColumn() == 18) {
                            colname = "noviembre";
                        } else if (e.getColumn() == 19) {
                            colname = "diciembre";
                        }
                        String sql = "UPDATE pedidosnuevos SET " + colname + "='" + modelopedidos.getValueAt(e.getFirstRow(), e.getColumn()) + "' WHERE id_pedido=" + modelopedidos.getValueAt(e.getFirstRow(), 0);

                        conectar cc = new conectar();
                        Connection cn = cc.conexion();
                        PreparedStatement pst;

                        try {
                            pst = cn.prepareStatement(sql);
                            int rows = pst.executeUpdate();
                        } catch (SQLException ex) {
                            Logger.getLogger(JTable.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR" + ex);
                        }
                        cargarara();
                        String hi = null;
                        int row = tbpedidos.getSelectedRow();
                        hi = String.valueOf(modelopedidos.getValueAt(row, 21));

                        //cargarara();
                        //String sqlUpdate = "UPDATE pedidosnuevos SET total_orden = ? WHERE id_pedido = ? ";
                    }
                }
            });

            try {
                int row = tbpedidos.getSelectedRow();

                PreparedStatement ps2 = cn.prepareStatement("UPDATE pedidosnuevos SET total_orden='" + modelopedidos.getValueAt(row, 21) + "' WHERE id_pedido='" + modelopedidos.getValueAt(row, 0) + "'");
                ps2.executeUpdate();
                //JOptionPane.showInputDialog(modelopedidos.getValueAt(row, 0));
            } catch (Exception i) {
                //JOptionPane.showInputDialog(i);
                //System.out.print(i.getMessage());
            }

            tbpedidos.setCellSelectionEnabled(true);
            tbpedidos.setSurrendersFocusOnKeystroke(true);
            tbpedidos.getInputMap(javax.swing.JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectNextColumnCell");

            tbpedidos.setModel(modelopedidos);

            //sumarcolumnasTabla(modelopedidos);
            
        } catch (SQLException ex) {
            Logger.getLogger(JTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void sumarcolumnasTabla() {
        for (int i = 0; i < tbpedidos.getRowCount(); i++) {
            int enero = Integer.parseInt(tbpedidos.getValueAt(i, 8).toString());
            int febrero = Integer.parseInt(tbpedidos.getValueAt(i, 9).toString());

            int total = enero + febrero;
            String nw = String.valueOf(total);
            //DefaultTableModel m = (DefaultTableModel) tbpedidos.getModel();
            String filla[] = {nw};
            //ob.addColumn(21, filla);
            modelopedidos.setValueAt(total, i, 21);
            tbpedidos.setModel(modelopedidos);

        }

    }

    void mostrardatoscajas(String valor) {
        DefaultTableModel modelo = new DefaultTableModel() {

            //Quitar edición de las tablas
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        modelo.addColumn("Id");
        modelo.addColumn("Marca");
        modelo.addColumn("Vitola");
        modelo.addColumn("Nombre");
        modelo.addColumn("Capa");
        modelo.addColumn("Tipo de Empaque");
        modelo.addColumn("Iventario Inicial");
        tb_cajas.setModel(modelo);

        modelo = new DefaultTableModel(DataBase.cargarcajas(), columnas_caja);
        tb_cajas.setModel(modelo);
        //NO MOVER LAS COLUMNAS DE LA TABLA
        tb_cajas.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cajas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_marca_pedidos = new javax.swing.JTextField();
        txt_nombre_pedidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_capa_pedidos = new javax.swing.JTextField();
        txt_empaque_pedidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_inventarioinicial_pedidos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_id_pedidos = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbpedidos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txt_buscar_pedidos = new javax.swing.JTextField();
        btn_cancelar_pedido = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar_cajas1 = new javax.swing.JTextField();
        txt_id_res = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_vitola_pedidos1 = new javax.swing.JTextField();
        btn_añadir = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_observacion = new javax.swing.JTextField();
        btn_actualizar_pedidos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbl_año_pedidos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos");

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro De Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        tb_cajas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Iventario Inicial"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_cajas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_cajasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_cajas);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Marca:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Vitola:");

        txt_marca_pedidos.setEditable(false);

        txt_nombre_pedidos.setEditable(false);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Capa:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tipo de Empaque:");

        txt_capa_pedidos.setEditable(false);

        txt_empaque_pedidos.setEditable(false);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Inventario Inicial:");

        txt_inventarioinicial_pedidos.setEditable(false);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Id:");

        txt_id_pedidos.setEditable(false);
        txt_id_pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_pedidosActionPerformed(evt);
            }
        });

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tbpedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbpedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpedidosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbpedidosMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbpedidosMousePressed(evt);
            }
        });
        tbpedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbpedidosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbpedidosKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tbpedidos);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Buscar:");

        txt_buscar_pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscar_pedidosActionPerformed(evt);
            }
        });
        txt_buscar_pedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscar_pedidosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscar_pedidosKeyTyped(evt);
            }
        });

        btn_cancelar_pedido.setBackground(new java.awt.Color(51, 51, 51));
        btn_cancelar_pedido.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar_pedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btn_cancelar_pedido.setText("Cancelar");
        btn_cancelar_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_pedidoActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Buscar:");

        txtBuscar_cajas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscar_cajas1ActionPerformed(evt);
            }
        });
        txtBuscar_cajas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar_cajas1KeyTyped(evt);
            }
        });

        txt_id_res.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_resActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nombre:");

        txt_vitola_pedidos1.setEditable(false);

        btn_añadir.setBackground(new java.awt.Color(51, 51, 51));
        btn_añadir.setForeground(new java.awt.Color(255, 255, 255));
        btn_añadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/añadir.png"))); // NOI18N
        btn_añadir.setText("Añadir");
        btn_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_añadirActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Observación:");

        txt_observacion.setText("Ninguna");

        btn_actualizar_pedidos.setBackground(new java.awt.Color(51, 51, 51));
        btn_actualizar_pedidos.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualizar_pedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        btn_actualizar_pedidos.setText("Actualizar");
        btn_actualizar_pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_pedidosActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Año:");

        lbl_año_pedidos.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_marca_pedidos)
                                            .addComponent(txt_id_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_nombre_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_vitola_pedidos1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_buscar_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_empaque_pedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_capa_pedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_inventarioinicial_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_año_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_actualizar_pedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_añadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancelar_pedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar_cajas1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_id_res, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar_cajas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_id_res, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_id_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txt_nombre_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_añadir)
                            .addComponent(txt_inventarioinicial_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txt_capa_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_marca_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_vitola_pedidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_actualizar_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txt_buscar_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_cancelar_pedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_empaque_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbl_año_pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_actualizar_pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_pedidosActionPerformed

        //actualizardatos();
        cargarPedidosEjemplo();
        // cargarara();

    }//GEN-LAST:event_btn_actualizar_pedidosActionPerformed

    private void btn_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_añadirActionPerformed
        // TODO add your handling code here:
        if (txt_id_pedidos.getText().isEmpty() || txt_marca_pedidos.getText().isEmpty() || txt_vitola_pedidos1.getText().isEmpty() || txt_nombre_pedidos.getText().isEmpty()
                || txt_capa_pedidos.getText().isEmpty() || txt_empaque_pedidos.getText().isEmpty() || txt_inventarioinicial_pedidos.getText().isEmpty()
                || txt_observacion.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor llenar todos los campos.");
        } else {

            try {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO pedidosNuevos (id_caja,marca_pedido,vitola_pedido,nombre_pedido,capa_pedido,tipo_empaque_pedido,invent_inicial_pedido,enero,"
                        + "febrero,marzo,abril,mayo,junio,julio,agosto,septiembre,octubre,noviembre,diciembre,observacion_orden, total_orden,año_pedidos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                //pst.setString(1, txt_id_pedidos.getText());
                pst.setString(1, txt_id_res.getText());
                pst.setString(2, txt_marca_pedidos.getText());
                pst.setString(3, txt_vitola_pedidos1.getText());
                pst.setString(4, txt_nombre_pedidos.getText());
                pst.setString(5, txt_capa_pedidos.getText());
                pst.setString(6, txt_empaque_pedidos.getText());
                pst.setString(7, txt_inventarioinicial_pedidos.getText());
                pst.setInt(8, 0);
                pst.setInt(9, 0);
                pst.setInt(10, 0);
                pst.setInt(11, 0);
                pst.setInt(12, 0);
                pst.setInt(13, 0);
                pst.setInt(14, 0);
                pst.setInt(15, 0);
                pst.setInt(16, 0);
                pst.setInt(17, 0);
                pst.setInt(18, 0);
                pst.setInt(19, 0);
                pst.setString(20, txt_observacion.getText());
                pst.setInt(21, 0);
                pst.setString(22, lbl_año_pedidos.getText());

                pst.executeUpdate();

                //mostrardatospedidos("");
                cargarPedidosEjemplo();
                modelpedido.fireTableDataChanged();

                //sumarcolumnasTabla();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        limpiarpedido();

    }//GEN-LAST:event_btn_añadirActionPerformed

    private void txt_id_resActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_resActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_id_resActionPerformed

    private void txtBuscar_cajas1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar_cajas1KeyTyped
        // TODO add your handling code here:

        //METODO PARA BUSCAR
        txtBuscar_cajas1.setText(txtBuscar_cajas1.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);
        DefaultTableModel cajas = (DefaultTableModel) tb_cajas.getModel();
        txtBuscar_cajas1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar_cajas1.getText(), 1, 2, 3, 4));
                //trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtbuscar_ver_cajas.getText(), 3));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(cajas);
        tb_cajas.setRowSorter(trs);


    }//GEN-LAST:event_txtBuscar_cajas1KeyTyped

    private void txtBuscar_cajas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar_cajas1ActionPerformed


    }//GEN-LAST:event_txtBuscar_cajas1ActionPerformed

    private void btn_cancelar_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_pedidoActionPerformed
        // TODO add your handling code here:
        limpiarpedido();
        dispose();

    }//GEN-LAST:event_btn_cancelar_pedidoActionPerformed

    private void txt_buscar_pedidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_pedidosKeyTyped
        // TODO add your handling code here:

        txt_buscar_pedidos.setText(txt_buscar_pedidos.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);
        DefaultTableModel m = (DefaultTableModel) tbpedidos.getModel();
        txt_buscar_pedidos.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txt_buscar_pedidos.getText(), 2, 3, 4, 5, 6));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(m);
        tbpedidos.setRowSorter(trs);

    }//GEN-LAST:event_txt_buscar_pedidosKeyTyped

    private void txt_buscar_pedidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_pedidosKeyReleased
        // TODO add your handling code here:
        //rowSorter.setRowFilter(RowFilter.regexFilter(txt_buscar_pedidos.getText().toUpperCase(),IDBUSQUEDA1));
        //rowSorter.setRowFilter(RowFilter.regexFilter(txt_buscar_pedidos.getText().toUpperCase(),IDBUSQUEDA2));
    }//GEN-LAST:event_txt_buscar_pedidosKeyReleased

    private void txt_buscar_pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscar_pedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_pedidosActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void tbpedidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpedidosKeyTyped
        // TODO add your handling code here:
    
    

    
    }//GEN-LAST:event_tbpedidosKeyTyped

    private void tbpedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpedidosKeyPressed
        // TODO add your handling code here:
          int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {

            evt.consume();
            JOptionPane.showMessageDialog(null, "No puede ingresar Letras!!!", "Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    
    }//GEN-LAST:event_tbpedidosKeyPressed

    private void tbpedidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpedidosMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbpedidosMousePressed

    private void tbpedidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpedidosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpedidosMouseEntered

    private void tbpedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpedidosMouseClicked
        // TODO add your handling code here:

        /*if (tbpedidos.getSelectedRow() != -1) {
            String codigo = (String) modelopedidos.getValueAt(tbpedidos.getSelectedRow(), 8);
            lbl_total_orden.setText("");

            int row = tbpedidos.rowAtPoint(evt.getPoint());
            // si uno de los campos de la BD esta vacio, dara un error y no
            // mostrará el registro de la siguientes columnas, poner todos
            // los campos a 0 o null por defecto

            String dato=String.valueOf(modelopedidos.getValueAt(tbpedidos.getSelectedRow(),8));
            lbl_total_orden.setText(tbpedidos.getValueAt(row, 8).toString());

            System.out.println(codigo);
        } else {
            System.out.println("Seleccione un renglon primero");
        }*/

    }//GEN-LAST:event_tbpedidosMouseClicked

    private void txt_id_pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_pedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_pedidosActionPerformed

    private void tb_cajasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cajasMouseClicked
        // TODO add your handling code here:
        //System.out.println("Id:", txt_id_res);
        txt_id_res.setText("");
        txt_marca_pedidos.setText("");
        txt_vitola_pedidos1.setText("");
        txt_nombre_pedidos.setText("");
        txt_capa_pedidos.setText("");
        txt_empaque_pedidos.setText("");
        txt_inventarioinicial_pedidos.setText("");

        int row = tb_cajas.rowAtPoint(evt.getPoint());
        // si uno de los campos de la BD esta vacio, dara un error y no
        // mostrará el registro de la siguientes columnas, poner todos
        // los campos a 0 o null por defecto

        txt_id_res.setText(tb_cajas.getValueAt(row, 0).toString());
        txt_marca_pedidos.setText(tb_cajas.getValueAt(row, 1).toString());
        txt_vitola_pedidos1.setText(tb_cajas.getValueAt(row, 2).toString());
        txt_nombre_pedidos.setText(tb_cajas.getValueAt(row, 3).toString());
        txt_capa_pedidos.setText(tb_cajas.getValueAt(row, 4).toString());
        txt_empaque_pedidos.setText(tb_cajas.getValueAt(row, 5).toString());
        txt_inventarioinicial_pedidos.setText(tb_cajas.getValueAt(row, 6).toString());
    }//GEN-LAST:event_tb_cajasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroDePedidos

.class  


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroDePedidos

.class  


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroDePedidos

.class  


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroDePedidos

.class  


.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroDePedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar_pedidos;
    private javax.swing.JButton btn_añadir;
    private javax.swing.JButton btn_cancelar_pedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_año_pedidos;
    private javax.swing.JTable tb_cajas;
    private javax.swing.JTable tbpedidos;
    private javax.swing.JTextField txtBuscar_cajas1;
    private javax.swing.JTextField txt_buscar_pedidos;
    private javax.swing.JTextField txt_capa_pedidos;
    private javax.swing.JTextField txt_empaque_pedidos;
    private javax.swing.JTextField txt_id_pedidos;
    private javax.swing.JTextField txt_id_res;
    private javax.swing.JTextField txt_inventarioinicial_pedidos;
    private javax.swing.JTextField txt_marca_pedidos;
    private javax.swing.JTextField txt_nombre_pedidos;
    private javax.swing.JTextField txt_observacion;
    private javax.swing.JTextField txt_vitola_pedidos1;
    // End of variables declaration//GEN-END:variables

    public static RegistroDePedidos getInstance() {
        if (myInstance == null) {
            myInstance = new RegistroDePedidos();
        }
        return myInstance;
    }

    private void limpiarpedido() {
        txt_capa_pedidos.setText(null);
        txt_empaque_pedidos.setText(null);
        txt_id_pedidos.setText(null);
        txt_inventarioinicial_pedidos.setText(null);
        txt_marca_pedidos.setText(null);
        txt_nombre_pedidos.setText(null);
        txt_vitola_pedidos1.setText(null);
        //txt_observacion.setText(null);

    }

    //conectar cc= new conectar();
    //Connection cn= cc.conexion();
    private DefaultTableModel modelocaja;

    private final String columnas_caja[] = {"Id", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial"};

    private DefaultTableModel modelpedido;

    private final String columnas_pedido[] = {"Id Pedido", "Id Caja", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Observación", "Total Orden", "Año"};

    public static String añoActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY");

        return formatoFecha.format(fecha);

    }
    

}
