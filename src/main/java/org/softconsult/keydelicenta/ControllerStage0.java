package org.softconsult.keydelicenta;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Stage0 {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
