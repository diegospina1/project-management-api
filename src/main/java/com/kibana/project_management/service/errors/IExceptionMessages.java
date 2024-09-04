package com.kibana.project_management.service.errors;

public interface IExceptionMessages {

    String clienteMsg = "No se pudo encontrar el cliente, o está desactivado.";

    String noEncontradoString(String name);

    String noEncontradoId(Long id);
}
