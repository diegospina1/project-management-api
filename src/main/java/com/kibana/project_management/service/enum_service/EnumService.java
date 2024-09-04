package com.kibana.project_management.service.enum_service;

import com.kibana.project_management.domain.tarea.subentities.clasificacion.Clasificacion;
import com.kibana.project_management.domain.tarea.subentities.clasificacion.ClasificacionEnum;
import com.kibana.project_management.domain.tarea.subentities.linea.Linea;
import com.kibana.project_management.domain.tarea.subentities.linea.LineaEnum;
import com.kibana.project_management.domain.tarea.subentities.prioridad.Prioridad;
import com.kibana.project_management.domain.tarea.subentities.prioridad.PrioridadEnum;
import com.kibana.project_management.service.enum_service.subentities_service.ClasificacionService;
import com.kibana.project_management.service.enum_service.subentities_service.LineaService;
import com.kibana.project_management.service.enum_service.subentities_service.PrioridadService;
import com.kibana.project_management.service.errors.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnumService implements IEnumService {

    private final ClasificacionService clasificacionService;
    private final PrioridadService prioridadService;
    private final LineaService lineaService;

    @Autowired
    public EnumService(ClasificacionService clasificacionService, PrioridadService prioridadService, LineaService lineaService) {
        this.clasificacionService = clasificacionService;
        this.prioridadService = prioridadService;
        this.lineaService = lineaService;
    }

    @Override
    public Clasificacion obtenerClasificacion(String clasificacion) {
        if(clasificacion != null){
            try{
                ClasificacionEnum clasificacionEnum = ClasificacionEnum.valueOf(clasificacion);
                return clasificacionService.buscarClasificacion(clasificacionEnum);
            } catch (IllegalArgumentException e) {
                throw new NoEncontradoException(String.format("Clasificacion no encontrada: %s", clasificacion));
            }
        } else {
            return null;
        }

    }

    @Override
    public Prioridad obtenerPrioridad(String prioridad) {
        if(prioridad != null){
            try {
                PrioridadEnum prioridadEnum = PrioridadEnum.valueOf(prioridad);
                return prioridadService.buscarPrioridad(prioridadEnum);
            } catch (IllegalArgumentException e) {
                throw new NoEncontradoException(String.format("Prioridad no encontrada: %s", prioridad));
            }
        } else {
            return null;
        }
    }

    @Override
    public Linea obtenerLinea(String linea) {
        if(linea != null){
            try {
                LineaEnum lineaEnum = LineaEnum.valueOf(linea);
                return lineaService.buscarLinea(lineaEnum);
            } catch (IllegalArgumentException e) {
                throw new NoEncontradoException(String.format("Linea no encontrada: %s", linea));
            }
        } else {
            return null;
        }

    }
}
