package org.martin.buscadorFrases.data;


import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.martin.buscadorFrases.model.TCRResultados;
import org.martin.buscadorFrases.model.TMResultados;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author martin
 */
public class Buscador {
    
    private File directory;
    private String busqueda;
    private int contadorResultados;
    private JPanel areaResultados;
    private boolean terminado;
    private TMResultados model;
    private JTable table;
    private LinkedList<File> listaResultados;
    
    public Buscador(String ruta, String busqueda, JPanel areaResultados) throws FileNotFoundException {

        directory = new File(ruta);
        System.out.println(directory.getName());
        this.busqueda = busqueda;
        contadorResultados = 0;
        this.areaResultados = areaResultados;
        this.terminado = false;
        listaResultados = new LinkedList<>();
    }

    public Buscador(String ruta, String busqueda, JTable table) {
        this.directory = new File(ruta);
        this.busqueda = busqueda;
        contadorResultados = 0;
        this.table = table;
        this.terminado = false;
        listaResultados = new LinkedList<>();
    }
    
    public void searchText() throws FileNotFoundException{
        
        terminado = false;
        reiniarContador();
        buscarFrase(directory);
        terminado = !terminado;
    }
    
    public void searchFile(){
        terminado = false;
        reiniarContador();
        searchFiles(directory);
        terminado = !terminado;
    }
    
//    private void addResultado(File file){
//
//        JLabel lbl = new JLabel(file.getName());
//        ImageIcon img;
//        
//        if (file.isDirectory()) img = new ImageIcon(getClass().getResource(
//                                     "/org/martin/buscadorFrases/resources/folder.png"));
//        
//        else img = new ImageIcon(getClass().getResource(
//                                     "/org/martin/buscadorFrases/resources/file.png"));
//        
//        lbl.setIcon(img);
//        lbl.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
//        lbl.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                try {
//                    if (file.isDirectory()) Desktop.getDesktop().open(file);
//                
//                    else Desktop.getDesktop().open(getDirectory(file));
//                
//                } catch (IOException ex) {
//                    Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//            }
//        });
//        
//        lbl.setSize(areaResultados.getWidth(), 30);
//        areaResultados.add(lbl);
//        areaResultados.updateUI();
//    }
//    
    // Retorna el directorio al cual el archivo pertenece
    
    private void addResultado(File file){

        if (listaResultados.isEmpty()) {
            table.setRowHeight(32);
            table.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                    try {
                        TMResultados tm = (TMResultados) table.getModel();
                        File selected = tm.getFile(table.getSelectedRow());

                        if (selected.isDirectory()) {
                            Desktop.getDesktop().open(selected);
                        } else {
                            Desktop.getDesktop().open(getDirectory(selected));
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

        }
        
        listaResultados.add(file);
        System.out.println("Cantidad de resultados actual: " + listaResultados.size());
        table.setModel(new TMResultados(listaResultados));
        table.setDefaultRenderer(Object.class, new TCRResultados(listaResultados));
    }
    
    private File getDirectory(File f){
        
        String filePath = f.getAbsolutePath();
        String path = "";
        int cantSlash = 0;
        int cantSlash2 = 0;
        
        for (int i = 0; i < filePath.length(); i++)
            if (filePath.charAt(i) == '/') cantSlash++; 

        for (int i = 0; i < filePath.length(); i++) {
            if (filePath.charAt(i) == '/') cantSlash2++; 
            
            path += filePath.charAt(i);
            
            if(cantSlash2 == cantSlash) break;
            
        }
        System.out.println(cantSlash);
        System.out.println(filePath);
        System.out.println(path);
        return new File(path);
    }

    public JPanel getAreaResultados() {
        return areaResultados;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public File getDirectory() {
        return directory;
    }

    public int getContadorResultados() {
        return contadorResultados;
    }

    public boolean isTerminado() {
        return terminado;
    }
    
    public void buscar(TypeSearch type, File directory) throws FileNotFoundException{
        
        if (type == TypeSearch.FRASE) buscarFrase(directory);
        
        else searchFiles(directory);
    }
    
    public void buscarFrase(File directory) throws FileNotFoundException{
        
        BufferedReader br;
        boolean rutaImpresa = false;
        for (File file : directory.listFiles()) {
            
            if (file.isDirectory()) buscarFrase(file);
            
            else{
                
                br = new BufferedReader(new FileReader(file));
                
                br.lines().forEach((line) -> {
                    
                    if (line.contains(busqueda) && !rutaImpresa) {
                        aumentarContador();
                        addResultado(file);
                        toTrue(rutaImpresa);
                        System.out.println(rutaImpresa);
                    }
                });
            }
        }
    }
    
    public void searchFiles(File directory){

        for (File file : directory.listFiles()) {
            
            if (file.getName().contains(busqueda)) {
                addResultado(file);
                aumentarContador();
            }    
            
            if (file.isDirectory()) searchFiles(file);

        }        
    }
    
    private void toTrue(boolean param){
        param = true;
    }
    
    private void aumentarContador(){
        contadorResultados++;
    }

    private void reiniarContador(){
        contadorResultados = 0;
    }
}
