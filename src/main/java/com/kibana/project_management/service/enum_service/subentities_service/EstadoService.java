package com.kibana.project_management.service.enum_service.subentities_service;

import com.kibana.project_management.domain.asignacion.estado.Estado;
import com.kibana.project_management.domain.asignacion.estado.EstadoEnum;
import com.kibana.project_management.domain.asignacion.estado.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado buscarEstado(EstadoEnum estadoEnum){
        return estadoRepository.findByEstado(estadoEnum.name());
    }

}
