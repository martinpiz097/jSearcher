/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.buscadorFrases.model;

import java.awt.Component;
import java.io.File;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author martin
 */
public class TCRResultados implements TableCellRenderer {

    private LinkedList<File> archivos;

    public TCRResultados(LinkedList<File> archivos) {
        this.archivos = archivos;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        
        File f = archivos.get(row);
        JLabel lbl = new JLabel();
        
        if (column == 0) {
            String rutaIcon;
            lbl.setText(f.getName());
            if (f.isDirectory()) rutaIcon = "/org/martin/buscadorFrases/resources/folder.png";
        
            else rutaIcon = "/org/martin/buscadorFrases/resources/file.png";

            lbl.setIcon(new ImageIcon(getClass().getResource(rutaIcon)));
        }
        
        else lbl = new JLabel(value.toString());
        return lbl;
    }

}
