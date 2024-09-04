package com.kibana.project_management.domain.tarea.dto;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTarea(
        @NotNull
        Long id,
        String clasificacion,
        String prioridad,
        String linea,
        Integer cantidad
) {
}
