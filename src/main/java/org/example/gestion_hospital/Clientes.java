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
import java.util.Random; // Importamos Random para poder generar el código del paciente

public class Clientes {

    @FXML
    private Button agregar; //boton para agregar la tarjeta al Vbox

    @FXML
    private Button eliminar; // Botón para eliminar la tarjeta seleccionada

    @FXML
    private Button editar; // Botón para editar la tarjeta seleccionada

    @FXML
    private VBox tarjetasContainer; // Este es el VBox donde se almacenaran las tarjetas


    private VBox tarjetaSeleccionada = null; // Variable para guardar la tarjeta seleccionada
    private Paciente pacienteSeleccionado = null; // Paciente relacionado a la tarjeta seleccionada


    @FXML
    private void volverAInicio() {
        try {
            // Cargar la escena de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) agregar.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void initialize() {
        agregar.setOnAction(e -> abrirModalAgregar());


        eliminar.setOnAction(e -> {
            if (tarjetaSeleccionada != null) {
                tarjetasContainer.getChildren().remove(tarjetaSeleccionada); // Eliminar la tarjeta seleccionada
                tarjetaSeleccionada = null; // Restablecer la selección
            }
        });


        editar.setOnAction(e -> {
            if (pacienteSeleccionado != null) {
                abrirModalEditar(pacienteSeleccionado); // Abrir modal para editar el paciente
            }
        });
    }



    private void abrirModalAgregar() {
        ModalAgregar modal = new ModalAgregar(this::crearTarjetaPaciente);
        modal.mostrar();
    }

    //Metodo para poder crear/añadir la tarjeta al contenderor
    private void crearTarjetaPaciente(Paciente paciente) {
        VBox tarjeta = new VBox();
        tarjeta.setSpacing(5);
        tarjeta.getStyleClass().add("tarjeta-paciente"); // Añadimos una clase css

        // Generarmos el codigo del paciente
        String codigo = generarCodigoPaciente();
        Label labelCodigo = new Label("Código: " + codigo);
        labelCodigo.getStyleClass().add("label-codigo"); // Añadimos una clase css

        Label labelNombre = new Label("Nombre: " + paciente.getNombre());
        labelNombre.getStyleClass().add("label-nombre"); // Añadimos una clase css

        Label labelDNI = new Label("DNI: " + paciente.getDni());
        labelDNI.getStyleClass().add("label-dni");

        Label labelAlturaPeso = new Label("Altura: " + paciente.getAltura() + " cm, Peso: " + paciente.getPeso() + " kg");
        labelAlturaPeso.getStyleClass().add("label-altura-peso"); // Añadimos una clase css

        tarjeta.getChildren().addAll(labelNombre, labelDNI, labelAlturaPeso, labelCodigo);

        // Añadimos el paciente y el cod_paciente al modelo compartido con Citas.java
        PacienteCodigo.agregarPaciente(codigo, paciente);

        // Evento de click para seleccionar cualquier tarjeta
        tarjeta.setOnMouseClicked(e -> seleccionarTarjeta(tarjeta, paciente)); // Aquí pasamos el paciente

        // Añadir el VBox con los datos al contenedor principal
        tarjetasContainer.getChildren().add(tarjeta);
    }


    private void seleccionarTarjeta(VBox tarjeta, Paciente paciente) {
        // Si ya hay una tarjeta seleccionada, quitarle el estilo de seleccionada
        if (tarjetaSeleccionada != null) {
            tarjetaSeleccionada.getStyleClass().remove("tarjeta-seleccionada");
        }

        // Aplicar el estilo de seleccionada a la tarjeta actual
        tarjetaSeleccionada = tarjeta;
        tarjetaSeleccionada.getStyleClass().add("tarjeta-seleccionada");

        // Guardar la referencia del paciente asociado
        pacienteSeleccionado = paciente;
    }


    private void abrirModalEditar(Paciente paciente) {
        // Crear un modal para editar (referencia al modal-agregar), /cambiando los datos del paciente
        ModalAgregar modal = new ModalAgregar(p -> {
            paciente.setNombre(p.getNombre());
            paciente.setDni(p.getDni());
            paciente.setAltura(p.getAltura());
            paciente.setPeso(p.getPeso());

            // Actualizar los datos en la tarjeta seleccionada
            Label labelNombre = (Label) tarjetaSeleccionada.getChildren().get(0);
            Label labelDNI = (Label) tarjetaSeleccionada.getChildren().get(1);
            Label labelAlturaPeso = (Label) tarjetaSeleccionada.getChildren().get(2);

            labelNombre.setText("Nombre: " + paciente.getNombre());
            labelDNI.setText("DNI: " + paciente.getDni());
            labelAlturaPeso.setText("Altura: " + paciente.getAltura() + " cm, Peso: " + paciente.getPeso() + " kg");
        });

        // Mostrar el modal con los datos del paciente actual
        modal.mostrar(paciente);
    }


    private String generarCodigoPaciente() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(index));
        }
        return codigo.toString();
    }

}
