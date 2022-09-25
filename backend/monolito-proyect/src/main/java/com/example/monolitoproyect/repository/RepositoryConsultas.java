package com.example.monolitoproyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.monolitoproyect.ConsultasEntity;

public interface RepositoryConsultas extends PagingAndSortingRepository<ConsultasEntity,Integer>{
    @Query(value="SELECT c.id_consultas,e.nombre as especialidad,m.nombre as medico,p.nombre as paciente,h.inicio,h.fin,c.fecha,ec.nombre as estado,con.nombre as consultorio "+
    "FROM consultas c "+
    "JOIN atenciones a ON c.id_atencion=a.id_atencion "+
    "JOIN pacientes p ON c.id_paciente=p.id_paciente "+
    "JOIN horarios h ON c.id_horario=h.id_horario "+
    "JOIN estados_consultas ec ON c.id_estado_consulta=ec.id_estado_consulta "+
    "JOIN consultorios con ON c.id_consultorio=con.id_consultorio "+
    "JOIN medicos m ON a.id_medico=m.id_medico "+
    "JOIN especialidades e ON a.id_especialidades=e.id_especialidades "+
    "WHERE p.id_paciente= ?1 and ec.id_estado_consulta=1",nativeQuery=true)
    List<Object> listarReservasPacienteId(int id);
}