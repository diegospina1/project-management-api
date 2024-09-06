package com.kibana.project_management.service.enum_service.subentities_service;

import com.kibana.project_management.domain.tarea.prioridad.Prioridad;
import com.kibana.project_management.domain.tarea.prioridad.PrioridadEnum;
import com.kibana.project_management.domain.tarea.prioridad.PrioridadRepository;
import com.kibana.project_management.service.errors.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrioridadService {

    private final PrioridadRepository prioridadRepository;

    @Autowired
    public PrioridadService(PrioridadRepository prioridadRepository) {
        this.prioridadRepository = prioridadRepository;
    }

    public Prioridad buscarPrioridad(PrioridadEnum prioridadEnum){
        Optional<Prioridad> prioridadOptional = prioridadRepository.findByPrioridad(prioridadEnum.name());
        if(prioridadOptional.isPresent()){
            return prioridadOptional.get();
        } else {
            throw new NoEncontradoException("Prioridad no encontrada");
        }

    }
}
