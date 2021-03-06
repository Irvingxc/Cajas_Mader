/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendientedecajas;

import base.DataBase;
import com.placeholder.PlaceHolder;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Elssy
 */
public class VerInventarioTotal extends javax.swing.JFrame {

    /**
     * Creates new form VerInventarioTotal
     */
    
      PlaceHolder holder;

    //VARIABLE PARA EL BUSCADOR
    TableRowSorter trs;
    private static VerInventarioTotal myInstance;

    public VerInventarioTotal() {
        initComponents();
        //pintarColumna();
        setLocationRelativeTo(null);
        mostrarRegistroInventario("");
        //tb_inventario.setDefaultRenderer(Object.class, new MiRender());
        
        btnRegresara_inventario.requestFocus();
        
        
        
         holder = new PlaceHolder(txt_buscar_registro_invent, "Marca, Vitola, Nombre, Capa, Fecha");
         
         tb_inventario.setRowSorter(trs);
    }

    void mostrarRegistroInventario(String valor) {
        //String sql1 = "SELECT id_pedido, enero, febrero FROM pedidosnuevos";
        DefaultTableModel modeloinventario = new DefaultTableModel() {

            //Quitar edición de las tablas
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        //tbpedidos.setModel(modelopedidos);

        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM inventario";
        }

        modeloinventario.addColumn("Id Inventario");
        modeloinventario.addColumn("Id Caja");
        modeloinventario.addColumn("Marca");
        modeloinventario.addColumn("Vitola");
        modeloinventario.addColumn("Nombre");
        modeloinventario.addColumn("Capa");
        modeloinventario.addColumn("Tipo de Empaque");
        modeloinventario.addColumn("Iventario Inicial");
        modeloinventario.addColumn("Total Entradas");
        modeloinventario.addColumn("Total Salidas");
        modeloinventario.addColumn("Total Orden");
        modeloinventario.addColumn("Total Inventario");
        modeloinventario.addColumn("Fecha Actual");

        tb_inventario.setModel(modeloinventario);
        
        //NO MOVER LAS COLUMNAS DE LA TABLA
        tb_inventario.getTableHeader().setReorderingAllowed(false);

        modeloinventario = new DefaultTableModel(DataBase.cargarDatosInventario(), columnas_invent);
        tb_inventario.setModel(modeloinventario);
        
        MiRender color = new MiRender(11);
        tb_inventario.getColumnModel().getColumn(11).setCellRenderer(color);
        

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
        txt_buscar_registro_invent = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_inventario = new javax.swing.JTable();
        btnRegresara_inventario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registros Inventario");

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Buscar:");

        txt_buscar_registro_invent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscar_registro_inventKeyTyped(evt);
            }
        });

        tb_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Inventario", "Id Caja", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Total Entradas", "Total Salidas", "Total Inventario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_inventario);

        btnRegresara_inventario.setBackground(new java.awt.Color(51, 51, 51));
        btnRegresara_inventario.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresara_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-correcta.png"))); // NOI18N
        btnRegresara_inventario.setText("Regresar");
        btnRegresara_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresara_inventarioActionPerformed(evt);
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
                        .addComponent(txt_buscar_registro_invent, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                        .addComponent(btnRegresara_inventario)
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_buscar_registro_invent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegresara_inventario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresara_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresara_inventarioActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresara_inventarioActionPerformed

    private void txt_buscar_registro_inventKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_registro_inventKeyTyped
          //METODO PARA BUSCAR
        txt_buscar_registro_invent.setText(txt_buscar_registro_invent.getText().replaceAll("( )+", " "));
        //Validaciones.Space(txt_buscar_pedidos.getText().length(), evt);        
        DefaultTableModel buscarusuario = (DefaultTableModel) tb_inventario.getModel();
        txt_buscar_registro_invent.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + txt_buscar_registro_invent.getText(), 2, 3, 4, 5, 12));
                //trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtbuscar_ver_cajas.getText(), 3));

                super.keyReleased(ke); //To change body of generated methods, choose Tools | Templates.
            }

        });
        trs = new TableRowSorter(buscarusuario);
        tb_inventario.setRowSorter(trs);
    }//GEN-LAST:event_txt_buscar_registro_inventKeyTyped

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
            java.util.logging.Logger.getLogger(VerInventarioTotal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerInventarioTotal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerInventarioTotal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerInventarioTotal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerInventarioTotal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresara_inventario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_inventario;
    private javax.swing.JTextField txt_buscar_registro_invent;
    // End of variables declaration//GEN-END:variables

    private final String columnas_invent[] = {"Id Inventario", "Id Caja", "Marca", "Vitola", "Nombre", "Capa", "Tipo de Empaque", "Inventario Inicial", "Total Entrdas", "Total Salidas", "Total Orden", "Total Inventario", "Fecha Actual"};

    public static VerInventarioTotal getInstance() {
        if (myInstance == null) {
            myInstance = new VerInventarioTotal();
        }
        return myInstance;
    }

    /*void pintarColumna(){
        MiRender color = new MiRender(10);
        tb_inventario.getColumnModel().getColumn(10).setCellRenderer(color);
        
    }*/
}
