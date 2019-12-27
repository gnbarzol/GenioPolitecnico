/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolictenico;

import Tree.AnimalTree;
import Tree.Node;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class Adivinador {
    private BorderPane root;
    private final Font theFontSubtitle = Font.font("Helvetica", FontWeight.BOLD, 18 );
    private AnimalTree arbol;
    private Node node;
    private Label lPregunta;
    
    public Adivinador(){
        arbol = new AnimalTree();
        node = arbol.getArbol();
        root = new BorderPane();
        root.setTop(title());
        
        lPregunta = new Label();
        //lPregunta.setTextFill(Color.web("#333333"));
        //lPregunta.setFont(theFontSubtitle);
        //root.setCenter(lPregunta);
        //lPregunta.setOnMouseClicked((e)->{
            root.setCenter(esquemaCentral());
        //});
    }
    
    private VBox title(){
        Font theFonttitle = Font.font("Helvetica", FontWeight.BOLD, 21 );
        VBox contenedorTitulo = new VBox();
        Label titulo = new Label("Genio Politecnico");
        titulo.setTextFill(Color.web("#FFFFFF"));
        titulo.setFont(theFonttitle);
        titulo.setPadding(new Insets(10,0,10,0));
        contenedorTitulo.setStyle("-fx-background-color: #333333;");
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
        
        
        centro.getChildren().addAll(img,mostrarPreguntas("Empezar Juego"/*(String)node.getData()*/));
        centro.setStyle("-fx-background-color:aliceblue");
        centro.setAlignment(Pos.CENTER);
        centro.setSpacing(50);
        
        return centro;
    }
    
    public VBox mostrarPreguntas(String pregunta){
        VBox contenidoPreguntas = new VBox();
        lPregunta.setText(pregunta.toUpperCase());  //Debe estar como atributo en la clase
        lPregunta.setTextFill(Color.web("#333333"));
        lPregunta.setFont(theFontSubtitle);
        
        contenidoPreguntas.getChildren().addAll(lPregunta, mostrarOpciones());
        contenidoPreguntas.setAlignment(Pos.CENTER);
        contenidoPreguntas.setPrefSize(350, 650);
        contenidoPreguntas.setSpacing(30);
        
        return contenidoPreguntas;
    }
    
    public HBox mostrarOpciones(){
        HBox opciones = new HBox();
        Label lsi = new Label("Si");
        lsi.setId("OpSi");
        lsi.setFont(theFontSubtitle);
        Label lno = new Label("No");
        lno.setId("OpNo");
        lno.setFont(theFontSubtitle);
        
        lsi.setOnMouseClicked((e)->{
            if(!lPregunta.getText().toLowerCase().equals("empezar juego"))
                node = node.getLeft();
            actualizarPregunta(node);
            
            //Si el nodo que viene es null debo preguntarle por el animal y como identificarlo
        });
        
        lno.setOnMouseClicked((e)->{
            if(lPregunta.getText().toLowerCase().equals("empezar juego"))
                Platform.exit();
            node = node.getRight();
            actualizarPregunta(node);
            
            //Si el nodo que viene es null debo preguntarle por el animal y como identificarlo
        });
        
        opciones.getChildren().addAll(lsi,lno);
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(50);
        
        
        return opciones;
    }
    
    public void actualizarPregunta(Node node){
        lPregunta.setText(((String)node.getData()).toUpperCase()); 
    }
    
}
