package com.example.monolitoproyect.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.monolitoproyect.dto.AdmTraspasosDto;
import com.example.monolitoproyect.dto.ConsultasFechaDto;
import com.example.monolitoproyect.entities.ConsultasEntity;
import com.example.monolitoproyect.entities.HojaTraspasoEntity;
import com.example.monolitoproyect.entities.PacienteEntity;
import com.example.monolitoproyect.repository.RepositoryConsultas;
import com.example.monolitoproyect.repository.RepositoryConsultasJPA;
import com.example.monolitoproyect.repository.RepositoryHojaTraspaso;
import com.example.monolitoproyect.repository.RepositoryHojaTraspasoJPA;
import com.example.monolitoproyect.repository.RepositoryPacientesJPA;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/adm")
public class AdmController {
    @Autowired
    RepositoryPacientesJPA repositoryPacientesJPA;
    @Autowired
    RepositoryConsultas repositoryConsultas;
    @Autowired
    RepositoryHojaTraspaso repositoryHojaTraspaso;
    @Autowired
    RepositoryHojaTraspasoJPA repositoryHojaTraspasoJPA;

    @GetMapping(value="/hojas-traspaso/{fecha}")
    public @ResponseBody List<Object> obtenerHojasTraspaso(@PathVariable("fecha") String fecha){
        ArrayList<Object> listaTraspasos=new ArrayList();
        List<Object>lista= repositoryHojaTraspaso.buscarHojasTraspasoFecha(fecha);
        for(int i=0;i<lista.size();i++){
            Object[] l=(Object[]) lista.get(i);
            AdmTraspasosDto cpid=new AdmTraspasosDto(l[0],l[1],l[2], l[3],l[4],l[5]);
            listaTraspasos.add(cpid);
        }
        return listaTraspasos;
    }

    @GetMapping(value = "pacientes")
    public @ResponseBody List<PacienteEntity> obtenerEstadosConsulta(){
        return repositoryPacientesJPA.findAll();
    }

    @PutMapping(value = "/hojas-traspaso/habilitar/{id}")
    public @ResponseBody HojaTraspasoEntity habilitarTraspaso(@PathVariable("id")Integer id_trapaso){
        HojaTraspasoEntity hte=repositoryHojaTraspasoJPA.findById(id_trapaso).get();
        hte.setTraspaso_aceptado_adm(1);
        repositoryHojaTraspasoJPA.save(hte);
        return hte;
    }
    @PutMapping(value = "/hojas-traspaso/deshabilitar/{id}")
    public @ResponseBody HojaTraspasoEntity deshabilitarTraspaso(@PathVariable("id")Integer id_trapaso){
        HojaTraspasoEntity hte=repositoryHojaTraspasoJPA.findById(id_trapaso).get();
        hte.setTraspaso_aceptado_adm(0);
        repositoryHojaTraspasoJPA.save(hte);
        return hte;
    }
    @GetMapping(value = "/consultas-dia")
    public @ResponseBody List<Object> controllerMethod(@RequestParam Map<String, String> customQuery) {

        System.out.println("Fecha" + customQuery.get("fecha"));

       
        ArrayList<Object> listaConsultas=new ArrayList();
        List<Object>lista= repositoryConsultas.listarConsultasFecha(customQuery.get("fecha"));
        for(int i=0;i<lista.size();i++){
            Object[] l=(Object[]) lista.get(i);
            ConsultasFechaDto cpid=new ConsultasFechaDto(l[0],l[1],l[2], l[3], l[4],l[5],l[6],l[7],l[8]);
            listaConsultas.add(cpid);
        }
        return listaConsultas;
    }
    @GetMapping(value = "/datos_consulta_adm/{id}")
    public @ResponseBody List<Object> controllerMethod(@PathVariable("id") Integer id) {
        return repositoryConsultas.obtenerTodosDatosConsulta(id);
    }
    @Autowired
    RepositoryConsultasJPA repositoryConsultasJPA;
    @DeleteMapping(value = "consulta/{id}")
    public @ResponseBody ConsultasEntity eliminarConsultaAdm(@PathVariable("id")int id){
        ConsultasEntity ca=repositoryConsultasJPA.findById(id).get();
        ca.setId_estado_consulta(8);
        repositoryConsultasJPA.save(ca);
        return ca;
    }
}
