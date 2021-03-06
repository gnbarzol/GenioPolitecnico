/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolictenico;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
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
        sc.getStylesheets().add("Recursos/Styles.css");
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
