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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static pendientedecajas.crearNuevaEntrada.txtidnuevaentrada;

/**
 *
 * @author grixe
 */
public class crearNuevaSalida extends javax.swing.JFrame {

    private static crearNuevaSalida myInstance;

    /**
     * Creates new form crearNuevaSalida
     */
    //Variable Place HOLDER
    PlaceHolder holder;

    TableRowSorter trs;

    //SUMA DE COLUMNAS
    private void suma() {
        int suma = 0;

        for (int i = 0; i < jtablesalidas.getRowCount(); i++) {

            int renglon;
            renglon = Integer.parseInt(jtablesalidas.getValueAt(i, 8).toString());

            suma = suma + renglon;

        }

        txttotalsalida.setText(String.valueOf(suma));
    }

    public crearNuevaSalida() {
        initComponents();
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Imagenes\\icono2.png"));
        mostrardatoscajas("");
        mostrardatos("");
        lblidcajasalida.setVisible(false);
        jforfechasalida.setText(fechaActual());

        txtidsalida.setText(DataBase.extraerIdMaxSalidas());

        holder = new PlaceHolder(txtbuscarcajasalida, "Marca, Vitola, Nombre, Capa");
        //PARA AÑADIR EL FOCUS A OTRO COMPONENTE
        btnañadircontenedor.requestFocus();

    }

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

    void mostrardatos(String valor) {
        DefaultTableModel modeloSalida = new DefaultTableModel() {

            //Quitar edición de las tablas
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloSalida.addColumn("ID Caja");
        modeloSalida.addColumn("Marca");
        modeloSalida.addColumn("Vitola");
        modeloSalida.addColumn("Nombre");
        modeloSalida.addColumn("Capa");
        modeloSalida.addColumn("Tipo de Empaque");
        modeloSalida.addColumn("ID Salida");
        modeloSalida.addColumn("Número de Contenedor");
        modeloSalida.addColumn("Cantidad");
        modeloSalida.addColumn("Fecha de Contenedor");
        jtablesalidas.setModel(modeloSalida);
        String sql = "";
        if (valor.equals("")) {
            sql = "select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n"
                    + "sal.id_salida, sal.num_contenedor, sal.cant_contenedor, sal.fecha_contenedor\n"
                    + "from registrocajas as caj join salidas as sal\n"
                    + "on caj.id_caja = sal.id_caja where caj.id_caja=0";
        } else {
            sql = "select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n"
                    + "sal.id_salida, sal.num_contenedor, sal.cant_contenedor, sal.fecha_contenedor\n"
                    + "from registrocajas as caj join salidas as sal\n"
                    + "on caj.id_caja = sal.id_caja where caj.id_caja= '" + valor + "'";

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
                modeloSalida.addRow(datos);
            }
            jtablesalidas.setModel(modeloSalida);
        } catch (SQLException ex) {

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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablecajas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtbuscarcajasalida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtmarcasalida = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtvitolasalida = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnombresalida = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcapasalida = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttipoempaquesalida = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtidsalida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnumcontenedorsalida = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtcantidadsalida = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtablesalidas = new javax.swing.JTable();
        txttotalsalida = new javax.swing.JTextField();
        btnguardarsalida = new javax.swing.JButton();
        btncancelarsalida = new javax.swing.JButton();
        btnactualizarsalida = new javax.swing.JButton();
        btnañadircontenedor = new javax.swing.JButton();
        jforfechasalida = new javax.swing.JFormattedTextField();
        lblidcajasalida = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salidas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

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

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscar:");

        txtbuscarcajasalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarcajasalidaKeyTyped(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Marca:");

        txtmarcasalida.setEditable(false);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Vitola:");

        txtvitolasalida.setEditable(false);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre:");

        txtnombresalida.setEditable(false);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Capa:");

        txtcapasalida.setEditable(false);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tipo de Empaque:");

        txttipoempaquesalida.setEditable(false);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("ID:");

        txtidsalida.setEditable(false);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Num. de contenedor:");

        txtnumcontenedorsalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumcontenedorsalidaKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Cantidad:");

        txtcantidadsalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadsalidaKeyTyped(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Fecha:");

        jtablesalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtablesalidas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(jtablesalidas);

        btnguardarsalida.setBackground(new java.awt.Color(51, 51, 51));
        btnguardarsalida.setForeground(new java.awt.Color(255, 255, 255));
        btnguardarsalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete.png"))); // NOI18N
        btnguardarsalida.setText("Guardar");
        btnguardarsalida.setEnabled(false);
        btnguardarsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarsalidaActionPerformed(evt);
            }
        });

        btncancelarsalida.setBackground(new java.awt.Color(51, 51, 51));
        btncancelarsalida.setForeground(new java.awt.Color(255, 255, 255));
        btncancelarsalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btncancelarsalida.setText("Cancelar");
        btncancelarsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarsalidaActionPerformed(evt);
            }
        });

        btnactualizarsalida.setBackground(new java.awt.Color(51, 51, 51));
        btnactualizarsalida.setForeground(new java.awt.Color(255, 255, 255));
        btnactualizarsalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        btnactualizarsalida.setText("Actualizar");
        btnactualizarsalida.setEnabled(false);
        btnactualizarsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarsalidaActionPerformed(evt);
            }
        });

        btnañadircontenedor.setBackground(new java.awt.Color(51, 51, 51));
        btnañadircontenedor.setForeground(new java.awt.Color(255, 255, 255));
        btnañadircontenedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/añadir.png"))); // NOI18N
        btnañadircontenedor.setText("Añadir");
        btnañadircontenedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnañadircontenedorActionPerformed(evt);
            }
        });

        try {
            jforfechasalida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jforfechasalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jforfechasalidaKeyTyped(evt);
            }
        });

        lblidcajasalida.setText("jLabel12");

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnguardarsalida)
                .addGap(18, 18, 18)
                .addComponent(btnactualizarsalida)
                .addGap(18, 18, 18)
                .addComponent(btncancelarsalida)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtmarcasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtvitolasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnombresalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcapasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttipoempaquesalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnumcontenedorsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcantidadsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jforfechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(btnañadircontenedor)))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalsalida, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscarcajasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblidcajasalida)
                        .addGap(72, 72, 72))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscarcajasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblidcajasalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmarcasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtvitolasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombresalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtcapasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txttipoempaquesalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtidsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtnumcontenedorsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtcantidadsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnañadircontenedor)
                    .addComponent(jforfechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotalsalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardarsalida)
                    .addComponent(btncancelarsalida)
                    .addComponent(btnactualizarsalida))
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -4, 940, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //FECHA ACTUAL
    public static String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");

        return formatoFecha.format(fecha);

    }


    private void btncancelarsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarsalidaActionPerformed
        dispose();

    }//GEN-LAST:event_btncancelarsalidaActionPerformed

    private void jtablecajasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablecajasMouseClicked

        lblidcajasalida.setText("");
        txtmarcasalida.setText("");
        txtvitolasalida.setText("");
        txtnombresalida.setText("");
        txtcapasalida.setText("");
        txttipoempaquesalida.setText("");

        int row = jtablecajas.rowAtPoint(evt.getPoint());

        lblidcajasalida.setText(jtablecajas.getValueAt(row, 0).toString());
        txtmarcasalida.setText(jtablecajas.getValueAt(row, 1).toString());
        txtvitolasalida.setText(jtablecajas.getValueAt(row, 2).toString());
        txtnombresalida.setText(jtablecajas.getValueAt(row, 3).toString());
        txtcapasalida.setText(jtablecajas.getValueAt(row, 4).toString());
        txttipoempaquesalida.setText(jtablecajas.getValueAt(row, 5).toString());
        mostrardatos(jtablecajas.getValueAt(row, 0).toString());
        suma();

    }//GEN-LAST:event_jtablecajasMouseClicked

    private void btnañadircontenedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnañadircontenedorActionPerformed

        if (txtmarcasalida.getText().isEmpty() || txtvitolasalida.getText().isEmpty() || txtnombresalida.getText().isEmpty()
                || txtcapasalida.getText().isEmpty() || txttipoempaquesalida.getText().isEmpty() || txtnumcontenedorsalida.getText().isEmpty()
                || txtcantidadsalida.getText().isEmpty() || jforfechasalida.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor llenar todos los campos.");

        } else {

            if (DataBase.validarIDSalida(txtidsalida.getText()) == 0) {

                try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO salidas (id_caja, num_contenedor, cant_contenedor, fecha_contenedor) VALUES (?,?,?,?)");

                    pst.setString(1, lblidcajasalida.getText());
                    pst.setString(2, txtnumcontenedorsalida.getText());
                    pst.setString(3, txtcantidadsalida.getText());
                    pst.setString(4, jforfechasalida.getText());

                    pst.executeUpdate();

                    this.btnguardarsalida.setEnabled(true);
                    this.btnactualizarsalida.setEnabled(false);

                    //mostrardatos("");
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

                //LIMPIAR CAMPOS AL AÑADIR
                txtnumcontenedorsalida.setText("");
                txtcantidadsalida.setText("");

                //MOSTRAR ID
                txtidsalida.setText(DataBase.extraerIdMaxSalidas());

                mostrardatos("");

            } else {

                DataBase.ActualizarSalidas(txtidsalida.getText(), lblidcajasalida.getText(), txtnumcontenedorsalida.getText(),
                        txtcantidadsalida.getText(), jforfechasalida.getText());

                mostrardatos("");

                JOptionPane.showMessageDialog(null, "Se editó exitosamente");
                this.btnactualizarsalida.setEnabled(true);
                this.btnguardarsalida.setEnabled(false);

                //LIMPIAR CAMPOS AL AÑADIR
                txtnumcontenedorsalida.setText("");
                txtcantidadsalida.setText("");

                //MOSTRAR ID
                txtidsalida.setText(DataBase.extraerIdMaxSalidas());

            }

        }

        jforfechasalida.setText(fechaActual());
        mostrardatos(lblidcajasalida.getText());
        suma();

    }//GEN-LAST:event_btnañadircontenedorActionPerformed

    private void btnguardarsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarsalidaActionPerformed
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO salidastotales(id_caja, total_salidas) VALUES (?,?)");
            pst.setString(1, lblidcajasalida.getText());
            pst.setString(2, txttotalsalida.getText());

            pst.executeUpdate();

            // Mensaje de exito
            JOptionPane.showMessageDialog(null, "Se guardó exitosamente");

            this.btnguardarsalida.setEnabled(false);
            this.btnactualizarsalida.setEnabled(false);

            //MOSTRAR ID
            txtidsalida.setText(DataBase.extraerIdMaxSalidas());

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        //LIMPIAR CAMPOS AL GUARDAR
        txtnumcontenedorsalida.setText("");
        txtcantidadsalida.setText("");
        jforfechasalida.setText(fechaActual());
        txttotalsalida.setText("");

        //MOSTRAR ID
        txtidsalida.setText(DataBase.extraerIdMaxSalidas());

        this.btnguardarsalida.setEnabled(false);
        this.btnactualizarsalida.setEnabled(false);
    }//GEN-LAST:event_btnguardarsalidaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int fila = jtablesalidas.getSelectedRow();

        if (fila >= 0) {

            lblidcajasalida.setText(jtablesalidas.getValueAt(fila, 0).toString());
            txtmarcasalida.setText(jtablesalidas.getValueAt(fila, 1).toString());
            txtvitolasalida.setText(jtablesalidas.getValueAt(fila, 2).toString());
            txtnombresalida.setText(jtablesalidas.getValueAt(fila, 3).toString());
            txtcapasalida.setText(jtablesalidas.getValueAt(fila, 4).toString());
            txttipoempaquesalida.setText(jtablesalidas.getValueAt(fila, 5).toString());
            txtidsalida.setText(jtablesalidas.getValueAt(fila, 6).toString());
            txtnumcontenedorsalida.setText(jtablesalidas.getValueAt(fila, 7).toString());
            txtcantidadsalida.setText(jtablesalidas.getValueAt(fila, 8).toString());
            jforfechasalida.setText(jtablesalidas.getValueAt(fila, 9).toString());

        } else {
            JOptionPane.showMessageDialog(null, "No seleciono fila");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnactualizarsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarsalidaActionPerformed
       DataBase.ActualizarSalidasTotales(lblidcajasalida.getText(), txttotalsalida.getText());
        JOptionPane.showMessageDialog(null, "Se editó exitosamente");
        this.btnguardarsalida.setEnabled(false);
        this.btnactualizarsalida.setEnabled(false);
        jforfechasalida.setText(fechaActual());
        txttotalsalida.setText("");
    }//GEN-LAST:event_btnactualizarsalidaActionPerformed

    private void txtbuscarcajasalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarcajasalidaKeyTyped

        //METODO PARA BUSCAR
        txtbuscarcajasalida.setText(txtbuscarcajasalida.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);
        DefaultTableModel salida = (DefaultTableModel) jtablecajas.getModel();
        txtbuscarcajasalida.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtbuscarcajasalida.getText(), 1, 2, 3, 4));
                //trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtbuscar_ver_cajas.getText(), 3));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(salida);
        jtablecajas.setRowSorter(trs);


    }//GEN-LAST:event_txtbuscarcajasalidaKeyTyped

    private void txtnumcontenedorsalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumcontenedorsalidaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtnumcontenedorsalidaKeyTyped

    private void txtcantidadsalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadsalidaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtcantidadsalidaKeyTyped

    private void jforfechasalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jforfechasalidaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_jforfechasalidaKeyTyped

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
            java.util.logging.Logger.getLogger(crearNuevaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crearNuevaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crearNuevaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crearNuevaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crearNuevaSalida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizarsalida;
    private javax.swing.JButton btnañadircontenedor;
    private javax.swing.JButton btncancelarsalida;
    private javax.swing.JButton btnguardarsalida;
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField jforfechasalida;
    private javax.swing.JTable jtablecajas;
    private javax.swing.JTable jtablesalidas;
    private javax.swing.JLabel lblidcajasalida;
    private javax.swing.JTextField txtbuscarcajasalida;
    private javax.swing.JTextField txtcantidadsalida;
    private javax.swing.JTextField txtcapasalida;
    private javax.swing.JTextField txtidsalida;
    private javax.swing.JTextField txtmarcasalida;
    private javax.swing.JTextField txtnombresalida;
    private javax.swing.JTextField txtnumcontenedorsalida;
    private javax.swing.JTextField txttipoempaquesalida;
    private javax.swing.JTextField txttotalsalida;
    private javax.swing.JTextField txtvitolasalida;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel modelocaja;
    private final String[] columnas_caja = {"ID", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventrio Inicial"};

    //Metodo para que solo se abra una sola pantalla.
    public static crearNuevaSalida getInstance() {
        if (myInstance == null) {
            myInstance = new crearNuevaSalida();
        }
        return myInstance;
    }

    conectar cc = new conectar();
    Connection cn = cc.conexion();

}
