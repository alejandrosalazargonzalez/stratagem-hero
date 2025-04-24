package es.alejandrosalazargonzalez.stratagemhero.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import es.alejandrosalazargonzalez.stratagemhero.controller.abstractas.AbstractController;
import es.alejandrosalazargonzalez.stratagemhero.model.Stratagem;
import es.alejandrosalazargonzalez.stratagemhero.model.StratagemServiceModel;

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

    private List<Stratagem> stratagemList;
    private Stratagem currentStratagem;
    private int inputIndex = 0;
    private StratagemServiceModel stratagemService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            stratagemService = new StratagemServiceModel();
            loadStratagems();
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
     * carga las estratagemas de la base de datos en una lista
     */
    private void loadStratagems() {
        stratagemList = stratagemService.obtenerStratagemas();
    }

    /**
     * cambia la estratagema actual por una nueva aleatoria
     */
    private void setNewStratagem() {
        inputIndex = 0;
        if (stratagemList == null || stratagemList.isEmpty()) {
            stratagemList = stratagemService.obtenerStratagemas();
        }

        currentStratagem = stratagemList.get(new Random().nextInt(stratagemList.size()));
        stratagemName.setText(currentStratagem.getName());
        inputProgress.setText("Input: ");

        String iconPath = "/img/icons/" + currentStratagem.getName().toLowerCase().replace(" ", "_") + "_Stratagem_Icon"
                + ".png";
        stratagemIcon.setImage(new Image(getClass().getResource(iconPath).toString()));

        sequenceBox.getChildren().clear();
        for (String dir : currentStratagem.getSequence()) {
            ImageView flecha = new ImageView(
                    new Image(getClass().getResource("/img/arrows/" + dir.toLowerCase() + ".jpg").toString()));
            flecha.setFitWidth(40);
            flecha.setFitHeight(40);
            sequenceBox.getChildren().add(flecha);
        }
    }

    private void handleKeyInput(KeyEvent event) {
        if (currentStratagem == null)
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
}
