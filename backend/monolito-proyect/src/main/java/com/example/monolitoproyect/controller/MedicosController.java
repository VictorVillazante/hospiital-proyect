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
import com.example.monolitoproyect.entities.AtencionEntity;
import com.example.monolitoproyect.entities.ConsultasEntity;
import com.example.monolitoproyect.entities.EspecialidadesEntity;
import com.example.monolitoproyect.entities.EstadosConsultaEntity;
import com.example.monolitoproyect.entities.MedicosEntity;
import com.example.monolitoproyect.repository.RepositoryAtencion;
import com.example.monolitoproyect.repository.RepositoryConsultas;
import com.example.monolitoproyect.repository.RepositoryConsultasJPA;
import com.example.monolitoproyect.repository.RepositoryEspecialidadesJPA;
import com.example.monolitoproyect.repository.RepositoryEstadoConsultas;
import com.example.monolitoproyect.repository.RepositoryMedicos;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/medicos")
public class MedicosController {
    @Autowired
    private RepositoryMedicos repositoryMedicos;

    @Autowired
    private RepositoryEspecialidadesJPA repositoryEspecialidadesJPA;

    @Autowired
    private RepositoryAtencion respositoryAtencion;

    @Autowired
    private RepositoryConsultas repositoryConsultas;

    @GetMapping(path = "/especialidades")
    public @ResponseBody
    List<EspecialidadesEntity> getConsultasPacienteId() {
       
        List<EspecialidadesEntity> listaEspecialidades=repositoryEspecialidadesJPA.findAll();
        
        return listaEspecialidades;
    }
    @GetMapping(path = "/atencion")
    public @ResponseBody Object getAtencion(@RequestParam Map<String, String> customQuery){
       Log.info("Buscando id atencion");
        AtencionEntity atencion=respositoryAtencion.listarConsultasMedicoFecha(customQuery.get("id_medico"),customQuery.get("id_especialidades"));
        if(atencion==null){
            return -1;
        }else{
            return atencion.getId_atencion();
        }
        // if(respositoryAtencion.listarConsultasMedicoFecha(customQuery.get("id_medico"),customQuery.get("id_especialidades"))==null){
        //     return -1;
        // }else{
        //     AtencionEntity atencion=respositoryAtencion.listarConsultasMedicoFecha(customQuery.get("id_medico"),customQuery.get("id_especialidades"));
        //     return atencion.getId_atencion();
        // }
    }

    @GetMapping(path = "/especialidades/{id}")
    public @ResponseBody
    List<MedicosEntity> getMedicosPorEspecialidad(@PathVariable("id") Integer id) {
       
        List<MedicosEntity> listaMedicos=repositoryMedicos.listaMedicosDeEspecialidadId(id);
        
        return listaMedicos;
    }
    @GetMapping(value = "/consultas-dia")
    public @ResponseBody List<ConsultasFechaMedico> controllerMethod(@RequestParam Map<String, String> customQuery) {

        System.out.println("Medico" + customQuery.get("id_medico"));
        System.out.println("Fecha" + customQuery.get("fecha"));

        ArrayList<ConsultasFechaMedico> listaConsultas=new ArrayList();
        List<Object>lista= repositoryConsultas.listarConsultasMedicoFecha(customQuery.get("id_medico"),customQuery.get("fecha"));
        for(int i=0;i<lista.size();i++){
            Object[] l=(Object[]) lista.get(i);
            ConsultasFechaMedico cpid=new ConsultasFechaMedico(l[0],l[1],l[2], l[3], l[4],l[5],l[6],l[7]);
            listaConsultas.add(cpid);
        }
        return listaConsultas;
    }
    @Autowired
    RepositoryEstadoConsultas repositoryEstadoConsultas;
    @GetMapping(value = "estados-consultas")
    public @ResponseBody List<EstadosConsultaEntity> obtenerEstadosConsulta(){
        return repositoryEstadoConsultas.findAll();
    }
} 
