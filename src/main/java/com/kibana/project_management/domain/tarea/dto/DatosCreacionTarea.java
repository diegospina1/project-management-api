package com.kibana.project_management.domain.tarea.dto;


import com.kibana.project_management.domain.tarea.subentities.clasificacion.ClasificacionEnum;
import com.kibana.project_management.domain.tarea.subentities.linea.LineaEnum;
import com.kibana.project_management.domain.tarea.subentities.prioridad.PrioridadEnum;
import jakarta.validation.constraints.NotNull;

public record DatosCreacionTarea(
        @NotNull
        ClasificacionEnum clasificacion,
        @NotNull
        Long cliente_id,
        @NotNull
        LineaEnum linea,
        @NotNull
        PrioridadEnum prioridad,
        @NotNull
        Integer cantidad
) {
}
