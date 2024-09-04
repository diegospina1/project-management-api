package com.kibana.project_management.domain.empleado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosActualizarPassword(
        @NotBlank
        String user,
        @NotBlank
        @Size(min = 8, max = 24)
        String password
) {
}
