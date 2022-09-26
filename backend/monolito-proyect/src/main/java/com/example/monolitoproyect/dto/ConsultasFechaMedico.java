package com.example.monolitoproyect.dto;

import lombok.Data;

@Data
public class ConsultasFechaMedico {
    private Object id;
    private Object especialidad;
    private Object paciente;
    private Object inicio;
    private Object fin;
    private Object consultorio;
    public ConsultasFechaMedico(Object id, Object especialidad, Object paciente, Object inicio, Object fin,
            Object consultorio) {
        this.id = id;
        this.especialidad = especialidad;
        this.paciente = paciente;
        this.inicio = inicio;
        this.fin = fin;
        this.consultorio = consultorio;
    }
}
