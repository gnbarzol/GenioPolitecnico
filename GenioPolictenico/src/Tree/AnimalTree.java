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
     * 
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
        guardarArbol(root);
        
    }
    
    public void posOrden(){
        posOrden(root);
    }

//metodo recursivo para recorrido posorden

    private void posOrden(Node nodo){
        if (nodo == null)
            return;
        posOrden(nodo.getLeft());
        posOrden(nodo.getRight());
        nodos.add((String)nodo.getData());
}


    
    public void guardarArbol(Node arbol){
        //Metodo que leerla el arbol pasado por parametro en pos-orden
        // y lo añadira al archivo de datos-1.txt.
        String ruta = "src/Recursos/datos-1.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        //Almacenamiento de las preguntas y respuestas contenidas en el archivo txt en una lista en posorden 
        List<String> a=leerArchivo();
        //Almacenamiento de las preguntas y respuestas del nodo root en una lista en posorden
        posOrden(root);
        if(archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo,true));
                //recorrido de las lista a para comprar con los elementos de b
                for(int i=0; i<a.size();i++){
                    // si son iguales solo se agrega
                    if(a.get(i).substring(2).trim().equals(nodos.get(i).trim()))
                        bw.write(a.get(i)+"\n");
                    //else if para coger los elementos sobrantes de b
                    else if(i>=a.size()){
                        for(int j=i; j<nodos.size();j++){
                            if(nodos.get(i).trim().endsWith("?"))
                                bw.write("#P "+ nodos.get(j)+"\n");
                            else
                                bw.write("#R "+ nodos.get(j)+"\n");
                        }
                    }
                    //else que agrega las preguntas y respuestas que no se encuentran en el txt
                    else{
                        bw.write("#R "+nodos.get(i)+"\n");
                        bw.write("#R "+nodos.get(i+1)+"\n");
                        bw.write("#P "+nodos.get(i+2)+"\n");
                        i=i+3;
                    } 
                }
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(AnimalTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    
}
