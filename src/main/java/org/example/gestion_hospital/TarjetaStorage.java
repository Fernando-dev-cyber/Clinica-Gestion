package org.example.gestion_hospital;

import java.util.ArrayList;
import java.util.List;

public class TarjetaStorage {
    private static TarjetaStorage instance;
    private List<Paciente> pacientes;

    private TarjetaStorage() {
        pacientes = new ArrayList<>();
    }

    public static TarjetaStorage getInstance() {
        if (instance == null) {
            instance = new TarjetaStorage();
        }
        return instance;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(pacientes);
    }

    public void eliminarPaciente(Paciente paciente) {
        pacientes.remove(paciente);
    }
}
