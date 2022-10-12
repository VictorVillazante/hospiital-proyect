package com.example.monolitoproyect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.monolitoproyect.entities.HojaTraspasoEntity;

public interface RepositoryHojaTraspaso extends PagingAndSortingRepository<HojaTraspasoEntity,Integer>{
    @Query(value="SELECT * FROM hojas_traspaso WHERE id_consultas=?1",nativeQuery=true)
    List<Object> buscarHojasTraspasoDeConsulta(String id_atencion);
    
}