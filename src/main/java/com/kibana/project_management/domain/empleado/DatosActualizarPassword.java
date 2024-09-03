package com.kibana.project_management.domain.empleado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

public record DatosActualizarPassword(
        @NotBlank
        String user,
        @NotBlank
        @Size(min = 8, max = 24)
        String password
) {
}
