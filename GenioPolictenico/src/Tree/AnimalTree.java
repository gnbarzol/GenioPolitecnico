/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author user
 */
public class AnimalTree {
    private Node root;

    
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
        
    }
    
    
    public void guardarArbol(Node arbol){
        //Metodo que leerla el arbol pasado por parametro en pos-orden
        // y lo añadira al archivo de datos-1.txt.
    }
    
    public int BFS(Node p_tree) {
        if (p_tree == null) {
            return 1;
        }
        Queue<Node> queue_level = new LinkedList<>();
        queue_level.clear();
        queue_level.add(p_tree);
        while (!(queue_level.isEmpty())) {
            Node temp = queue_level.remove();
            System.out.println(temp.getData() + " ");
            if (temp.getLeft() != null) {
                queue_level.add(temp.getLeft());
            }
            if (temp.getRight()!= null) {
                queue_level.add(temp.getRight());
            }
        }
        return 0;
    }
    
    public void recorridoPosorden()
    {
    ayudantePosorden(root);
    }

    //metodo recursivo para recorrido posorden

    private void ayudantePosorden(Node nodo)
    {
    if (nodo == null)
    return;

    ayudantePosorden(nodo.getLeft());
    ayudantePosorden(nodo.getRight());
    System.out.println(nodo.getData() + " ");
    }



    
    public static void main (String[] args){
        AnimalTree ad = new AnimalTree();
        ad.recorridoPosorden();
    }
    
}
