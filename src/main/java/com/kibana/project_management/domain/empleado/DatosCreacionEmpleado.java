package com.kibana.project_management.domain.empleado;

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
