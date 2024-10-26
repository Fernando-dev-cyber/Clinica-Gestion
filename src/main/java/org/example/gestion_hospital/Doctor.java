package org.example.gestion_hospital;

public class Doctor {

    private String codigo;
    private String nombre;
    private String especialidad;
    private boolean disponible;

    // Constructor, getters y setters
    public Doctor(String codigo, String nombre, String especialidad, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }
}
