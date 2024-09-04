package com.kibana.project_management.service.enum_service.subentities_service;

import com.kibana.project_management.domain.tarea.subentities.linea.Linea;
import com.kibana.project_management.domain.tarea.subentities.linea.LineaEnum;
import com.kibana.project_management.domain.tarea.subentities.linea.LineaRepository;
import com.kibana.project_management.service.errors.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LineaService {

    private final LineaRepository lineaRepository;

    @Autowired
    public LineaService(LineaRepository lineaRepository) {
        this.lineaRepository = lineaRepository;
    }

    public Linea buscarLinea(LineaEnum lineaEnum){
        Optional<Linea> lineaOptional = lineaRepository.findByLinea(lineaEnum.name());
        if (lineaOptional.isPresent()){
            return lineaOptional.get();
        } else {
            throw new NoEncontradoException("Linea no encontrada.");
        }
    }
}
