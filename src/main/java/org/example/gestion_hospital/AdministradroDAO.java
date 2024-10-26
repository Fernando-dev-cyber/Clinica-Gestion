package org.example.gestion_hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministradroDAO implements InterAdministradorDAO{

    private final String url = "jdbc:mysql://localhost:3306/gestion_hospital";
    private final String user = "root";
    private final String password = "";

    @Override
    public boolean validarCredenciales(String correo, String dni) {
        boolean esValido = false;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM administradores WHERE correo = ? AND dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, correo);
            statement.setString(2, dni);

            ResultSet resultSet = statement.executeQuery();
            esValido = resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return esValido;
    }

}
