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
import com.example.monolitoproyect.dto.OrdenLaboratorioDto;
import com.example.monolitoproyect.entities.AtencionEntity;
import com.example.monolitoproyect.entities.ConsultasEntity;
import com.example.monolitoproyect.entities.EspecialidadesEntity;
import com.example.monolitoproyect.entities.EstadosConsultaEntity;
import com.example.monolitoproyect.entities.HojaTraspaso;
import com.example.monolitoproyect.entities.MedicosEntity;
import com.example.monolitoproyect.entities.OrdenLaboratorio;
import com.example.monolitoproyect.entities.RecetasEntity;
import com.example.monolitoproyect.repository.RepositoryAtencion;
import com.example.monolitoproyect.repository.RepositoryConsultas;
import com.example.monolitoproyect.repository.RepositoryConsultasJPA;
import com.example.monolitoproyect.repository.RepositoryEspecialidadesJPA;
import com.example.monolitoproyect.repository.RepositoryEstadoConsultas;
import com.example.monolitoproyect.repository.RepositoryHojaTraspasoJPA;
import com.example.monolitoproyect.repository.RepositoryMedicos;
import com.example.monolitoproyect.repository.RepositoryOrdenLaboratorio;
import com.example.monolitoproyect.repository.RepositoryOrdenLaboratorioJPA;
import com.example.monolitoproyect.repository.RespositoryRecetas;
import com.example.monolitoproyect.repository.RespositoryRecetasJPA;
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

    @Autowired
    private RepositoryConsultasJPA repositoryConsultasJPA;
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
    
    @Autowired
    RespositoryRecetas respositoryRecetas;
    @GetMapping(value = "consulta/receta/{id}")
    public @ResponseBody List<RecetasEntity> obtenerRecetasDeConsulta(@PathVariable("id")Integer id){
        return respositoryRecetas.listaRecetasPorConsulta(id);
    }

    @DeleteMapping(value = "consulta/receta/{id}")
    public @ResponseBody String eliminarReceta(@PathVariable("id")Integer id){
        respositoryRecetasJPA.deleteById(id);
        return "ok";
    }
    
    @Autowired
    RespositoryRecetasJPA respositoryRecetasJPA;
    @PostMapping(value = "consulta/receta")
    public @ResponseBody RecetasEntity registrarNuevaReceta(@RequestBody RecetasEntity recentaNueva){
        respositoryRecetasJPA.save(recentaNueva);
        return recentaNueva;
    }

    @PutMapping(value = "consulta/detalle/{id}")
    public @ResponseBody ConsultasEntity actualizarDetalleConsulta(@RequestBody Map<String,Object> nuevos,@PathVariable("id")Integer id){
        Log.info(Integer.parseInt(nuevos.get("id_estado_consulta")+""));
        Log.info(nuevos.get("informe_consulta"));
        ConsultasEntity ca=repositoryConsultasJPA.findById(id).get();
        ca.setId_estado_consulta(Integer.parseInt(nuevos.get("id_estado_consulta")+""));
        ca.setInforme_consulta(nuevos.get("informe_consulta")+"");
        repositoryConsultasJPA.save(ca);
        return ca;
    }
    @Autowired
    RepositoryHojaTraspasoJPA repositoryHojaTraspasoJPA;
    @PostMapping(value = "/hojas-traspaso")
    public @ResponseBody HojaTraspaso registrarHojaTraspaso(@RequestBody HojaTraspaso htn){
        repositoryHojaTraspasoJPA.save(htn);
        return htn;
    }

    @Autowired
    RepositoryOrdenLaboratorioJPA repositoryOrdenLaboratorioJPA;
    @Autowired
    RepositoryOrdenLaboratorio repositoryOrdenLaboratorio;
    @PostMapping(value = "/orden-laboratorio")
    public @ResponseBody OrdenLaboratorio registrarOrdenLaboratorio(@RequestBody OrdenLaboratorio oln){
        repositoryOrdenLaboratorioJPA.save(oln);
        return oln;
    }
    @GetMapping(value = "/orden-laboratorio")
    public @ResponseBody List<OrdenLaboratorio> obtenerTodasOrdenesLaboratorio(){
        return repositoryOrdenLaboratorioJPA.findAll();
    }
    @GetMapping(value = "/consulta/orden-laboratorio/{id}")
    public @ResponseBody List<Object> obtenerTodasOrdenesLaboratorio(@PathVariable("id") String id_consulta){
        List<Object>ordenes= repositoryOrdenLaboratorio.buscarOrdenesLaboratorioDeConsulta(id_consulta);
        List<Object>of=new ArrayList<Object>();
        for(int i=0;i<ordenes.size();i++){
            Object[]valores=(Object[]) ordenes.get(i);
            of.add(new OrdenLaboratorioDto(valores[0],valores[1],valores[2]));
        }
        return of;
    }
    @DeleteMapping(value = "/orden-laboratorio/{id}")
    public @ResponseBody OrdenLaboratorio eliminarOrden(@PathVariable("id")int idOrden){
        OrdenLaboratorio oe=repositoryOrdenLaboratorioJPA.findById(idOrden).get();
        repositoryOrdenLaboratorioJPA.delete(oe);
        return oe;

    }
} 
