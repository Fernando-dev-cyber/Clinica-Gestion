package org.example.gestion_hospital;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PacienteTarjetaCliente {

    private VBox tarjeta;
    private Paciente paciente;
    private String codigo;

    public PacienteTarjetaCliente(Paciente paciente, String codigo) {
        this.paciente = paciente;
        this.codigo = codigo;
        this.tarjeta = crearTarjetaPaciente();
    }

    private VBox crearTarjetaPaciente() {
        VBox tarjeta = new VBox();
        tarjeta.setSpacing(5);
        tarjeta.getStyleClass().add("tarjeta-paciente"); // Añadimos una clase css

        Label labelCodigo = new Label("Código: " + codigo);
        labelCodigo.getStyleClass().add("label-codigo"); // Añadimos una clase css

        Label labelNombre = new Label("Nombre: " + paciente.getNombre());
        labelNombre.getStyleClass().add("label-nombre"); // Añadimos una clase css

        Label labelDNI = new Label("DNI: " + paciente.getDni());
        labelDNI.getStyleClass().add("label-dni");

        Label labelAlturaPeso = new Label("Altura: " + paciente.getAltura() + " cm, Peso: " + paciente.getPeso() + " kg");
        labelAlturaPeso.getStyleClass().add("label-altura-peso"); // Añadimos una clase css

        tarjeta.getChildren().addAll(labelCodigo, labelNombre, labelDNI, labelAlturaPeso);
        return tarjeta;
    }

    public VBox getTarjeta() {
        return tarjeta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void actualizarTarjeta(Paciente pacienteActualizado) {
        // Método para actualizar los datos en la tarjeta
        this.paciente = pacienteActualizado;
        Label labelNombre = (Label) tarjeta.getChildren().get(1);
        Label labelDNI = (Label) tarjeta.getChildren().get(2);
        Label labelAlturaPeso = (Label) tarjeta.getChildren().get(3);

        labelNombre.setText("Nombre: " + paciente.getNombre());
        labelDNI.setText("DNI: " + paciente.getDni());
        labelAlturaPeso.setText("Altura: " + paciente.getAltura() + " cm, Peso: " + paciente.getPeso() + " kg");
    }
}
