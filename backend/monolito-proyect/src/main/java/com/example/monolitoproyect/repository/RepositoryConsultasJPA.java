package com.example.monolitoproyect.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.monolitoproyect.ConsultasEntity;

public interface RepositoryConsultasJPA extends org.springframework.data.jpa.repository.JpaRepository<ConsultasEntity, Integer>{
}