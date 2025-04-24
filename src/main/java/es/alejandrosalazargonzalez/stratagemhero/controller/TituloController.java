package es.alejandrosalazargonzalez.stratagemhero.controller;


import es.alejandrosalazargonzalez.stratagemhero.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *   @author alejandrosalazargonzalez
 *   @version 1.0.0
 */
public class TituloController extends AbstractController {
    
    @FXML Button entrarButton;

    /**
     * va a la pantalla de login
     */
    @FXML
    void tituloToLoginClick(){
        cambiarPantalla(entrarButton, "app-init", "titulo");
    }
}
