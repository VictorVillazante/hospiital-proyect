package com.example.monolitoproyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="hojas_traspaso")
@Entity
public class HojaTraspaso {
    @Id
    @Column
    int id_hoja_traspaso;
    @Column
    int id_consultas;
    @Column
    String examen_clinico;
    @Column
    String diagnostico_presuntivo;
    public HojaTraspaso(int id_hoja_traspaso, int id_consultas, String examen_clinico, String diagnostico_presuntivo) {
        this.id_hoja_traspaso = id_hoja_traspaso;
        this.id_consultas = id_consultas;
        this.examen_clinico = examen_clinico;
        this.diagnostico_presuntivo = diagnostico_presuntivo;
    }
    public HojaTraspaso() {
    }
    public int getId_hoja_traspaso() {
        return id_hoja_traspaso;
    }
    public void setId_hoja_traspaso(int id_hoja_traspaso) {
        this.id_hoja_traspaso = id_hoja_traspaso;
    }
    public int getId_consultas() {
        return id_consultas;
    }
    public void setId_consultas(int id_consultas) {
        this.id_consultas = id_consultas;
    }
    public String getExamen_clinico() {
        return examen_clinico;
    }
    public void setExamen_clinico(String examen_clinico) {
        this.examen_clinico = examen_clinico;
    }
    public String getDiagnostico_presuntivo() {
        return diagnostico_presuntivo;
    }
    public void setDiagnostico_presuntivo(String diagnostico_presuntivo) {
        this.diagnostico_presuntivo = diagnostico_presuntivo;
    }
    @Override
    public String toString() {
        return "HojaTraspaso [id_hoja_traspaso=" + id_hoja_traspaso + ", id_consultas=" + id_consultas
                + ", examen_clinico=" + examen_clinico + ", diagnostico_presuntivo=" + diagnostico_presuntivo + "]";
    }
   
}
