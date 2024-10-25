package org.example.gestion_hospital;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Receta {

    @FXML
    private Button volver;

    @FXML
    private void volverAInicio() {
        try {
            // Cargar la escena de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml")); //elegimos el archivo a cambiar
            Parent root = loader.load();
            Stage stage = (Stage) volver.getScene().getWindow(); // Obtener la ventana actual
            stage.setScene(new Scene(root)); // Cambiar la escena a inicio.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
