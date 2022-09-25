package com.example.monolitoproyect.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jline.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.monolitoproyect.ConsultasEntity;
import com.example.monolitoproyect.dto.ConsultasPacienteId;
import com.example.monolitoproyect.repository.RepositoryConsultas;
import com.example.monolitoproyect.repository.RepositoryConsultasJPA;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/pacientes")
public class PacientesController {
    @Autowired
    private RepositoryConsultas repositoryConsultas;

    @Autowired
    private RepositoryConsultasJPA repositoryConsultasJPA;

    @GetMapping(path = "/reservas/{id}")
    public @ResponseBody
    List<ConsultasPacienteId> getConsultasPacienteId(@PathVariable("id")Integer id) {
        // This returns a JSON or XML with the users
        // ArrayList listaVentasProductosDetalle= cersinjpa.getVentasDetalle();
        // for (O producto: listaVentasProductosDetalle){
        //     VentasAux pva=(VentasAux)producto;
        // }
        ArrayList<ConsultasPacienteId> listaConsultas=new ArrayList();
        List<Object>lista= repositoryConsultas.listarReservasPacienteId(id);
        for(int i=0;i<lista.size();i++){
            Object[] l=(Object[]) lista.get(i);
            ConsultasPacienteId cpid=new ConsultasPacienteId(l[0],l[1],l[2], l[3], l[4],l[5], l[6], l[7],l[8]);
            listaConsultas.add(cpid);
        }
        
        return listaConsultas;
    }

    @DeleteMapping("/consulta/cancelada/{id}")
    public @ResponseBody ConsultasEntity delete(@PathVariable("id")int id){
        Log.info("Eliminando "+id);
        Optional<ConsultasEntity> ca= repositoryConsultasJPA.findById(id);
        Log.info("Ant "+ca);
        ConsultasEntity cn=new ConsultasEntity();
        cn.setFecha(ca.get().getFecha());
        cn.setId_atencion(ca.get().getId_atencion());
        cn.setId_consultas(ca.get().getId_consultas());
        cn.setId_consultorio(ca.get().getId_consultorio());
        cn.setId_estado_consulta(5);
        cn.setId_horario(ca.get().getId_horario());
        cn.setId_paciente(ca.get().getId_paciente());
        
        return repositoryConsultasJPA.save(cn);
    }
}
