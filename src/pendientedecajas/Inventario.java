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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Elssy
 */
public class Inventario extends javax.swing.JFrame {

    /**
     * Creates new form Inventario
     *
     */
    final static String URL = "jdbc:mysql://localhost:3306/pCajasMadera?useSSL=true";
    final static String USUARIO = "root";
    final static String CLAVE = "";

    //Variable de instancia   
    conectar cc = new conectar();
    Connection cn = cc.conexion();
    DefaultTableModel modelopedidos;
    TableRowSorter trs;
    private static Inventario myInstance;
    static JTable tb_table = new JTable() {

        //Quitar edición de las tablas
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
     //Variable Place HOLDER
    PlaceHolder holder;
    


    private void suma() {

        String entradas, salidas;
        int entero1, entero2, total;

        entradas = txt_total_entradas_inve.getText();
        salidas = txt_total_salidas_inv.getText();

        entero1 = Integer.parseInt(entradas);
        entero2 = Integer.parseInt(salidas);

        total = entero1 - entero2;

        txt_inventario_total.setText("" + total);
        txt_inventario_total.setText(String.valueOf(total));
    }

    public Inventario() {
        initComponents();
        setLocationRelativeTo(null);
        mostrardatos("");
        lbl_id_caja.setVisible(false);
        txt_id_inventario.setText(DataBase.extraerIdMaxInventario());
        txt_total_orden.setVisible(false);
        
        holder = new PlaceHolder(txtBuscar_inventario, "Marca, Vitola, Nombre, Capa");
        //PARA AÑADIR EL FOCUS A OTRO COMPONENTE
        btn_guardar_inventario.requestFocus();
        
        lblFecha_inventario.setText(fechaActual());

    }

    void mostrardatos(String valor) {
        DefaultTableModel modeloVerEntrada = new DefaultTableModel() {

            //NO EDITAR LAS FILAS EN LA TABLA    
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }
        };

        modeloVerEntrada.addColumn("ID Caja");
        modeloVerEntrada.addColumn("Marca");
        modeloVerEntrada.addColumn("Vitola");
        modeloVerEntrada.addColumn("Nombre");
        modeloVerEntrada.addColumn("Capa");
        modeloVerEntrada.addColumn("Tipo de Empaque");
        modeloVerEntrada.addColumn("Inventario Inicial");
        modeloVerEntrada.addColumn("Total Orden");
        modeloVerEntrada.addColumn("Total Entradas");
        modeloVerEntrada.addColumn("Total Salidas");

        tb_tablas.setModel(modeloVerEntrada);

        //NO MOVER LAS COLUMNAS DE LA TABLA
        tb_tablas.getTableHeader().setReorderingAllowed(false);

        String sql = "";
        if (valor.equals("")) {
            sql = "select caja.id_caja, caja.marca, caja.vitola, caja.nombre,"
                    + "		caja.capa, caja.tipo_empaque, caja.invent_inicial, ped.total_orden,"
                    + "        en.total_entradas, sal.total_salidas "
                    + "from registrocajas as caja "
                    + "join pedidosnuevos as ped on caja.id_caja = ped.id_caja "
                    + "join entradas as en on caja.id_caja = en.id_caja "
                    + "join salidastotales as sal on caja.id_caja = sal.id_caja "
                    + "where caja.id_caja = caja.id_caja";
        } else {
            return;
        }

        String[] datos = new String[10];
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

                modeloVerEntrada.addRow(datos);
            }
            tb_tablas.setModel(modeloVerEntrada);
            
            // PARA REFRESCAR LA TABLA -- ESTA EN PRUEBA
            DefaultTableModel tableModel = (DefaultTableModel) tb_tablas.getModel();
            tb_tablas.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(VerEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tablas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar_inventario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_id_inventario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_marca_inventario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nombre_inventario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_vitola_inventario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_capa_inventario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_tipo_inventario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_inventario_incial_inv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_total_entradas_inve = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_total_salidas_inv = new javax.swing.JTextField();
        txt_inventario_total = new javax.swing.JTextField();
        btn_guardar_inventario = new javax.swing.JButton();
        btn_cancelar_inventario = new javax.swing.JButton();
        txt_ver_inventario = new javax.swing.JButton();
        lbl_id_caja = new javax.swing.JLabel();
        txt_total_orden = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblFecha_inventario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        tb_tablas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_tablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_tablasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_tablas);

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Buscar:");

        txtBuscar_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar_inventarioKeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Id:");

        txt_id_inventario.setEditable(false);

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Marca:");

        txt_marca_inventario.setEditable(false);

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Nombre:");

        txt_nombre_inventario.setEditable(false);

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Vitola:");

        txt_vitola_inventario.setEditable(false);

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Capa:");

        txt_capa_inventario.setEditable(false);

        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Tipo de Empaque:");

        txt_tipo_inventario.setEditable(false);

        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Inventario Inicial:");

        txt_inventario_incial_inv.setEditable(false);

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Total Entradas:");

        txt_total_entradas_inve.setEditable(false);

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Total Salidas:");

        txt_total_salidas_inv.setEditable(false);

        txt_inventario_total.setEditable(false);
        txt_inventario_total.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_inventario_totalInputMethodTextChanged(evt);
            }
        });
        txt_inventario_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inventario_totalActionPerformed(evt);
            }
        });
        txt_inventario_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_inventario_totalKeyReleased(evt);
            }
        });

        btn_guardar_inventario.setBackground(new java.awt.Color(51, 51, 51));
        btn_guardar_inventario.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btn_guardar_inventario.setText("Guardar");
        btn_guardar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_inventarioActionPerformed(evt);
            }
        });

        btn_cancelar_inventario.setBackground(new java.awt.Color(51, 51, 51));
        btn_cancelar_inventario.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btn_cancelar_inventario.setText("Cancelar");
        btn_cancelar_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_inventarioActionPerformed(evt);
            }
        });

        txt_ver_inventario.setBackground(new java.awt.Color(51, 51, 51));
        txt_ver_inventario.setForeground(new java.awt.Color(255, 255, 255));
        txt_ver_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-correcta.png"))); // NOI18N
        txt_ver_inventario.setText("Mostrar Registro");
        txt_ver_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ver_inventarioActionPerformed(evt);
            }
        });

        txt_total_orden.setEditable(false);

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Total Inventario:");

        lblFecha_inventario.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFecha_inventario.setForeground(new java.awt.Color(51, 51, 51));
        lblFecha_inventario.setText("Fecha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_id_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(143, 143, 143)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_nombre_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel6)
                                        .addGap(78, 78, 78)
                                        .addComponent(txt_capa_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(6, 6, 6)
                                        .addComponent(txt_marca_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel5)
                                        .addGap(31, 31, 31)
                                        .addComponent(txt_vitola_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel7)
                                        .addGap(12, 12, 12)
                                        .addComponent(txt_tipo_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_total_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_inventario_incial_inv, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(27, 27, 27)
                                .addComponent(txt_total_salidas_inv, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txt_total_entradas_inve, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32)
                                .addComponent(txt_inventario_total, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btn_guardar_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btn_cancelar_inventario)
                                .addGap(6, 6, 6)
                                .addComponent(txt_ver_inventario))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(txtBuscar_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(442, 442, 442)
                        .addComponent(lblFecha_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_id_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lbl_id_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFecha_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_capa_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txt_inventario_incial_inv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_marca_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_vitola_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tipo_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)))
                    .addComponent(txt_total_orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txt_total_entradas_inve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_inventario_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txt_total_salidas_inv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btn_guardar_inventario)
                    .addComponent(btn_cancelar_inventario)
                    .addComponent(txt_ver_inventario))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1259, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_inventarioActionPerformed
        // TODO add your handling code here:
         if(txt_marca_inventario.getText().isEmpty() || txt_vitola_inventario.getText().isEmpty() || txt_nombre_inventario.getText().isEmpty()
                || txt_capa_inventario.getText().isEmpty() || txt_tipo_inventario.getText().isEmpty() || txt_inventario_incial_inv.getText().isEmpty()
         ){
           
            JOptionPane.showMessageDialog(this, "Por favor llenar todos los campos.");
        }else{
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO inventario (id_caja,marca_inventario,vitola_inventario,nombre_inventario,capa_inventario,tipo_empaque_inventario,invent_inicial_inventario,total_entradas_inventario,total_salidas_inventario,total_orden_inventario,total_invent,fecha_inventario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            //pst.setString(1, txt_id_registro.getText());
            pst.setString(1, lbl_id_caja.getText());
            pst.setString(2, txt_marca_inventario.getText());
            pst.setString(3, txt_vitola_inventario.getText());
            pst.setString(4, txt_nombre_inventario.getText());
            pst.setString(5, txt_capa_inventario.getText());
            pst.setString(6, txt_tipo_inventario.getText());
            pst.setString(7, txt_inventario_incial_inv.getText());
            pst.setString(8, txt_total_entradas_inve.getText());
            pst.setString(9, txt_total_salidas_inv.getText());
            pst.setString(10, txt_total_orden.getText());
            pst.setString(11, txt_inventario_total.getText());
            pst.setString(12, lblFecha_inventario.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se guardó exitosamente");
            limpiarInventario();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            txt_id_inventario.setText(DataBase.extraerIdMaxInventario());
        }
         }

    }//GEN-LAST:event_btn_guardar_inventarioActionPerformed

    private void tb_tablasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_tablasMouseClicked
        // TODO add your handling code here:
        lbl_id_caja.setText("");
        //txt_id_inventario.setText("");
        txt_marca_inventario.setText("");
        txt_vitola_inventario.setText("");
        txt_nombre_inventario.setText("");
        txt_capa_inventario.setText("");
        txt_tipo_inventario.setText("");
        txt_inventario_incial_inv.setText("");
        txt_total_orden.setText("");
        txt_total_entradas_inve.setText("");
        txt_total_salidas_inv.setText("");

        int row = tb_tablas.rowAtPoint(evt.getPoint());
        // si uno de los campos de la BD esta vacio, dara un error y no
        // mostrará el registro de la siguientes columnas, poner todos
        // los campos a 0 o null por defecto

        lbl_id_caja.setText(tb_tablas.getValueAt(row, 0).toString());
        txt_marca_inventario.setText(tb_tablas.getValueAt(row, 1).toString());
        txt_vitola_inventario.setText(tb_tablas.getValueAt(row, 2).toString());
        txt_nombre_inventario.setText(tb_tablas.getValueAt(row, 3).toString());
        txt_capa_inventario.setText(tb_tablas.getValueAt(row, 4).toString());
        txt_tipo_inventario.setText(tb_tablas.getValueAt(row, 5).toString());
        txt_inventario_incial_inv.setText(tb_tablas.getValueAt(row, 6).toString());
        txt_total_orden.setText(tb_tablas.getValueAt(row, 7).toString());
        txt_total_entradas_inve.setText(tb_tablas.getValueAt(row, 8).toString());
        txt_total_salidas_inv.setText(tb_tablas.getValueAt(row, 9).toString());
        
        int one= Integer.parseInt(tb_tablas.getValueAt(row, 8).toString());
        int two= Integer.parseInt(tb_tablas.getValueAt(row, 9).toString());
        int resta = one-two;
        
        txt_inventario_total.setText(""+resta);
    }//GEN-LAST:event_tb_tablasMouseClicked

    private void btn_cancelar_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_inventarioActionPerformed
        // TODO add your handling code here:
        limpiarInventario();
        dispose();
    }//GEN-LAST:event_btn_cancelar_inventarioActionPerformed

    private void txt_ver_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ver_inventarioActionPerformed
        // TODO add your handling code here:
        VerInventarioTotal ver_invet = VerInventarioTotal.getInstance();
        ver_invet.setVisible(true);
       

       
    }//GEN-LAST:event_txt_ver_inventarioActionPerformed

    private void txt_inventario_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inventario_totalActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_inventario_totalActionPerformed

    private void txt_inventario_totalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_inventario_totalKeyReleased
        // TODO add your handling code here:
        suma();
    }//GEN-LAST:event_txt_inventario_totalKeyReleased

    private void txt_inventario_totalInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_inventario_totalInputMethodTextChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_inventario_totalInputMethodTextChanged

    private void txtBuscar_inventarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar_inventarioKeyTyped
        
          //METODO PARA BUSCAR
        txtBuscar_inventario.setText(txtBuscar_inventario.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);
        DefaultTableModel inventario = (DefaultTableModel) tb_tablas.getModel();
        txtBuscar_inventario.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar_inventario.getText(), 1, 2, 3, 4));
                //trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtbuscar_ver_cajas.getText(), 3));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(inventario);
        tb_tablas.setRowSorter(trs);
        
    }//GEN-LAST:event_txtBuscar_inventarioKeyTyped

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar_inventario;
    private javax.swing.JButton btn_guardar_inventario;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JLabel lblFecha_inventario;
    private javax.swing.JLabel lbl_id_caja;
    private javax.swing.JTable tb_tablas;
    private javax.swing.JTextField txtBuscar_inventario;
    private javax.swing.JTextField txt_capa_inventario;
    private javax.swing.JTextField txt_id_inventario;
    private javax.swing.JTextField txt_inventario_incial_inv;
    private javax.swing.JTextField txt_inventario_total;
    private javax.swing.JTextField txt_marca_inventario;
    private javax.swing.JTextField txt_nombre_inventario;
    private javax.swing.JTextField txt_tipo_inventario;
    private javax.swing.JTextField txt_total_entradas_inve;
    private javax.swing.JTextField txt_total_orden;
    private javax.swing.JTextField txt_total_salidas_inv;
    private javax.swing.JButton txt_ver_inventario;
    private javax.swing.JTextField txt_vitola_inventario;
    // End of variables declaration//GEN-END:variables
    //Modelo de la tabla cajas
    private DefaultTableModel modelocaja;

    private final String columnas_cajas[] = {"Id", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial"};

    //Modelo de la tabla pedidos
    private DefaultTableModel modelopedido;

    private final String columnas_pedidos[] = {"Id", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Total Ordenes"};

    //Modelo de la tabla entradas
    private DefaultTableModel modeloentradas;

    private final String columnas_entradas[] = {"Id", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Total Remisiones", "Total Entradas"};

    //Modelo de la tabla salidas
    private DefaultTableModel modelosalidas;

    private final String columnas_salidas[] = {"Id", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Total Salidas"};

    public static Inventario getInstance() {
        if (myInstance == null) {
            myInstance = new Inventario();
        }
        return myInstance;
    }

    //EVENTO PARA LIMPIAR
    private void limpiarInventario() {
        lbl_id_caja.setText(null);
        txt_id_inventario.setText(null);
        txt_marca_inventario.setText(null);
        txt_vitola_inventario.setText(null);
        txt_nombre_inventario.setText(null);
        txt_capa_inventario.setText(null);
        txt_tipo_inventario.setText(null);
        txt_inventario_incial_inv.setText(null);
        txt_total_orden.setText(null);
        txt_total_entradas_inve.setText(null);
        txt_total_salidas_inv.setText(null);
        txt_inventario_total.setText(null);

    }
    
    //FECHA ACTUAL
    public static String fechaActual(){
    
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");
        
        return formatoFecha.format(fecha);
        
    }
    
    
    String Ruta;
    public void impresion(){
       String imagen = "base\\Reporte_Inventario_MP.xls";
        Ruta = System.getProperty("user.dir") + "\\" + imagen;
        File file = new File(Ruta);
        ExportarExcel excel = new ExportarExcel(tb_tablas,file,"tb_tablas");
        excel.export();
    }
    
}
