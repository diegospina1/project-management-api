package com.kibana.project_management.domain.tarea.dto;

import com.kibana.project_management.domain.cliente.dto.DatosRespuestaCliente;
import com.kibana.project_management.domain.tarea.Tarea;
import com.kibana.project_management.domain.tarea.clasificacion.ClasificacionEnum;
import com.kibana.project_management.domain.tarea.linea.LineaEnum;
import com.kibana.project_management.domain.tarea.prioridad.PrioridadEnum;

import java.time.Instant;

public record DatosRespuestaTarea(
        Long id,
        ClasificacionEnum clasificacion,
        DatosRespuestaCliente cliente,
        LineaEnum linea,
        PrioridadEnum prioridad,
        Integer cantidad,
        Instant fecha_creacion,
        Boolean activo
) {
    public DatosRespuestaTarea(Tarea tarea){
        this(tarea.getId(), ClasificacionEnum.valueOf(tarea.getClasificacion().getClasificacion()),
                new DatosRespuestaCliente(tarea.getCliente()), LineaEnum.valueOf(tarea.getLinea().getLinea()),
                PrioridadEnum.valueOf(tarea.getPrioridad().getPrioridad()), tarea.getCantidad(),
                tarea.getFecha_creacion(), tarea.getActivo());
    }
}
