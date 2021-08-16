/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendientedecajas;

import base.conectar;
import com.placeholder.PlaceHolder;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author grixe
 */
public class VerSalidas extends javax.swing.JFrame {

    /**
     * Creates new form VerSalidas
     */
    PlaceHolder holder;

    //VARIABLE PARA EL BUSCADOR
    TableRowSorter trs;

    private static VerSalidas myInstance;

    public VerSalidas() {
        initComponents();
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Imagenes\\icono2.png"));
        mostrardatosSalidas("");

        //Para que el cursor salga en otro lado
        btnregresar_salidas.requestFocus();

        holder = new PlaceHolder(txtbuscar_verSalidas, "Marca, Vitola, Nombre, Capa");

        jtableversalidas.setRowSorter(trs);
    }

    //METODO PARA MOSRAR DATOS EN LA TABLA
    void mostrardatosSalidas(String valor) {

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
        modeloVerEntrada.addColumn("Número de Contenedor");
        modeloVerEntrada.addColumn("Cantidad");
        modeloVerEntrada.addColumn("Fecha");
        modeloVerEntrada.addColumn("Total de Salida");

        jtableversalidas.setModel(modeloVerEntrada);

        //NO MOVER LAS COLUMNAS DE LA TABLA
        jtableversalidas.getTableHeader().setReorderingAllowed(false);

        String sql = "";
        if (valor.equals("")) {
            sql = "select caj.id_caja, caj.marca, caj.vitola, caj.nombre, caj.capa, caj.tipo_empaque,\n"
                    + "sal.num_contenedor, sal.cant_contenedor, sal.fecha_contenedor,\n"
                    + "tot.total_salidas\n"
                    + "from registrocajas as caj join salidas as sal join salidastotales as tot\n"
                    + "on caj.id_caja = sal.id_caja and caj.id_caja = tot.id_caja";
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
            jtableversalidas.setModel(modeloVerEntrada);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbuscar_verSalidas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableversalidas = new javax.swing.JTable();
        btnregresar_salidas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros de Salidas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscar:");

        txtbuscar_verSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscar_verSalidasKeyTyped(evt);
            }
        });

        jtableversalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableversalidas);

        btnregresar_salidas.setBackground(new java.awt.Color(51, 51, 51));
        btnregresar_salidas.setForeground(new java.awt.Color(255, 255, 255));
        btnregresar_salidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-correcta.png"))); // NOI18N
        btnregresar_salidas.setText("Regresar");
        btnregresar_salidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresar_salidasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar_verSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                        .addComponent(btnregresar_salidas)
                        .addGap(63, 63, 63))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar_verSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnregresar_salidas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnregresar_salidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresar_salidasActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnregresar_salidasActionPerformed

    private void txtbuscar_verSalidasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_verSalidasKeyTyped

        //METODO PARA BUSCAR
        txtbuscar_verSalidas.setText(txtbuscar_verSalidas.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);        
        DefaultTableModel buscarsalidas = (DefaultTableModel) jtableversalidas.getModel();
        txtbuscar_verSalidas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtbuscar_verSalidas.getText(), 1, 2, 3, 4, 5));
                //trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtbuscar_ver_cajas.getText(), 3));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(buscarsalidas);
        jtableversalidas.setRowSorter(trs);

    }//GEN-LAST:event_txtbuscar_verSalidasKeyTyped

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
            java.util.logging.Logger.getLogger(VerSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerSalidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerSalidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnregresar_salidas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtableversalidas;
    private javax.swing.JTextField txtbuscar_verSalidas;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();

    public static VerSalidas getInstance() {
        if (myInstance == null) {
            myInstance = new VerSalidas();
        }
        return myInstance;
    }

}
