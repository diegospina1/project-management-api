package com.kibana.project_management.domain.asignacion.dto;

import com.kibana.project_management.domain.asignacion.Asignacion;
import com.kibana.project_management.domain.asignacion.estado.Estado;
import com.kibana.project_management.domain.empleado.dto.DatosRespuestaEmpleado;
import com.kibana.project_management.domain.tarea.dto.DatosRespuestaTarea;

import java.time.Instant;

public record DatosRespuestaAsignacion(
        Long id,
        DatosRespuestaTarea tarea,
        DatosRespuestaEmpleado empleado,
        Estado estado,
        Instant fecha_creacion,
        Instant fecha_limite
) {
    public DatosRespuestaAsignacion(Asignacion asignacion){
        this(asignacion.getId(), new DatosRespuestaTarea(asignacion.getTarea()), new DatosRespuestaEmpleado(asignacion.getEmpleado()),
                asignacion.getEstado(), asignacion.getFecha_creacion(), asignacion.getFecha_limite());
    }
}
