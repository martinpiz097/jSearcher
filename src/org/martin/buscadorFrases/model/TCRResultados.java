/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.buscadorFrases.model;

import java.awt.Color;
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
        JLabel lbl = new JLabel();
        ImageIcon icon;
        File file = archivos.get(row);
        
        if (column == 0) {
            if (file.isDirectory()) 
                icon = new ImageIcon(getClass().getResource("/org/martin/buscadorFrases/resources/folder.png"));

            else
                icon = new ImageIcon(getClass().getResource("/org/martin/buscadorFrases/resources/file.png"));
        
            lbl.setIcon(icon);
            lbl.setText(file.getName());
        }
    
        else lbl.setText(value.toString());

        Color bg = lbl.getBackground();
        
        if (isSelected) lbl.setBackground(Color.CYAN);
            
        else lbl.setBackground(bg);
        lbl.setOpaque(true);
        return lbl;
    }

}
