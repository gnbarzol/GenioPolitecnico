/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class AnimalTree {
    private Node root;
    private List<String> nodos;
    
    public AnimalTree() {
        this.root =cargarArbol(leerArchivo());
        
    }
    
    /**
     * 
     * @return Me retorna una lista con los datos de los nodos que se 
     * encuentran en el archivo.txt
     */
    public final List<String> leerArchivo(){
        List<String> data= new ArrayList();
        try(BufferedReader br= new BufferedReader(new FileReader("src/Recursos/datos-1.txt"))){
            String linea;
            while((linea=br.readLine())!=null){
                   data.add(linea);
            } 
        }catch (FileNotFoundException ex) {
            System.out.println("Archivo clientes no encontrado");
        }catch(IOException ex){
            System.out.println("Error inesperado en la clase: "+getClass());
        }    
        return data;
    }
    
    /**
     * 
     * @param lista con los datos de todos los nodos
     * @return Un arbol creado a partir de la lista de dataNodos
     */
    public final Node cargarArbol(List<String> lista){
        Deque<Node> pila = new LinkedList<>();
        for(int i=0; i<lista.size();i++){
            String elemento = lista.get(i);
            String tipo = elemento.substring(0, 2);
            String data = elemento.substring(2).trim();
            Node<String> node = new Node<>(data);
            
            if(tipo.equals("#R")){
                pila.push(node);
            }
            else{   
                Node<String> right = pila.pop();
                Node<String> left = pila.pop();
                node.setLeft(left);
                node.setRight(right);
                pila.push(node);
            }
        }
        Node<String> resul = pila.pop();
        return resul;
    }   

    public Node getArbol() {
        return root;
    }
    
    /**
     * @param node Es el nodo que va a ser sustituido por el nuevo subArbol creado
     * @param pregunta Es la pregunta que el usuario formula para identificar la respuesta(Nueva Raiz)
     * @param respuesta, es la respuesta(Hoja) de la nueva raiz formada de la pregunta
     * @param bool, es la respuesta del usuario ante un pregunta( SI , NO)
     */
    public void actualizarNodos(Node<String> node, String pregunta, String respuesta, Boolean bool){
        Node<String> antiguoNode= new Node<>(node.getData());
        Node<String> r= new Node<>(respuesta);
        node.setData(pregunta);
        if(bool){
            node.setLeft(r);
            node.setRight(antiguoNode);
        }else{
            node.setRight(r);
            node.setLeft(antiguoNode);            
        }
        guardarArbol();
        
    }
    
    /**
     * Metodo que leerla el arbol pasado por parametro en pos-orden
     */
    public void posOrden(){
        posOrden(root);
    }

    private void posOrden(Node nodo){
        if (nodo == null)
            return;
        posOrden(nodo.getLeft());
        posOrden(nodo.getRight());
        nodos.add((String)nodo.getData());
}


    /**
     * Me reescribe el archivo .txt con los nodos actualizados
     */
    public void guardarArbol(){
        nodos = new ArrayList<>();
        posOrden(root);
        String path = "src/Recursos/datos-1.txt";
        File archivo = new File(path);
        if(archivo.exists())
            try {
                Files.delete(Paths.get(path));
        } catch (IOException ex) {
            Logger.getLogger(AnimalTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        try(BufferedWriter bw= new BufferedWriter(new FileWriter(archivo))){
            for(String dataNodo: nodos){
                if(dataNodo.endsWith("?")){
                    bw.write("#P " + dataNodo);
                    bw.newLine();
                }else{
                    bw.write("#R " + dataNodo);
                    bw.newLine();
                }
            }
        }catch(IOException e){
            System.out.println("Error inesperado en la clase: "+getClass());
        } 
        
        
    }
    
}
