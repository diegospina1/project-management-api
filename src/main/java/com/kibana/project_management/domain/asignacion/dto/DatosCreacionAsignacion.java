package com.kibana.project_management.domain.asignacion.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record DatosCreacionAsignacion(
        @NotNull
        Long empleado_id,
        @NotNull
        Long tarea_id,
        @NotNull
        Instant fecha_limite
) {
}
