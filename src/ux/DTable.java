/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ux;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class DTable extends JTable{
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
       Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
       component.setBackground(Color.WHITE);
       component.setForeground(Color.BLACK);
       
       if((Float.class.equals(this.getColumnClass(columnIndex))) && (getValueAt(rowIndex, columnIndex)!=null)){
           float val = Float.parseFloat(getValueAt(rowIndex, columnIndex).toString());
           if(val<=0){
               component.setBackground(Color.YELLOW);
               component.setForeground(Color.RED);
           }
       }
       return component;
    }
    
}
