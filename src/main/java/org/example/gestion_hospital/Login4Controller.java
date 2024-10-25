package org.example.gestion_hospital;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login4Controller {

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField correo;

    @FXML
    private Button ingresar;




    // Método que maneja la acción del botón de Ingresar
    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String correoIngresado = correo.getText();
        String dniIngresado = contraseña.getText();

        // Validar el correo y la contraseña desde la base de datos
        if (validarCredenciales(correoIngresado, dniIngresado)) {
            try {
                // Cargar el archivo FXML del dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gestion_hospital/inicio.fxml"));
                BorderPane dashboard = loader.load();

                // Obtener la ventana actual
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Establecer la nueva escena en el stage
                Scene scene = new Scene(dashboard);
                stage.setScene(scene);


                // Cargar el FXML que queremos que se muestre primero en el center
                FXMLLoader centerLoader = new FXMLLoader(getClass().getResource("/org/example/gestion_hospital/principal.fxml"));
                Parent centerContent = centerLoader.load(); // Carga el FXML

                // Establecer el contenido en el center del BorderPane
                dashboard.setCenter(centerContent);


                // Mostrar la ventana actualizada
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Mostrar un mensaje de alerta si el correo o la contraseña son incorrectos
            mostrarAlerta("Error de autenticación", "El correo o la contraseña son incorrectos. Inténtalo de nuevo.");
        }
    }



    // Método para validar las credenciales del usuario desde la base de datos
    private boolean validarCredenciales(String correo, String dni) {
        boolean esValido = false;

        String url = "jdbc:mysql://localhost:3306/riego";
        String user = "root";
        String password = "";

        // Conexión a la base de datos
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM cliente WHERE Correo = ? AND DNI = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, correo);
            statement.setString(2, dni);

            ResultSet resultSet = statement.executeQuery();

            // Si se encuentra un registro, las credenciales son válidas
            if (resultSet.next()) {
                esValido = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return esValido;
    }


    // Método para mostrar una alerta
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
