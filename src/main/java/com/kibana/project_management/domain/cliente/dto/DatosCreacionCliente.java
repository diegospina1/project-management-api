package com.kibana.project_management.domain.cliente.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosCreacionCliente(
        @NotBlank
        String nombres
) {
}
