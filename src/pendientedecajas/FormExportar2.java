/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendientedecajas;

import com.lowagie.text.List;
import java.io.File;
import javax.swing.JTable;
import java.io.*;
//import java.util.List;
import javax.swing.*;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import jxl.*;

/**
 *
 * @author Elssy
 */
public class FormExportar2 {

    private File file;
    java.util.List tabla;
    java.util.List nom_files;

    /**
     *
     * @param file
     * @param tabla
     * @param nom_files
     * @throws Exception
     */
    public FormExportar2(File file, java.util.List tabla, java.util.List nom_files) throws Exception {
        this.file = file;
        this.tabla = tabla;
        this.nom_files = nom_files;
        if (nom_files.size() != tabla.size()) {
            throw new Exception("Error");
        }
    }

    public boolean export() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            
            for (int index = 0; index < tabla.size(); index++) {
                JTable table = (JTable) tabla.get(index);
                WritableSheet s = w.createSheet((String) nom_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i, j, String.valueOf(object)));
                    }
                }
            }
            w.write();
            w.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
