package com.kibana.project_management.service.enum_service;

import com.kibana.project_management.domain.tarea.subentities.clasificacion.Clasificacion;
import com.kibana.project_management.domain.tarea.subentities.linea.Linea;
import com.kibana.project_management.domain.tarea.subentities.prioridad.Prioridad;
import org.springframework.stereotype.Service;

@Service
public interface IEnumService {
    Clasificacion obtenerClasificacion(String clasificacion);
    Prioridad obtenerPrioridad(String prioridad);
    Linea obtenerLinea(String linea);
}
