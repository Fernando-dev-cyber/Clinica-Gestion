package org.example.gestion_hospital;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class Citas {

    @FXML
    private Button volver;

    @FXML
    private TextField codigoPacienteTextField;

    @FXML
    private Label nombrePacienteLabel;

    @FXML
    private ComboBox<Doctor> DoctoresComboBox;

    @FXML
    private Label nombreDoctorLabel;

    @FXML
    private DatePicker citaDatePicker; // DatePicker para la fecha de la cita

    @FXML
    private Button relacionarButton;

    @FXML
    private Button eliminarFilaButton;

    @FXML
    private TableView<RelacionTablaCitas> TablaRelacion;

    @FXML
    private TableColumn<RelacionTablaCitas, String> colCodPaciente;
    @FXML
    private TableColumn<RelacionTablaCitas, String> colNamePaciente;
    @FXML
    private TableColumn<RelacionTablaCitas, String> colCodDoctor;
    @FXML
    private TableColumn<RelacionTablaCitas, String> colNameDoctor;
    @FXML
    private TableColumn<RelacionTablaCitas, LocalDate> colCita;
    @FXML
    private TableColumn<RelacionTablaCitas, String> colCodDoc;


    private InterDoctorDAO doctorDAO; // DAO para doctores




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
        // Inicializar conexión a la base de datos y DAO

        // Inicializar conexión a la base de datos y DAO
        String url = "jdbc:mysql://localhost:3306/gestion_hospital";
        String user = "root";
        String password = "";


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            doctorDAO = new DoctorDAO(connection);
            cargarDoctores();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores de conexión
        }


        // Configuración de columnas de la tabla
        colCodPaciente.setCellValueFactory(new PropertyValueFactory<>("codPaciente"));
        colNamePaciente.setCellValueFactory(new PropertyValueFactory<>("namePaciente"));
        colCodDoctor.setCellValueFactory(new PropertyValueFactory<>("codDoctor"));
        colNameDoctor.setCellValueFactory(new PropertyValueFactory<>("nameDoctor"));
        colCita.setCellValueFactory(new PropertyValueFactory<>("cita"));
        colCodDoc.setCellValueFactory(new PropertyValueFactory<>("codDoc"));

        relacionarButton.setOnAction(event -> relacionarDatos());
    }

    @FXML
    private void eliminarFila() {
        // Obtener la fila seleccionada
        RelacionTablaCitas seleccionada = TablaRelacion.getSelectionModel().getSelectedItem();

        // Verificar que haya una fila seleccionada
        if (seleccionada != null) {
            // Remover la fila de la tabla
            TablaRelacion.getItems().remove(seleccionada);

        } else {
            System.out.println("Seleccione una fila para eliminar.");
        }
    }

    private void relacionarDatos() {
        // Obtener datos del paciente y doctor
        String codPaciente = codigoPacienteTextField.getText();
        String namePaciente = nombrePacienteLabel.getText();
        Doctor doctorSeleccionado = DoctoresComboBox.getSelectionModel().getSelectedItem();

        if (codPaciente.isEmpty() || namePaciente.isEmpty() || doctorSeleccionado == null || citaDatePicker.getValue() == null) {
            // Mostrar mensaje de error si faltan datos
            System.out.println("Por favor, complete todos los datos antes de relacionar.");
            return;
        }

        String codDoctor = doctorSeleccionado.getCodigo();
        String nameDoctor = doctorSeleccionado.getNombre();
        LocalDate cita = citaDatePicker.getValue();
        String codDoc = generarCodigoUnico(); // Generar código único para Cod_doc

        // Crear una nueva instancia de Relacion y agregarla a la tabla
        RelacionTablaCitas nuevaRelacion = new RelacionTablaCitas(codPaciente, namePaciente, codDoctor, nameDoctor, cita, codDoc);
        TablaRelacion.getItems().add(nuevaRelacion);
    }

    // Método para generar un código único para Cod_doc
    private String generarCodigoUnico() {
        // Codigo UUID para un código único
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }





    private void cargarDoctores(){
    // Uso de doctorDAO para obtener la lista de doctores
        List<Doctor> doctoresList = doctorDAO.obtenerDoctores();
        ObservableList<Doctor> doctores = FXCollections.observableArrayList(doctoresList);
        DoctoresComboBox.setItems(doctores);


        // Mostrar el nombre del doctor
        DoctoresComboBox.setConverter(new StringConverter<Doctor>() {
            @Override
            public String toString(Doctor doctor) {
                return doctor != null ? doctor.getNombre() : "";
            }

            @Override
            public Doctor fromString(String string) {
                return null; // X_x
            }
        });

        // Agregacion de un evento listener para mostrar el codigo del doctor en el Label
        DoctoresComboBox.setOnAction(event -> {
            Doctor selectedDoctor = DoctoresComboBox.getSelectionModel().getSelectedItem();
            if (selectedDoctor != null) {
                nombreDoctorLabel.setText(selectedDoctor.getCodigo()); // Muestra el codigo del doctor en el Label
            }
        });

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
