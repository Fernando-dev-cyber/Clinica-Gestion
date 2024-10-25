package org.example.gestion_hospital;

public class Paciente {

    private String nombre;
    private String dni;
    private String altura;
    private String peso;

    public Paciente(String nombre, String dni, String altura, String peso) {
        this.nombre = nombre;
        this.dni = dni;
        this.altura = altura;
        this.peso = peso;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
