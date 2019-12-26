/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class Adivinador {
    private Node n;
    private BinaryTree arbol;

    public Adivinador() {
        this.arbol = new BinaryTree(cargarArbol(leerArchivo()));
        
    }
    
    public List<String> leerArchivo(){
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
    
    public Node cargarArbol(List<String> lista){
        Deque<Node> pila = new LinkedList<>();
        for(int i=0; i<lista.size();i++){
            String elemento = lista.get(i);
            String tipo = elemento.substring(0, 2);
            String data = elemento.substring(2).trim();
            Node<String> node = new Node<>(data);
            if(tipo.equals("#R"))
                pila.offer(node);
            else{   
                Node<String> right = pila.poll();
                Node<String> left = pila.poll();
                node.setLeft(left);
                node.setRight(right);
                pila.offer(node);
            }
        }
        Node<String> fin = pila.poll();
        System.out.println(fin.getLeft().getData()+","+fin.getRight().getData());
        return fin;
    }

    public static void main(String[] arg){
        Adivinador ad = new Adivinador();
        
    }
    
}
