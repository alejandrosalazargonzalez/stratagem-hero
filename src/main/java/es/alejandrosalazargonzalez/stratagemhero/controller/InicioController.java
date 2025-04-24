package es.alejandrosalazargonzalez.stratagemhero.controller;

import es.alejandrosalazargonzalez.stratagemhero.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class InicioController extends AbstractController {

    @FXML
    public Text userText;
    @FXML
    private TextField userTextField;
    @FXML
    public ComboBox<String> dificultadBox;
    @FXML
    private Text minasText;
    @FXML
    private TextField nivelTextField;
    @FXML
    private Button regresarButton;
    @FXML
    public Button jugarButton;
    @FXML
    public Text errorText;
    @FXML
    public Text filasText;
    @FXML
    public Text columnasText;
    @FXML
    private TextField filasField;
    @FXML
    private TextField columnasField;
    @FXML
    private TextField minasField;


    /**
     * Cambia la pantalla a la partida y configura la dificultad
     * según lo que haya seleccionado el usuario del usuario.
     */
    @FXML
    private void inicioToPartidaOnClick() {
        cambiarPantalla(jugarButton, "juego", "app-init");
    }

    /**
     * cambia a la pantalla login
     */
    @FXML
    private void inicioToLoginOnClick() {
        cambiarPantalla(regresarButton, "app-init", "app-init");
    }

}
