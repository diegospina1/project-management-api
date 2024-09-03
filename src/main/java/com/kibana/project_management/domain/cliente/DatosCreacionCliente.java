package com.kibana.project_management.domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record DatosCreacionCliente(
        @NotBlank
        String nombres
) {
}
