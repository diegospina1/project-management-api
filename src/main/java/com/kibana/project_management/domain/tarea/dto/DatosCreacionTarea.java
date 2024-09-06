package com.kibana.project_management.domain.tarea.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DatosCreacionTarea(
        @NotNull
        Long cliente_id,
        @NotNull
        @NotEmpty
        String clasificacion,
        @NotNull
        @NotEmpty
        String linea,
        @NotNull
        @NotEmpty
        String prioridad,
        @NotNull
        @Min(1)
        Integer cantidad
) {
}
