//package org.martin.buscadorFrases.data;
//
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.LinkedList;
//import javax.swing.JTextArea;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author martin
// */
//public class Buscador {
//    
//    private File directory;
//    private String busqueda;
//    private int contadorResultados;
//    private JTextArea areaResultados;
//    private boolean terminado;
//    
//    public Buscador(String ruta, String busqueda, JTextArea areaResultados) throws FileNotFoundException {
//
//        directory = new File(ruta);
//        System.out.println(directory.getName());
//        this.busqueda = busqueda;
//        contadorResultados = 0;
//        this.areaResultados = areaResultados;
//        this.terminado = false;
//    }
//    
//    public void searchText() throws FileNotFoundException{
//        
//        terminado = false;
//        reiniarContador();
//        buscarFrase(directory);
//        terminado = !terminado;
//    }
//    
//    public void searchFile(){
//        terminado = false;
//        reiniarContador();
//        searchFiles(directory);
//        terminado = !terminado;
//    }
//    private void addResultado(String strParam){
//        areaResultados.setText(areaResultados.getText() + strParam + "\n");
//    }
//
//    public JTextArea getAreaResultados() {
//        return areaResultados;
//    }
//
//    public String getBusqueda() {
//        return busqueda;
//    }
//
//    public File getDirectory() {
//        return directory;
//    }
//
//    public int getContadorResultados() {
//        return contadorResultados;
//    }
//
//    public boolean isTerminado() {
//        return terminado;
//    }
//    
//    public void buscarFrase(File directory) throws FileNotFoundException{
//        
//        BufferedReader br;
//        boolean rutaImpresa = false;
//        for (File file : directory.listFiles()) {
//            
//            if (file.isDirectory()) buscarFrase(file);
//            
//            else{
//                
//                br = new BufferedReader(new FileReader(file));
//                
//                br.lines().forEach((line) -> {
//                    
//                    if (line.contains(busqueda) && !rutaImpresa) {
//                        aumentarContador();
//                        addResultado(file.getName());
//                        toTrue(rutaImpresa);
//                        System.out.println(rutaImpresa);
//                    }
//                });
//            }
//        }
//    }
//    
//    public void searchFiles(File directory){
//
//        for (File file : directory.listFiles()) {
//            
//            if (file.isDirectory()) searchFiles(file);
//
//            else if (file.getName().contains(busqueda)) {
//                addResultado(file.getAbsolutePath());
//                aumentarContador();
//            }    
//        }
//        
//    }
//    
//    private void toTrue(boolean param){
//        param = true;
//    }
//    
//    private void aumentarContador(){
//        contadorResultados++;
//    }
//
//    private void reiniarContador(){
//        contadorResultados = 0;
//    }
//}
