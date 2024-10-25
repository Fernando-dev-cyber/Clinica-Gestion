package org.example.gestion_hospital;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Citas {

    @FXML
    private Button volver;

    @FXML
    private TextField codigoPacienteTextField;

    @FXML
    private Label nombrePacienteLabel;

    private HashMap<String, String> pacientes = new HashMap<>();


    @FXML
    private void volverAInicio() {
        try {
            // Cargar la escena de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) volver.getScene().getWindow(); // Obtener la ventana actual
            stage.setScene(new Scene(root)); // Cambiar la escena a inicio.fxml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize() {
        // Pruebas de enlace para corrobara la funcion de busqueda del cod_paciente
        pacientes.put("A1B2C3D4", "Juan Perez");
        pacientes.put("Z8Y7X6W5", "Maria López");

    }


    @FXML
    private void buscarPaciente() {
        String codigoIngresado = codigoPacienteTextField.getText();

        // Buscar al paciente por el código en el modelo compartido
        Paciente paciente = PacienteCodigo.buscarPacientePorCodigo(codigoIngresado);

        if (paciente != null) {
            // Si el código existe, muestra el nombre del paciente en el label
            nombrePacienteLabel.setText(paciente.getNombre());
        } else {
            // Si no se encuentra el código, muestra un mensaje de error
            nombrePacienteLabel.setText("Código no encontrado");
        }
    }
}
