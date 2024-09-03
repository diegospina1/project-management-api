package com.kibana.project_management.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCliente(
        @NotNull
        Long id,
        @NotBlank
        String nombres
) {
}
