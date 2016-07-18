/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.buscadorFrases.model;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
/**
 *
 * @author martin
 */
public class TMResultados implements TableModel{
    
    private final LinkedList<File> archivos;
    private static final NumberFormat nf = new DecimalFormat("#0.0");
    private static final short BYTE_IN_KILOBYTE = 1024;
    
    public TMResultados(LinkedList<File> archivos) {
        System.out.println("Model built");
        this.archivos = archivos;
    }
    
    public void addFile(File f){
        archivos.add(f);
    }

    public LinkedList<File> getFiles(){
        return archivos;
    }
    
    public File getFile(int index){
        // archivos.element() --> retorna el primer elemento de la lista (solo en linkedList)
        return archivos.get(index);
    }

    private long raise(int number, int numberOfTimes){
        
        for (int i = 1; i < numberOfTimes; i++) 
            number *= number;

        return number;
    }
    
    @Override
    public int getRowCount() {
        return archivos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex){
            case 0: return "Nombre";
            case 1: return "Tamaño";
            default: return "Ubicacion";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        if (columnIndex == 0) return Object.class;
        
        else return String.class;
        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        File f = archivos.get(rowIndex);
        JLabel lbl;
        String rutaIcon;
        long tamaño = f.length();

        switch(columnIndex){
            
            case 1: 
                if (tamaño < raise(BYTE_IN_KILOBYTE, 2)) 
                    return nf.format((double)f.length() / 1000) + "kB";
                
                else if (tamaño < raise(BYTE_IN_KILOBYTE, 3)) 
                    return nf.format((double)f.length() / raise(BYTE_IN_KILOBYTE, 2)) + "MB";
                
                else return nf.format((double)f.length() / raise(BYTE_IN_KILOBYTE, 3)) + "GB";
                
            default: return f.getParent();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
    
}
