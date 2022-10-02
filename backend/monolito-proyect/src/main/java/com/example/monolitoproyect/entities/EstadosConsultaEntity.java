package com.example.monolitoproyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.firebase.database.annotations.NotNull;

@Table(name = "estados_consultas")
@Entity
public class EstadosConsultaEntity {
    @Id
    @Column
    @NotNull
    private int id_estado_consulta;
    @NotNull
    @Column
    private String nombre;
    public EstadosConsultaEntity(int id_estado_consulta, String nombre) {
        this.id_estado_consulta = id_estado_consulta;
        this.nombre = nombre;
    }
    public EstadosConsultaEntity() {
    }
    public int getId_estado_consulta() {
        return id_estado_consulta;
    }
    public void setId_estado_consulta(int id_estado_consulta) {
        this.id_estado_consulta = id_estado_consulta;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
