package es.alejandrosalazargonzalez.stratagemhero.controller;

import java.util.ArrayList;
import java.util.List;

import es.alejandrosalazargonzalez.stratagemhero.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.stratagemhero.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */
public class LoginController extends AbstractController {

    @FXML
    private ComboBox<String> idiomaComboBox;
    @FXML
    public Text usuarioText;
    @FXML
    private TextField userEmailTextField;
    @FXML
    public Text passwordText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text errorText;
    @FXML
    private Button aceptarButton;
    @FXML
    public Button registrarButton;
    @FXML
    public Button recuperarButton;

    @FXML
    public void initialize() {
        List<String> idiomas = new ArrayList<>();
        idiomas.add("es");
        idiomas.add("en");
        idiomaComboBox.getItems().addAll(idiomas);
        idiomaComboBox.setValue(cargarIdiomaActual());
    }

    /**
     * va a la pantalla de posts despues de comprobar que es correcto
     *
     */
    @FXML
    private void loginAceptarOnClick() {
        if (revisarCamposLogin()) {
            errorText.setText("¡Bienvenidos al mundo de la programación!");
            cambiarPantalla(aceptarButton, "inicio", "app-init");
        }
    }

    /**
     * comprueba los campos de la pagina
     * 
     * @return true/false
     */
    private boolean revisarCamposLogin() {
        if (!comprobarTextField(userEmailTextField)) {
            errorText.setText("Usuario no puede estar vacio");
            return false;
        }
        if (!comprobarTextField(passwordField)) {
            errorText.setText("Contraseña no puede estar vacio");
            return false;
        }
        UsuarioEntity usuario = getUsuarioServiceModel().obtenerUsuarioPorUsuario(userEmailTextField.getText());
        if (usuario == null) {
            errorText.setText("el usuario no existe");
            return false;
        }
        if (!(usuario.getContrasenia().equals(passwordField.getText()))) {
            errorText.setText("error en usuario o contraseña");
            return false;
        }
        return true;
    }

    /**
     * cambia a la pantalla registrar
     */
    @FXML
    private void loginToRegistrarOnClick() {
        cambiarPantalla(registrarButton, "registrar", "app-init");
    }

    /**
     * cambia a la pantalla recuperar
     */
    @FXML
    private void loginToRecuperarOnClick(){
        cambiarPantalla(recuperarButton, "recuperar", "app-init");
    }

    /**
     * cambia el idioma de la web
     */
    @FXML
    private void comboBoxCambiarIdioma() {
        String idioma = idiomaComboBox.getValue().toString();
        setIdioma(idioma);
        cargarIdiomaActual();
    }
}