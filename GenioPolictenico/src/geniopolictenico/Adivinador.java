/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolictenico;

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
    private final Font theFonttitle = Font.font("Helvetica", FontWeight.BOLD, 19 );
    private final Font theFontSubtitle = Font.font("Helvetica", FontWeight.BOLD, 16 );
    
    public Adivinador(){
        root = new BorderPane();
        root.setTop(title());
        root.setCenter(esquemaCentral());
    }
    
    private VBox title(){
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
        
        centro.getChildren().addAll(img,mostrarPreguntas("Primera pregunta"));
        centro.setStyle("-fx-background-color:aliceblue");
        centro.setAlignment(Pos.CENTER);
        centro.setSpacing(50);
        
        return centro;
    }
    
    public VBox mostrarPreguntas(String pregunta){
        VBox contenidoPreguntas = new VBox();
        Label p1 = new Label(pregunta.toUpperCase());
        p1.setTextFill(Color.web("#333333"));
        p1.setFont(theFontSubtitle);
        
        contenidoPreguntas.getChildren().addAll(p1, mostrarOpciones());
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
        
        opciones.getChildren().addAll(lsi,lno);
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(50);
        
        
        return opciones;
    }
    
}
