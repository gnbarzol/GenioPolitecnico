/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolictenico;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author GaryBarzola
 */
public class GenioPolictenico extends Application {
    protected static Scene sc;
    Adivinador adivinador;
    
    @Override
    public void start(Stage primaryStage) {
        adivinador = new Adivinador();
        sc = new Scene(adivinador.getRoot());
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Scene getScene() {
        return sc;
    }
}
