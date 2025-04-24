package es.alejandrosalazargonzalez.stratagemhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class PrincipalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("titulo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 730, 477);
        stage.setTitle("Bucaminas!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}