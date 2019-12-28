/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolictenico;

import Tree.AnimalTree;
import Tree.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private final BorderPane root;
    private final Font theFontSubtitle = Font.font("Helvetica", FontWeight.BOLD, 18 );
    private final AnimalTree arbol;
    private Node node;
    private Label lPregunta;
    private VBox contenidoPreguntas;
    private VBox contenedorMejoras;
    private Boolean respt= false;
    
    public Adivinador(){
        contenedorMejoras= new VBox();
        contenidoPreguntas = new VBox();
        lPregunta = new Label();
        arbol = new AnimalTree();
        node = arbol.getArbol();
        root = new BorderPane();
        root.setTop(title());
        root.setCenter(esquemaCentral());
        
    }
    
    /**
     * 
     * @return Crea el titulo de la app
     */
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
    
    /**
     * 
     * @return Es el contenedor principal donde se visualizan las preguntas y las respuestas
     * que el adivino puede mostrar al usuario.
     */
    private HBox esquemaCentral(){
        HBox centro = new HBox();
        
        Image imgPuesto = new Image("/recursos/Adivinobaba.png");
        ImageView img = new ImageView(imgPuesto);
        img.setFitHeight(650);
        img.setFitWidth(450);
        
        VBox vB = mostrarPreguntas("Empezar Juego");
        if(centro.getChildren().contains(img)){
            centro.getChildren().clear();
        }
        centro.getChildren().addAll(img,vB);
        centro.setStyle("-fx-background-color:aliceblue");
        centro.setAlignment(Pos.CENTER);
        centro.setSpacing(50);
        
        return centro;
    }
    
    /**
     * 
     * @param pregunta Recibe la pregunta inicial para comenzar el juego
     * @return Contenedor con la pregunta pasada por parametro
     */
    public VBox mostrarPreguntas(String pregunta){
        lPregunta.setText(pregunta.toUpperCase());
        lPregunta.setTextFill(Color.web("#333333"));
        lPregunta.setFont(theFontSubtitle);
        
        if(contenidoPreguntas.getChildren().contains(lPregunta)){
            contenidoPreguntas.getChildren().clear();
        }
        contenidoPreguntas.getChildren().addAll(lPregunta, mostrarOpciones());
        contenidoPreguntas.setAlignment(Pos.CENTER);
        contenidoPreguntas.setPrefSize(350, 650);
        contenidoPreguntas.setSpacing(30);
        
        return contenidoPreguntas;
    }
    
    /**
     * 
     * @return Contenedor con las respuestas que el usuario puede elegir(Si, No)
     * al presionar si, se avanza a la pregunta de la izq.
     * al presionar no, se avanza a la pregunta de la der.
     * Al llegar a una respuesta(hoja) el genio pregunta si se acerto o no.
     */
    public HBox mostrarOpciones(){
        HBox opciones = new HBox();
        Label lsi = new Label("Si");
        lsi.getStyleClass().add("OpSi");
        lsi.setFont(theFontSubtitle);
        Label lno = new Label("No");
        lno.getStyleClass().add("OpNo");
        lno.setFont(theFontSubtitle);
        
        opciones.getChildren().addAll(lsi,lno);
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(50);
        
        lsi.setOnMouseClicked((e)->{
            if(!lPregunta.getText().toLowerCase().equals("empezar juego")){
                if(node.getLeft()!=null){
                    node = node.getLeft();
                    actualizarPregunta(node);
                }else{
                    lPregunta.getStyleClass().add("adivinoR");
                    adivinoAcerto();
                    opciones.getChildren().removeAll(lsi,lno);
                    opciones.getChildren().add(volverAJugar());
                }
                    
            }else{
                actualizarPregunta(node);
            }
        });
        
        lno.setOnMouseClicked((e)->{
            if(lPregunta.getText().toLowerCase().equals("empezar juego"))
                Platform.exit();
            else{
                if(node.getRight()!=null){
                    node = node.getRight();
                    actualizarPregunta(node);
                }else{
                    lPregunta.getStyleClass().add("adivinoF");
                    adivinoSMS("AYUDAME A MEJORAR");
                    opciones.getChildren().removeAll(lsi,lno);
                    opciones.getChildren().addAll(volverAJugar(), ayudarAMejorar());
                }
            }
            
        });
        
        return opciones;
    }
    
    /**
     * 
     * @param node Nodo con la data para cambiar el texto al Label lPregunta, el cual
     * muestra las preguntas al usuario.
     */
    public void actualizarPregunta(Node node){
        String data = (String)node.getData();
        if(!data.substring(data.trim().length()-1).equals("?")){
            data = "ES "+data+"?";
        }
        lPregunta.setText(data.toUpperCase()); 
    }
    
    /**
     * Manda un mensaje al usuario de que el genio acerto.
     */
    public void adivinoAcerto(){
        lPregunta.setText("EL GENIO LO HIZO DE NUEVO!");
    }
    
    /**
     * 
     * @param sms Recibe un sms del mensaje que quiero mostrar al usuario
     */
    public void adivinoSMS(String sms){
        lPregunta.setText(sms);
    }
    
    /**
     * 
     * @return Label jugarAgain, para que el usuario pueda volver a jugar
     */
    public Label volverAJugar(){
        Label jugarAgain = new Label("Volver a Jugar");
        jugarAgain.setTextFill(Color.web("#333333"));
        jugarAgain.setFont(theFontSubtitle);
        jugarAgain.getStyleClass().add("OpSi");
        
        jugarAgain.setOnMouseClicked((e)->{
            node = arbol.getArbol();
            root.setCenter(esquemaCentral());
            lPregunta.getStyleClass().clear();
            contenedorMejoras.getChildren().clear();
        });

        return jugarAgain;
    }
    
    /**
     * 
     * @return Label para mejorar el juego, llamado al momento de no acertar un pensamiento.
     */
    public Label ayudarAMejorar(){
        Label ayudar = new Label("Ayudar");
        ayudar.setTextFill(Color.web("#333333"));
        ayudar.setFont(theFontSubtitle);
        ayudar.getStyleClass().add("OpSi");
        
        ayudar.setOnMouseClicked((e)->{
            lPregunta.getStyleClass().clear();
            contenidoPreguntas.getChildren().clear();
            contenidoPreguntas.getChildren().add(obtenerDatos());
            //codigo que pida pregunta y respuesta y añada al arbol
        });
        
        return ayudar;
    }
    
    /**
     * 
     * @return Contenedor para que el usuario ingrese la pregunta y la respuesta
     */
    public VBox obtenerDatos(){
        
        VBox respuesta = new VBox();
        Button btn_sgt = new Button("Continuar");
        
        TextField tRespuesta = preguntarAnimal(respuesta);
        String data = (String)node.getData();

        contenedorMejoras.getChildren().addAll(respuesta, btn_sgt);
        contenedorMejoras.setAlignment(Pos.CENTER);
        contenedorMejoras.setSpacing(20);
        
                
        btn_sgt.setOnMouseClicked((e)->{
            VBox pregunta = new VBox();
            Button btn_sgte = new Button("Continuar");
            
            String dataRespuesta = tRespuesta.getText().trim();
            TextField tPregunta = preguntarPregunta(pregunta, dataRespuesta, data);
            
            respuesta.getChildren().clear();
            contenedorMejoras.getChildren().clear();
            contenedorMejoras.getChildren().addAll(pregunta, btn_sgte);
            
            btn_sgte.setOnMouseClicked((ex)->{
                VBox vBlugar = new VBox();

                String dataPregunta = tPregunta.getText().trim();
                
                Boolean respuestaLugar = asignarLugarNodo(vBlugar, dataRespuesta, dataPregunta);
                
                System.out.println("Node antes de actualizar: "+node.getData());
                
                arbol.actualizarNodos(node, dataPregunta, dataRespuesta, respuestaLugar);
                //llamar al metodo que me guarda el arbol
                
                System.out.println("Node izq de actualizar: "+node.getLeft().getData());
                
                pregunta.getChildren().clear();
                contenedorMejoras.getChildren().clear();
                contenedorMejoras.getChildren().addAll(vBlugar);
            });
        });
        
        return contenedorMejoras;
    }
    
    public TextField preguntarAnimal(VBox contenedor){
        Label rUser = new Label("Que animal estabas pensando? ");
        TextField tRespuesta = new TextField();

        contenedor.getChildren().addAll(rUser, tRespuesta);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(15);

        return tRespuesta;
    }
    
    public TextField preguntarPregunta(VBox contenedor, String dataRespuestaUser, String dataNodo){
        Label pUser = new Label("Escribe una pregunta que me permita diferenciar entre\n "+dataRespuestaUser+ " y "+dataNodo+": ");
        TextField tPregunta = new TextField();
        
        contenedor.getChildren().addAll(pUser, tPregunta);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(15);
        
        return tPregunta;
    }
    
    public Boolean asignarLugarNodo(VBox contenedor, String dataRespuestaUser, String pregunta){
        Label lLugar = new Label("Para "+dataRespuestaUser+", la respuesta a la pregunta: “"+pregunta+"”, es si o no?");
        
        HBox opciones = new HBox();
        Label si = new Label("SI"); si.getStyleClass().add("OpSi"); si.setTextFill(Color.web("#333333")); si.setFont(theFontSubtitle);
        Label no = new Label("NO"); no.getStyleClass().add("OpNo"); no.setTextFill(Color.web("#333333")); no.setFont(theFontSubtitle);
        
        
        opciones.getChildren().addAll(si, no);
        opciones.setAlignment(Pos.CENTER);
        opciones.setSpacing(40);
        
        contenedor.getChildren().addAll(lLugar,opciones);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(15);
        
        si.setOnMouseClicked((e)->{
            respt = true;
            contenedor.getChildren().clear();
            //contenedorMejoras.getChildren().clear();
            contenedor.getChildren().add(mostrarAgradecimiento());
        });
        
        no.setOnMouseClicked((e)->{
            respt = false; 
            contenedor.getChildren().clear();
            //contenedorMejoras.getChildren().clear();
            contenedor.getChildren().add(mostrarAgradecimiento());
        });
         
        return respt;
    }
    
    public VBox mostrarAgradecimiento(){
        VBox vB = new VBox();
        Label l = new Label("Gracias por ayudarme a mejorar");
        
        vB.getChildren().addAll(l,volverAJugar());
        vB.setAlignment(Pos.CENTER);
        vB.setSpacing(20);
        
        
        return vB;
    }
    
}
