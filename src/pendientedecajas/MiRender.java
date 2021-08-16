/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendientedecajas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Elssy
 */
  import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MiRender extends DefaultTableCellRenderer{

    private final int columna_patron;
  int columna;
  
    public MiRender(int Colpatron) {
        //Variable que encuentra la columna
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int rowIndex, int columnIndex) {
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, rowIndex, columnIndex);
      

        int valor = Integer.parseInt(table.getValueAt(rowIndex, columna_patron).toString());
        System.out.println(""+valor);
        if (valor < 0 ) {
            setBackground(Color.red);
            setForeground(Color.black);
        } else if (valor  == 0) {
            setBackground(Color.yellow);
            setForeground(Color.black);
        } else { //si no ponemos el else se pinta toda la columna
            setBackground(Color.white);
            setForeground(Color.black);
        }

        return this;
    }

}
