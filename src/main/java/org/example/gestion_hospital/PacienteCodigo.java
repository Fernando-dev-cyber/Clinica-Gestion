package org.example.gestion_hospital;

import java.util.HashMap;

public class PacienteCodigo {

    private static HashMap<String, Paciente> pacientes = new HashMap<>();

    public static void agregarPaciente(String codigo, Paciente paciente) {
        pacientes.put(codigo, paciente);
    }

    public static Paciente buscarPacientePorCodigo(String codigo) {
        return pacientes.get(codigo);
    }

    public static void removerPaciente(String codigo) {
        pacientes.remove(codigo);
    }


}
