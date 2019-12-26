/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolictenico;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author GaryBarzola
 */
public class Adivinador {
    private BorderPane root;
    
    public Adivinador(){
        root = new BorderPane();
        root.setTop(title());
        root.setCenter(esquemaCentral());
    }
    
    private VBox title(){
        VBox contenedorTitulo = new VBox();
        Label titulo = new Label("Genio Politecnico");
        
        contenedorTitulo.getChildren().add(titulo);
        contenedorTitulo.setAlignment(Pos.CENTER);
 
        return contenedorTitulo;
    }

    public BorderPane getRoot() {
        return root;
    }
    
    
    private HBox esquemaCentral(){
        HBox centro = new HBox();
        
        Image imgPuesto = new Image("/recursos/Adivinobaba.png");
        ImageView img = new ImageView(imgPuesto);
        img.setFitHeight(650);
        img.setFitWidth(450);
        
        centro.getChildren().addAll(img,mostrarJuego());
        centro.setAlignment(Pos.CENTER);
        centro.setSpacing(20);
        
        return centro;
    }
    
    public VBox mostrarJuego(){
        VBox contenidoPreguntas = new VBox();
        Label p1 = new Label("Pregunta de prueba?");
        
        
        contenidoPreguntas.getChildren().add(p1);
        contenidoPreguntas.setAlignment(Pos.CENTER);
        contenidoPreguntas.setPrefSize(350, 650);
        
        return contenidoPreguntas;
    }
    
}
