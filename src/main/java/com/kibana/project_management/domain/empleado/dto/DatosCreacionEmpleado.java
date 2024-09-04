package com.kibana.project_management.domain.empleado.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosCreacionEmpleado(
        @NotBlank
        String nombre,
        @NotBlank
        String user,
        @NotBlank
        String password
) {
}
