package com.kibana.project_management.domain.asignacion.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarEstado(
        @NotNull
        Long id,
        @NotNull
        @NotEmpty
        String estado
) {
}
