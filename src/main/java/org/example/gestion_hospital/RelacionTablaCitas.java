package org.example.gestion_hospital;

import java.time.LocalDate;

public class RelacionTablaCitas {

    private String codPaciente;
    private String namePaciente;
    private String codDoctor;
    private String nameDoctor;
    private LocalDate cita;
    private String codDoc;

    // Constructor y getters/setters
    public RelacionTablaCitas(String codPaciente, String namePaciente, String codDoctor, String nameDoctor, LocalDate cita, String codDoc) {
        this.codPaciente = codPaciente;
        this.namePaciente = namePaciente;
        this.codDoctor = codDoctor;
        this.nameDoctor = nameDoctor;
        this.cita = cita;
        this.codDoc = codDoc;
    }

    // Getters y Setters
    public String getCodPaciente() { return codPaciente; }
    public String getNamePaciente() { return namePaciente; }
    public String getCodDoctor() { return codDoctor; }
    public String getNameDoctor() { return nameDoctor; }
    public LocalDate getCita() { return cita; }
    public String getCodDoc() { return codDoc; }
}
