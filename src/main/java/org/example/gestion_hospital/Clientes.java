package org.example.gestion_hospital;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random; // Importamos Random para poder generar el código del paciente

public class Clientes {
    @FXML
    private Button agregar;
    @FXML
    private Button eliminar;
    @FXML
    private Button editar;
    @FXML
    private VBox tarjetasContainer;

    private PacienteTarjetaCliente tarjetaSeleccionada = null;
    private Map<String, PacienteTarjetaCliente> tarjetas = new HashMap<>();

    public void initialize() {


        // Cargar los pacientes guardados solo si no se han agregado antes
        List<Paciente> pacientesGuardados = TarjetaStorage.getInstance().getPacientes();
        for (Paciente paciente : pacientesGuardados) {
            // Verificar si ya existe una tarjeta para este paciente en el mapa
            if (!tarjetas.containsKey(paciente.getDni())) {
                crearTarjetaPaciente(paciente);
            }
        }

        agregar.setOnAction(e -> abrirModalAgregar());

        eliminar.setOnAction(e -> {
            if (tarjetaSeleccionada != null) {
                // Remover la tarjeta seleccionada del contenedor y del mapa de tarjetas
                tarjetasContainer.getChildren().remove(tarjetaSeleccionada.getTarjeta());
                tarjetas.remove(tarjetaSeleccionada.getPaciente().getDni());
                // Remover la referencia del paciente en PacienteCodigo
                PacienteCodigo.removerPaciente(tarjetaSeleccionada.getCodigo());
                TarjetaStorage.getInstance().eliminarPaciente(tarjetaSeleccionada.getPaciente());
                tarjetaSeleccionada = null;
            }
        });

        editar.setOnAction(e -> {
            if (tarjetaSeleccionada != null) {
                abrirModalEditar(tarjetaSeleccionada.getPaciente());
            }
        });
    }

    private void abrirModalAgregar() {
        ModalAgregar modal = new ModalAgregar(this::crearTarjetaPaciente);
        modal.mostrar();
    }

    private void crearTarjetaPaciente(Paciente paciente) {
        // Generar un código único de 8 caracteres para el paciente
        String codigo = Cod_PacienteGenerar.generarCodigoPaciente();
        PacienteTarjetaCliente tarjeta = new PacienteTarjetaCliente(paciente, codigo);

        // Agregar la tarjeta al mapa y al contenedor visual
        tarjetas.put(paciente.getDni(), tarjeta);
        tarjetasContainer.getChildren().add(tarjeta.getTarjeta());

        // Guardar el código y el paciente en PacienteCodigo para acceso desde otros apartados
        PacienteCodigo.agregarPaciente(codigo, paciente);

        // Configurar evento de selección de la tarjeta
        tarjeta.getTarjeta().setOnMouseClicked(e -> seleccionarTarjeta(tarjeta));

        // Guardado solo si el paciente no está ya en el almacenamiento
        if (!TarjetaStorage.getInstance().getPacientes().contains(paciente)) {
            TarjetaStorage.getInstance().agregarPaciente(paciente);
        }

    }

    private void seleccionarTarjeta(PacienteTarjetaCliente tarjeta) {
        if (tarjetaSeleccionada != null) {
            tarjetaSeleccionada.getTarjeta().getStyleClass().remove("tarjeta-seleccionada");
        }
        tarjetaSeleccionada = tarjeta;
        tarjetaSeleccionada.getTarjeta().getStyleClass().add("tarjeta-seleccionada");
    }



    private void abrirModalEditar(Paciente paciente) {
        ModalAgregar modal = new ModalAgregar(pacienteActualizado -> {
            tarjetaSeleccionada.actualizarTarjeta(pacienteActualizado);
        });
        modal.mostrar(paciente);
    }

    @FXML
    private void volverAInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) agregar.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
