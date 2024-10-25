package org.example.gestion_hospital;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class Inicio {

    @FXML
    private Circle circle;

    @FXML
    private ImageView user;

    @FXML
    private Button citas;

    @FXML
    private Button clientes;

    @FXML
    private Button receta;


    public void initialize() {
        // Configura el evento de clic en el botón citas
        citas.setOnAction(event -> handleCitasClick());
        clientes.setOnAction(event -> handleClientesClick());
        receta.setOnAction(event -> handleRecetaClick());
    }


    private void handleCitasClick() {
        try {
            // Carga el archivo FXML de citas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("citas.fxml"));
            AnchorPane citasPane = loader.load();

            // Obtiene el escenario actual y cambia la escena
            Stage stage = (Stage) citas.getScene().getWindow();
            Scene scene = new Scene(citasPane);
            stage.setScene(scene);
            stage.setTitle("Citas");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientesClick() {
        try {
            // Carga el archivo FXML de citas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientes.fxml"));
            AnchorPane citasPane = loader.load();

            // Obtiene el escenario actual y cambia la escena
            Stage stage = (Stage) clientes.getScene().getWindow();
            Scene scene = new Scene(citasPane);
            stage.setScene(scene);
            stage.setTitle("Clientes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRecetaClick() {
        try {
            // Carga el archivo FXML de citas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("receta.fxml"));
            AnchorPane citasPane = loader.load();

            // Obtiene el escenario actual y cambia la escena
            Stage stage = (Stage) receta.getScene().getWindow();
            Scene scene = new Scene(citasPane);
            stage.setScene(scene);
            stage.setTitle("Receta");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


