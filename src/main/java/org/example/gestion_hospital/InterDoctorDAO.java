package org.example.gestion_hospital;

import java.util.List;

public interface InterDoctorDAO {
    List<Doctor> obtenerDoctores();
    Doctor buscarDoctorPorCodigo(String codigo);
}
