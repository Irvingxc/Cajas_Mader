/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendientedecajas;

import base.DataBase;
import base.conectar;
import com.placeholder.PlaceHolder;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.runtime.Debug.id;


/**
 *
 * @author Admin
 */
public class crearNuevaEntrada extends javax.swing.JFrame {

    //MODELO DE LA TABLA
    DefaultTableModel model = new DefaultTableModel() {

        //NO EDITAR LAS FILAS EN LA TABLA    
        @Override
        public boolean isCellEditable(int row, int column) {

            return false;

        }
    };

    private static crearNuevaEntrada myInstance;
    private String id;

    /**
     * Creates new form crearNuevaEntrada
     */
    //VARIABLE PARA EL BUSCADOOR
    TableRowSorter trs;
    //Variable Place HOLDER
    PlaceHolder holder;

    //SUMA DE COLUMNAS
    public static void sumaRem() {
        int suma = 0;
        int suma2 = 0;

        for (int i = 0; i < jTablenuevaremisionEntrada.getRowCount(); i++) {

            int renglon;
            int invent;
            renglon = Integer.parseInt(jTablenuevaremisionEntrada.getValueAt(i, 9).toString());
            invent = Integer.parseInt(txtinventarioinicialcaja.getText());

            
            suma = suma + renglon;
            suma2 = suma + invent;

        }

        txttotalremisionEntrada.setText(String.valueOf(suma));
        txttotalentrada.setText(String.valueOf(suma2));
    }
    
    
    private void sumaEntrada() {

        String invent, rem;
        int entero1, entero2, total;

        invent = txtinventarioinicialcaja.getText();
        rem = txttotalremisionEntrada.getText();

        entero1 = Integer.parseInt(invent);
        entero2 = Integer.parseInt(rem);

        total = entero1 + entero2;

        txttotalentrada.setText("" + total);
        txttotalentrada.setText(String.valueOf(total));
    }

    public crearNuevaEntrada() {
        initComponents();
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Imagenes\\icono2.png"));
        mostrardatoscajas("");
        lblidcajasentrada.setVisible(false);

        //MOSTRAR ID
        txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones()); 
        txtidnuevaentrada.setText(DataBase.extraerIdMaxEntradas());
        jformfechanuevaremisionEntrada.setText(fechaActual());

        holder = new PlaceHolder(txtbuscar_entradas, "Marca, Vitola, Nombre, Capa");

        //PARA AÑADIR EL FOCUS A OTRO COMPONENTE
        //btnregistrarremision.requestFocus();
    }

    //TRAER DATOS DE OTRA TABLA  
    void mostrardatoscajas(String valor) {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Marca");
        modelo.addColumn("Vitola");
        modelo.addColumn("Nombre");
        modelo.addColumn("Capa");
        modelo.addColumn("Tipo de Empaque");
        modelo.addColumn("Inventario Inicial");
        jtablecajas.setModel(modelo);

        //NO MOVER LAS COLUMNAS DE LA TABLA
        jtablecajas.getTableHeader().setReorderingAllowed(false);

        modelo = new DefaultTableModel(DataBase.cargarcajas(), columnas_caja) {

            //NO EDITAR LAS FILAS EN LA TABLA    
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }
        };

        jtablecajas.setModel(modelo);

    }

    public void cargarRemisiones() {

        model = new DefaultTableModel(DataBase.cargarRemisionById(id), columnas_remision);

        jTablenuevaremisionEntrada.setModel(model);

        jTablenuevaremisionEntrada.getColumnModel().getColumn(0);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(1);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(2);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(3);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(4);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(5);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(6);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(7);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(8);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(9);
        jTablenuevaremisionEntrada.getColumnModel().getColumn(10);

        jTablenuevaremisionEntrada.setModel(model);

    }
    
    void mostrardatos(String valor) {
        DefaultTableModel modeloEntrada = new DefaultTableModel() {

            //Quitar edición de las tablas
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloEntrada.addColumn("ID Caja");
        modeloEntrada.addColumn("Marca");
        modeloEntrada.addColumn("Vitola");
        modeloEntrada.addColumn("Nombre");
        modeloEntrada.addColumn("Capa");
        modeloEntrada.addColumn("Tipo de Empaque");
        modeloEntrada.addColumn("ID Remision");
        modeloEntrada.addColumn("Num. Remisión");
        modeloEntrada.addColumn("Fecha");
        modeloEntrada.addColumn("Cantidad");
        modeloEntrada.addColumn("Precio Unitario");

        jTablenuevaremisionEntrada.setModel(modeloEntrada);
        String sql = "";
        if (valor.equals("")) {
            sql = "select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n" +
                    "rem.id_remision, rem.num_remision, rem.fecha_remision, rem.cant_remision, rem.precio_unitario\n" +
                    "from registrocajas as caj join remisiones as rem\n" +
                    "on caj.id_caja = rem.id_caja where caj.id_caja=0";
        } else {
            sql = "select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n" +
                    "rem.id_remision, rem.num_remision, rem.fecha_remision, rem.cant_remision, rem.precio_unitario\n" +
                    "from registrocajas as caj join remisiones as rem\n" +
                    "on caj.id_caja = rem.id_caja where caj.id_caja= '" + valor + "'";

        }

        String[] datos = new String[11];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);

                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                modeloEntrada.addRow(datos);
            }
            jTablenuevaremisionEntrada.setModel(modeloEntrada);
        } catch (SQLException ex) {

        }

    }

    //FECHA ACTUAL
    public static String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");

        return formatoFecha.format(fecha);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbuscar_entradas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablecajas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmarcacaja = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtvitolacaja = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnombrecaja = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcapacaja = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttipoempaquecaja = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtinventarioinicialcaja = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttotalentrada = new javax.swing.JTextField();
        btncancelarnuevaentrada = new javax.swing.JButton();
        btnguardarnuevaentrada = new javax.swing.JButton();
        lblidcajasentrada = new javax.swing.JLabel();
        txtidnuevaentrada = new javax.swing.JTextField();
        btnactualizarentrada = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtnumremisonEntrada = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jformfechanuevaremisionEntrada = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        txtcantidadremisionEntrada = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtpreciounitarioEntrada = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablenuevaremisionEntrada = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txttotalremisionEntrada = new javax.swing.JTextField();
        btnañadirnuevaremision = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtidremisionEntrada = new javax.swing.JTextField();

        jMenuItem1.setText("Modificar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crear Entradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscar:");

        txtbuscar_entradas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscar_entradasKeyTyped(evt);
            }
        });

        jtablecajas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtablecajas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtablecajasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtablecajas);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Marca:");

        txtmarcacaja.setEditable(false);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Vitola:");

        txtvitolacaja.setEditable(false);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nombre:");

        txtnombrecaja.setEditable(false);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Capa:");

        txtcapacaja.setEditable(false);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tipo Empaque:");

        txttipoempaquecaja.setEditable(false);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Inventario Inicial:");

        txtinventarioinicialcaja.setEditable(false);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Total Entradas:");

        txttotalentrada.setEditable(false);
        txttotalentrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttotalentradaKeyReleased(evt);
            }
        });

        btncancelarnuevaentrada.setBackground(new java.awt.Color(51, 51, 51));
        btncancelarnuevaentrada.setForeground(new java.awt.Color(153, 153, 153));
        btncancelarnuevaentrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btncancelarnuevaentrada.setText("Cancelar");
        btncancelarnuevaentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarnuevaentradaActionPerformed(evt);
            }
        });

        btnguardarnuevaentrada.setBackground(new java.awt.Color(51, 51, 51));
        btnguardarnuevaentrada.setForeground(new java.awt.Color(153, 153, 153));
        btnguardarnuevaentrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnguardarnuevaentrada.setText("Guardar");
        btnguardarnuevaentrada.setEnabled(false);
        btnguardarnuevaentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarnuevaentradaActionPerformed(evt);
            }
        });

        lblidcajasentrada.setText("jLabel11");

        txtidnuevaentrada.setEditable(false);
        txtidnuevaentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidnuevaentradaActionPerformed(evt);
            }
        });

        btnactualizarentrada.setBackground(new java.awt.Color(51, 51, 51));
        btnactualizarentrada.setForeground(new java.awt.Color(153, 153, 153));
        btnactualizarentrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        btnactualizarentrada.setText("Actualizar");
        btnactualizarentrada.setEnabled(false);
        btnactualizarentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarentradaActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Num. Remisión:");

        txtnumremisonEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumremisonEntradaKeyTyped(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Fecha:");

        jformfechanuevaremisionEntrada.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            jformfechanuevaremisionEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jformfechanuevaremisionEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jformfechanuevaremisionEntradaKeyTyped(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Cantidad:");

        txtcantidadremisionEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadremisionEntradaKeyTyped(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Precio Unitario:");

        txtpreciounitarioEntrada.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtpreciounitarioEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpreciounitarioEntradaKeyTyped(evt);
            }
        });

        jTablenuevaremisionEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablenuevaremisionEntrada.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(jTablenuevaremisionEntrada);

        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Total Remisiones:");

        txttotalremisionEntrada.setEditable(false);

        btnañadirnuevaremision.setBackground(new java.awt.Color(51, 51, 51));
        btnañadirnuevaremision.setForeground(new java.awt.Color(255, 255, 255));
        btnañadirnuevaremision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir-lista.png"))); // NOI18N
        btnañadirnuevaremision.setText("Añadir");
        btnañadirnuevaremision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnañadirnuevaremisionActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("ID:");

        txtidremisionEntrada.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtmarcacaja, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtvitolacaja, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnombrecaja, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcapacaja, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttipoempaquecaja, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnumremisonEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jformfechanuevaremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcantidadremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtpreciounitarioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(btnañadirnuevaremision)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtinventarioinicialcaja, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtbuscar_entradas, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(lblidcajasentrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidnuevaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttotalremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttotalentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnguardarnuevaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnactualizarentrada)
                                        .addGap(18, 18, 18)
                                        .addComponent(btncancelarnuevaentrada)
                                        .addGap(37, 37, 37)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar_entradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblidcajasentrada)
                    .addComponent(jLabel2)
                    .addComponent(txtidnuevaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmarcacaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtvitolacaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtnombrecaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtcapacaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txttipoempaquecaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtinventarioinicialcaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtnumremisonEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(txtcantidadremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jformfechanuevaremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtpreciounitarioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtidremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnañadirnuevaremision))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotalremisionEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotalentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelarnuevaentrada)
                    .addComponent(btnguardarnuevaentrada)
                    .addComponent(btnactualizarentrada))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, -4, 1290, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //EVENTO PARA BOTON CANCELAR
    private void btncancelarnuevaentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarnuevaentradaActionPerformed

        dispose();

    }//GEN-LAST:event_btncancelarnuevaentradaActionPerformed

    private void jtablecajasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablecajasMouseClicked
        lblidcajasentrada.setText("");
        txtmarcacaja.setText("");
        txtvitolacaja.setText("");
        txtnombrecaja.setText("");
        txtcapacaja.setText("");
        txttipoempaquecaja.setText("");
        txtinventarioinicialcaja.setText("");

        int row = jtablecajas.rowAtPoint(evt.getPoint());

        lblidcajasentrada.setText(jtablecajas.getValueAt(row, 0).toString());
        txtmarcacaja.setText(jtablecajas.getValueAt(row, 1).toString());
        txtvitolacaja.setText(jtablecajas.getValueAt(row, 2).toString());
        txtnombrecaja.setText(jtablecajas.getValueAt(row, 3).toString());
        txtcapacaja.setText(jtablecajas.getValueAt(row, 4).toString());
        txttipoempaquecaja.setText(jtablecajas.getValueAt(row, 5).toString());
        txtinventarioinicialcaja.setText(jtablecajas.getValueAt(row, 6).toString());
        
        //MOSTRAR ID
        txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones()); 
        
        mostrardatos(jtablecajas.getValueAt(row, 0).toString());
        sumaRem();
        
    }//GEN-LAST:event_jtablecajasMouseClicked

    private void btnguardarnuevaentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarnuevaentradaActionPerformed

        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO entradas(id_caja,total_remision,total_entradas) VALUES (?,?,?)");
            pst.setString(1, lblidcajasentrada.getText());
            pst.setString(2, txttotalremisionEntrada.getText());
            pst.setString(3, txttotalentrada.getText());

            pst.executeUpdate();
            //METODO PARA LIMPIAR CAMPOS

            // Mensaje de exito
            JOptionPane.showMessageDialog(null, "Se guardó exitosamente");
            //MOSTRAR ID
            txtidnuevaentrada.setText(DataBase.extraerIdMaxEntradas());

            //LIMPIAR CAMPOS AL GUARDAR
            txtmarcacaja.setText("");
            txtvitolacaja.setText("");
            txtnombrecaja.setText("");
            txtcapacaja.setText("");
            txttipoempaquecaja.setText("");
            txtinventarioinicialcaja.setText("");
            txtnumremisonEntrada.setText("");
            txtcantidadremisionEntrada.setText("");
            txtpreciounitarioEntrada.setText("");
            txttotalentrada.setText("");

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        txtidnuevaentrada.setText(DataBase.extraerIdMaxEntradas());
        this.btnguardarnuevaentrada.setEnabled(false);
    }//GEN-LAST:event_btnguardarnuevaentradaActionPerformed

    private void btnactualizarentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarentradaActionPerformed

        DataBase.ActualizarEntradas(lblidcajasentrada.getText(), txttotalremisionEntrada.getText(), txttotalentrada.getText());

        JOptionPane.showMessageDialog(null, "Se editó exitosamente");

        //LIMPIAR CAMPOS AL AÑADIR
        txtmarcacaja.setText("");
        txtvitolacaja.setText("");
        txtnombrecaja.setText("");
        txtcapacaja.setText("");
        txttipoempaquecaja.setText("");
        txtinventarioinicialcaja.setText("");
        txtinventarioinicialcaja.setText("");
        txtnumremisonEntrada.setText("");
        txtcantidadremisionEntrada.setText("");
        txtpreciounitarioEntrada.setText("");
        txttotalentrada.setText("");
        txttotalremisionEntrada.setText("");
        txttotalentrada.setText("");

        //MOSTRAR ID
        txtidnuevaentrada.setText(DataBase.extraerIdMaxEntradas());
        
        this.btnactualizarentrada.setEnabled(false);

    }//GEN-LAST:event_btnactualizarentradaActionPerformed

    private void txtidnuevaentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidnuevaentradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidnuevaentradaActionPerformed

    private void txtbuscar_entradasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_entradasKeyTyped
        // TODO add your handling code here:

        //METODO PARA BUSCAR
        txtbuscar_entradas.setText(txtbuscar_entradas.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);        
        DefaultTableModel cajas = (DefaultTableModel) jtablecajas.getModel();
        txtbuscar_entradas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtbuscar_entradas.getText(), 1, 2, 3, 4));
                //trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtbuscar_ver_cajas.getText(), 3));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(cajas);
        jtablecajas.setRowSorter(trs);

    }//GEN-LAST:event_txtbuscar_entradasKeyTyped

    private void txtnumremisonEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumremisonEntradaKeyTyped

        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtnumremisonEntradaKeyTyped

    private void jformfechanuevaremisionEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jformfechanuevaremisionEntradaKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_jformfechanuevaremisionEntradaKeyTyped

    private void txtcantidadremisionEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadremisionEntradaKeyTyped

        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();
    }//GEN-LAST:event_txtcantidadremisionEntradaKeyTyped

    private void txtpreciounitarioEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciounitarioEntradaKeyTyped

        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }

        if (c == '.' && txtpreciounitarioEntrada.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtpreciounitarioEntradaKeyTyped

    private void btnañadirnuevaremisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnañadirnuevaremisionActionPerformed

        if (txtmarcacaja.getText().isEmpty() || txtvitolacaja.getText().isEmpty() || txtnombrecaja.getText().isEmpty()
                || txtcapacaja.getText().isEmpty() || txttipoempaquecaja.getText().isEmpty() || txtinventarioinicialcaja.getText().isEmpty()
                || txtnumremisonEntrada.getText().isEmpty() || jformfechanuevaremisionEntrada.getText().isEmpty()
                || txtcantidadremisionEntrada.getText().isEmpty() || txtpreciounitarioEntrada.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor llenar todos los campos.");

        } else {

            if (DataBase.validarID(txtidremisionEntrada.getText()) == 0) {
                String[] agregar = new String[11];
                agregar[0] = lblidcajasentrada.getText();
                agregar[1] = txtmarcacaja.getText();
                agregar[2] = txtvitolacaja.getText();
                agregar[3] = txtnombrecaja.getText();
                agregar[4] = txtcapacaja.getText();
                agregar[5] = txttipoempaquecaja.getText();
                agregar[6] = txtidremisionEntrada.getText();
                agregar[7] = txtnumremisonEntrada.getText();
                agregar[8] = jformfechanuevaremisionEntrada.getText();
                agregar[9] = txtcantidadremisionEntrada.getText();
                agregar[10] = txtpreciounitarioEntrada.getText();

                model.addRow(agregar);

                //MOSTRAR ID
                txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones());

                try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO remisiones(id_caja,num_remision,"
                            + "fecha_remision,cant_remision, precio_unitario) VALUES (?,?,?,?,?)");

                    pst.setString(1, lblidcajasentrada.getText());
                    pst.setString(2, txtnumremisonEntrada.getText());
                    pst.setString(3, jformfechanuevaremisionEntrada.getText());
                    pst.setString(4, txtcantidadremisionEntrada.getText());
                    pst.setString(5, txtpreciounitarioEntrada.getText());

                    pst.executeUpdate();

                    this.btnguardarnuevaentrada.setEnabled(true);
                    this.btnactualizarentrada.setEnabled(false);

                    //MOSTRAR ID
                    txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones());

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

                //LIMPIAR CAMPOS AL AÑADIR
                txtnumremisonEntrada.setText("");
                txtcantidadremisionEntrada.setText("");
                txtpreciounitarioEntrada.setText("");

                //MOSTRAR ID
                txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones());
                
                mostrardatos("");

            } else {

                DataBase.ActualizarRemisiones(txtidremisionEntrada.getText(), lblidcajasentrada.getText(), txtnumremisonEntrada.getText(),
                        jformfechanuevaremisionEntrada.getText(), txtcantidadremisionEntrada.getText(), txtpreciounitarioEntrada.getText());

                mostrardatos("");
                
                JOptionPane.showMessageDialog(null, "Se editó exitosamente");
                this.btnactualizarentrada.setEnabled(true);
                this.btnguardarnuevaentrada.setEnabled(false);

                //LIMPIAR CAMPOS AL AÑADIR
                txtnumremisonEntrada.setText("");
                txtcantidadremisionEntrada.setText("");
                txtpreciounitarioEntrada.setText("");

                //MOSTRAR ID
                txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones());

            }

            jformfechanuevaremisionEntrada.setText(fechaActual());

            //MOSTRAR ID
            txtidremisionEntrada.setText(DataBase.extraerIdMaxRemisiones());

            //DataBase.setListarRemisiones(id);

        }

        jformfechanuevaremisionEntrada.setText(fechaActual());
        mostrardatos(lblidcajasentrada.getText());
        sumaRem();
        
    }//GEN-LAST:event_btnañadirnuevaremisionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int fila= jTablenuevaremisionEntrada.getSelectedRow();
        
    if(fila>=0){
        
        lblidcajasentrada.setText(jTablenuevaremisionEntrada.getValueAt(fila, 0).toString());
        txtmarcacaja.setText(jTablenuevaremisionEntrada.getValueAt(fila, 1).toString());
        txtvitolacaja.setText(jTablenuevaremisionEntrada.getValueAt(fila, 2).toString());
        txtnombrecaja.setText(jTablenuevaremisionEntrada.getValueAt(fila, 3).toString());
        txtcapacaja.setText(jTablenuevaremisionEntrada.getValueAt(fila, 4).toString());
        txttipoempaquecaja.setText(jTablenuevaremisionEntrada.getValueAt(fila, 5).toString());
        txtidremisionEntrada.setText(jTablenuevaremisionEntrada.getValueAt(fila, 6).toString());
        txtnumremisonEntrada.setText(jTablenuevaremisionEntrada.getValueAt(fila, 7).toString());
        jformfechanuevaremisionEntrada.setText(jTablenuevaremisionEntrada.getValueAt(fila, 8).toString());
        txtcantidadremisionEntrada.setText(jTablenuevaremisionEntrada.getValueAt(fila, 9).toString());
        txtpreciounitarioEntrada.setText(jTablenuevaremisionEntrada.getValueAt(fila, 10).toString());
        
    }
  
     else{
    JOptionPane.showMessageDialog(null,"No seleciono fila");
    
       }
    
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txttotalentradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalentradaKeyReleased
        sumaEntrada();
    }//GEN-LAST:event_txttotalentradaKeyReleased

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
            java.util.logging.Logger.getLogger(crearNuevaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crearNuevaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crearNuevaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crearNuevaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crearNuevaEntrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizarentrada;
    private javax.swing.JButton btnañadirnuevaremision;
    private javax.swing.JButton btncancelarnuevaentrada;
    private javax.swing.JButton btnguardarnuevaentrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTablenuevaremisionEntrada;
    private javax.swing.JFormattedTextField jformfechanuevaremisionEntrada;
    private javax.swing.JTable jtablecajas;
    private javax.swing.JLabel lblidcajasentrada;
    private javax.swing.JTextField txtbuscar_entradas;
    private javax.swing.JTextField txtcantidadremisionEntrada;
    private javax.swing.JTextField txtcapacaja;
    public static javax.swing.JTextField txtidnuevaentrada;
    public static javax.swing.JTextField txtidremisionEntrada;
    public static javax.swing.JTextField txtinventarioinicialcaja;
    private javax.swing.JTextField txtmarcacaja;
    private javax.swing.JTextField txtnombrecaja;
    private javax.swing.JTextField txtnumremisonEntrada;
    private javax.swing.JTextField txtpreciounitarioEntrada;
    private javax.swing.JTextField txttipoempaquecaja;
    public static javax.swing.JTextField txttotalentrada;
    public static javax.swing.JTextField txttotalremisionEntrada;
    private javax.swing.JTextField txtvitolacaja;
    // End of variables declaration//GEN-END:variables

    //MODELO PARA TABLA DE OTRO FRAME
    private DefaultTableModel modelocaja;
    private final String[] columnas_caja = {"ID", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventrio Inicial"};

    //MODELO PARA TABLA DE OTRO FRAME
    private final String[] columnas_remision = {"ID Caja", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "ID Remision",
        "Num. de remision", "Fecha", "Cantidad", "Precio Unitario"};

    private void limpiar() {
        // Metodo limpiar
        //txtidentrada =;
        txtmarcacaja.setText("");
        txtvitolacaja.setText("");
        txtnombrecaja.setText("");
        txtcapacaja.setText("");
        txttipoempaquecaja.setText("");
        txtinventarioinicialcaja.setText("");
        txttotalentrada.setText("");
    }

    public String getIdCajaSeleccionada() {
        int r = jtablecajas.getSelectedRow();
        String id = null;
        if (r >= 0) {
            id = jtablecajas.getValueAt(r, 0).toString();

        }
        return id;
    }

    //Metodo para que solo se abra una sola pantalla.
    public static crearNuevaEntrada getInstance() {
        if (myInstance == null) {
            myInstance = new crearNuevaEntrada();
        }
        return myInstance;
    }

    conectar cc = new conectar();
    Connection cn = cc.conexion();

}
