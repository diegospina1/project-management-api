package com.kibana.project_management.domain.cliente.dto;

import com.kibana.project_management.domain.cliente.Cliente;

public record DatosRespuestaCliente(
        Long id,
        String nombres,
        Boolean activo
) {
    public DatosRespuestaCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNombres(), cliente.getActivo());
    }
}
