package org.martin.buscadorFrases.data;


import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
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
    private static File[] files;
    
    public Buscador(String ruta, String busqueda, JPanel areaResultados) throws FileNotFoundException {

        directory = new File(ruta);
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
    
    public void search(TypeSearch typeSearch, TypeSearch include) throws FileNotFoundException, InterruptedException{
        
        if (typeSearch == TypeSearch.FILE) searchFile(include);
        else searchText(include);
        showResults();
    }
    
    public void searchText(TypeSearch include) throws FileNotFoundException, InterruptedException{
        
        terminado = false;
        reiniarContador();
        startSearchPhrase(directory, include);
        terminado = !terminado;
    }
    
    public void searchFile(TypeSearch include) throws InterruptedException{
        terminado = false;
        reiniarContador();
        startSearchFiles(directory, include);
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
    
    public void showResults(){
        table.setRowHeight(30);
        table.setModel(new TMResultados(listaResultados));
        table.setDefaultRenderer(Object.class, new TCRResultados(listaResultados));
    }
    
    private File getDirectory(File f){
        
        String filePath = f.getAbsolutePath();
        String path = "";
        String[] directories = filePath.split("/");
        int cantSlash = directories.length-1;

        for (int i = 0; i < directories.length; i++) 
            if (i < directories.length-1) path += directories[i];
        
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
    
    public void startSearchPhrase(File directory, TypeSearch include) throws FileNotFoundException, InterruptedException{
        
        BufferedReader br;
        boolean rutaImpresa = false;
        files = directory.listFiles();
        
        if (files != null) {

            for (File file : files) {

                if (include == TypeSearch.INCLUDE_HIDDEN) {
                    
                    if (file.isDirectory()) startSearchPhrase(file, include);
                
                    else {

                        br = new BufferedReader(new FileReader(file));

                        String line;
                        Iterator<String> it;
                        
                        for (it = br.lines().iterator(); it.hasNext();) {
                            line = it.next();
                            if (line.contains(busqueda) && !rutaImpresa) {
                                aumentarContador();
                                listaResultados.add(file);
                                break;
                            }
                        }

                    }
                }
                
                else if (!file.isHidden()) {
                    
                    if (file.isDirectory()) startSearchPhrase(file, include);
                
                    else {

                        br = new BufferedReader(new FileReader(file));
                        String line;
                        Iterator<String> it;
                        
                        for (it = br.lines().iterator(); it.hasNext();) {
                            line = it.next();
                            if (line.contains(busqueda) && !rutaImpresa) {
                                aumentarContador();
                                listaResultados.add(file);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void startSearchFiles(File directory, TypeSearch include) throws InterruptedException{

        files = directory.listFiles();

        if (files != null) {

            for (File file : files) {

                if (include == TypeSearch.INCLUDE_HIDDEN) {
                    if (file.getName().contains(busqueda)) {
                        listaResultados.add(file);
                        aumentarContador();
                    }
                    if (file.isDirectory()) 
                        startSearchFiles(file, include);
                }
                
                else if(!file.isHidden()){
                    if (file.getName().contains(busqueda)) {
                        listaResultados.add(file);
                        aumentarContador();
                    }
                    if (file.isDirectory()) 
                        startSearchFiles(file, include);
                }

                

            }

        }
    }
    
    private void toTrue(boolean param){
        
        if (!param) param = !param; 
        
    }
    
    private void aumentarContador(){
        contadorResultados++;
    }

    private void reiniarContador(){
        contadorResultados = 0;
    }
}
