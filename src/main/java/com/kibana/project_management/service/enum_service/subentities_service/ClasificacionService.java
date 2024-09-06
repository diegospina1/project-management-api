package com.kibana.project_management.service.enum_service.subentities_service;

import com.kibana.project_management.domain.tarea.clasificacion.Clasificacion;
import com.kibana.project_management.domain.tarea.clasificacion.ClasificacionEnum;
import com.kibana.project_management.domain.tarea.clasificacion.ClasificacionRepository;
import com.kibana.project_management.service.errors.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClasificacionService {

    private final ClasificacionRepository clasificacionRepository;

    @Autowired
    public ClasificacionService(ClasificacionRepository clasificacionRepository) {
        this.clasificacionRepository = clasificacionRepository;
    }

    public Clasificacion buscarClasificacion(ClasificacionEnum clasificacionEnum){
        Optional<Clasificacion> clasificacionOptional = clasificacionRepository.findByClasificacion(clasificacionEnum.name());
        if(clasificacionOptional.isPresent()){
            return clasificacionOptional.get();
        } else {
            throw new NoEncontradoException("No se ha encontrado la clasificacion");
        }
    }
}
