package org.example.gestion_hospital;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModalAgregar {

    private Consumer<Paciente> callback;

    public ModalAgregar(Consumer<Paciente> callback) {
        this.callback = callback;
    }

    public void mostrar() {
        mostrar(null); // Mostrar modal sin paciente (para creación)
    }

    public void mostrar(Paciente paciente) {
        // Crear un nuevo Stage para el modal
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle(paciente == null ? "Agregar Información del Paciente" : "Editar Información del Paciente");

        // Crear campos de texto
        TextField nombreField = new TextField();
        TextField dniField = new TextField();
        TextField alturaField = new TextField();
        TextField pesoField = new TextField();

        // Si estamos editando, cargar los datos del paciente
        if (paciente != null) {
            nombreField.setText(paciente.getNombre());
            dniField.setText(paciente.getDni());
            alturaField.setText(paciente.getAltura());
            pesoField.setText(paciente.getPeso());
        }

        // Crear botones
        Button aceptarButton = new Button("Aceptar");
        Button cancelarButton = new Button("Cancelar");

        aceptarButton.setOnAction(e -> {
            // Enviar los datos al callback como un objeto Paciente
            callback.accept(
                    new Paciente(
                            nombreField.getText(),
                            dniField.getText(),
                            alturaField.getText(),
                            pesoField.getText()
                    )
            );
            modalStage.close(); // Cerrar el modal al aceptar
        });

        cancelarButton.setOnAction(e -> modalStage.close()); // Cerrar el modal al cancelar

        // Crear layout y añadir los controles
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Nombre:"), 0, 0);
        gridPane.add(nombreField, 1, 0);
        gridPane.add(new Label("DNI:"), 0, 1);
        gridPane.add(dniField, 1, 1);
        gridPane.add(new Label("Altura (cm):"), 0, 2);
        gridPane.add(alturaField, 1, 2);
        gridPane.add(new Label("Peso (kg):"), 0, 3);
        gridPane.add(pesoField, 1, 3);

        // HBox para los botones
        HBox buttonBox = new HBox(10, aceptarButton, cancelarButton);
        gridPane.add(buttonBox, 1, 4);

        // Crear la escena y mostrarla
        Scene scene = new Scene(gridPane, 300, 200);
        modalStage.setScene(scene);
        modalStage.showAndWait(); // Mostrar el modal y esperar a que se cierre
    }
}
