package org.example.gestion_hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements InterDoctorDAO{

    private Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Doctor> obtenerDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        String query = "SELECT * FROM doctores"; // Elegimos de la tabla de doctores

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String codigo = rs.getString("cod_doctor");
                String nombre = rs.getString("nombre_doctor");
                String especialidad = rs.getString("especialidad");
                boolean disponible = rs.getBoolean("disponible");
                doctores.add(new Doctor(codigo, nombre, especialidad, disponible));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctores;
    }

    @Override
    public Doctor buscarDoctorPorCodigo(String codigo) {
        String query = "SELECT * FROM doctores WHERE cod_doctor = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre_doctor");
                String especialidad = rs.getString("especialidad");
                boolean disponible = rs.getBoolean("disponible");
                return new Doctor(codigo, nombre, especialidad, disponible);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
