package com.example.monolitoproyect.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jline.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.monolitoproyect.dto.ConsultasFechaMedico;
import com.example.monolitoproyect.dto.ConsultasPacienteId;
import com.example.monolitoproyect.entities.ConsultasEntity;
import com.example.monolitoproyect.entities.EspecialidadesEntity;
import com.example.monolitoproyect.repository.RepositoryConsultas;
import com.example.monolitoproyect.repository.RepositoryConsultasJPA;
import com.example.monolitoproyect.repository.RepositoryEspecialidadesJPA;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/medicos")
public class MedicosController {
    @Autowired
    private RepositoryConsultas repositoryConsultas;

    @Autowired
    private RepositoryEspecialidadesJPA repositoryEspecialidadesJPA;

    @GetMapping(path = "/especialidades")
    public @ResponseBody
    List<EspecialidadesEntity> getConsultasPacienteId() {
       
        List<EspecialidadesEntity> listaEspecialidades=repositoryEspecialidadesJPA.findAll();
        
        return listaEspecialidades;
    }
}
