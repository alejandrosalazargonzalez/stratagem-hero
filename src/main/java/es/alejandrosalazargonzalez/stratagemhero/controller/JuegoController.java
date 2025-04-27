package es.alejandrosalazargonzalez.stratagemhero.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import es.alejandrosalazargonzalez.stratagemhero.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.stratagemhero.model.Stratagem;
import es.alejandrosalazargonzalez.stratagemhero.model.StratagemServiceModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * @author alejandrosalazargonzalez
 * @version 1.0.0
 */

public class JuegoController extends AbstractController implements Initializable {
    @FXML
    private ImageView stratagemIcon;

    @FXML
    private Label stratagemName;

    @FXML
    private Label inputProgress;

    @FXML
    private HBox sequenceBox;
    @FXML
    private Button atrasButton;
    @FXML
    private Button reiniciarButton;
@FXML
private Label timerLabel;

    private Timeline timer;
    private double timeLeft = 10.0;
    private static final double TIME_BONUS = 2.0;
    private List<Stratagem> stratagemList;
    private Stratagem currentStratagem;
    private int inputIndex = 0;
    private StratagemServiceModel stratagemService;
    private boolean gameOver = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            stratagemService = new StratagemServiceModel();
            loadStratagems();
            setupTimer();
            setNewStratagem();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stratagemIcon.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyInput);
            }
        });
    }

    /**
     * Configura el temporizador para el juego
     */
    private void setupTimer() {
        timer = new Timeline(
            new KeyFrame(Duration.seconds(0.1), e -> {
                timeLeft -= 0.1;
                timerLabel.setText(String.format("%.1f", timeLeft));
                
                if (timeLeft <= 0) {
                    timer.stop();
                    gameOver = true;
                    inputProgress.setText("¡Se acabó el tiempo!");
                }
            })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    /**
     * carga las estratagemas de la base de datos en una lista
     */
    private void loadStratagems() {
        stratagemList = stratagemService.obtenerStratagemas();
    }

    /**
     * cambia la estratagema actual por una nueva aleatoria y suma el tiempo al contador al completarla
     */
    private void setNewStratagem() {
        inputIndex = 0;
        if (stratagemList == null || stratagemList.isEmpty()) {
            stratagemList = stratagemService.obtenerStratagemas();
        }

        currentStratagem = stratagemList.get(new Random().nextInt(stratagemList.size()));
        stratagemName.setText(currentStratagem.getName());
        inputProgress.setText("Input: ");

        timeLeft += TIME_BONUS;
        if (timeLeft > 10.0) {
            timeLeft = 10.0;
            
        }

        timerLabel.setText(String.format("%.1f", timeLeft));
        
        establecerIconos();
    }

    /**
     * pone los iconos de las estratagemas y las flechas en la interfaz
     */
    private void establecerIconos() {
        try {
            String iconName = currentStratagem.getName().replace(" ", "_") + "_Stratagem_Icon.png";
            URL iconUrl = getClass().getResource("/img/icons/" + iconName);
            if (iconUrl != null) {
                stratagemIcon.setImage(new Image(getClass().getResource("/img/icons/" + iconName ).toString()));
            } else {
                System.err.println("No se pudo encontrar el icono: " + iconName.trim());
            }
    
            sequenceBox.getChildren().clear();
    
            for (String dir : currentStratagem.getSequence()) {
                ImageView flecha = new ImageView(
                    new Image(getClass().getResource("/img/arrows/" + dir.toLowerCase() + ".jpg").toString())
                );
                flecha.setFitWidth(40);
                flecha.setFitHeight(40);
                sequenceBox.getChildren().add(flecha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja la entrada del teclado y verifica si la secuencia es correcta
     *
     * @param event el evento de teclado
     */
    private void handleKeyInput(KeyEvent event) {
        if (gameOver || currentStratagem == null)
            return;

        String expected = currentStratagem.getSequence().get(inputIndex);
        if (event.getCode().toString().equals(expected)) {
            inputIndex++;
            inputProgress.setText("Input: " + currentStratagem.getSequence().subList(0, inputIndex));

            if (inputIndex == currentStratagem.getSequence().size()) {
                setNewStratagem();
            }
        } else {
            inputProgress.setText("¡Fallaste! Reiniciando...");
            inputIndex = 0;
        }
    }

    @FXML
    public void juegoToInicio(){
        cambiarPantalla(atrasButton, "inicio","juego");
    }

    /**
     * reinicia el juego si se acaba el tiempo
     */
    @FXML
    public void reiniciarOnClick() {
        if (gameOver) {
            gameOver = false;
            timeLeft = 10.0;
            timerLabel.setText(String.format("%.1f", timeLeft));
            setNewStratagem();
            setupTimer();
        }
    }
}
