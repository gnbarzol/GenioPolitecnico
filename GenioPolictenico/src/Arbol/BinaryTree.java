package Arbol;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class BinaryTree<E> {
    private Node<E> root;
    
    public BinaryTree(){
        this.root = null;
    }
    
    public boolean isEmpty(){
        return this.root==null;
    }
    
    private Node<E> searchNode(E data){
        return searchNode(data,root);
    }
    
    
    private Node<E> searchNode(E data, Node<E> n){
        if(n==null) return null;
        else if(n.getData().equals(data)) return n;
        else{
            Node<E> l = searchNode(data, n.getLeft());
            return (l!=null)? l:searchNode(data, n.getRight()); //Condicion ternaria
        }
    }

    
    public boolean add(E child, E parent){
        Node<E> nch = new Node<>(child);
        if(child == null) return false;
        else if(parent == null && isEmpty()){
            root=nch;
            return true;
        }else if(parent!=null){
            if(searchNode(child)==null){
                Node<E> np = searchNode(parent);
                if(np==null || (np.getLeft()!=null && np.getRight()!=null))
                    return false;
                else if(np.getLeft()==null)
                    np.setLeft(nch);
                else
                    np.setRight(nch);
            }return true;
        }return false;
    }
    
    public int height(){
        return height(root);
    }
    private int height(Node<E> n){
        if(n==null) return 0;
        return 1+ Math.max(height(n.getLeft()), height(n.getRight()));
    }
}
