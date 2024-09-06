package com.kibana.project_management.service.errors;

public interface IExceptionMessages {

    String clienteMsg = "No se pudo encontrar el cliente, o está desactivado.";
    String empleadoMsg = "No se pudo encontrar el empleado, o está desactivado.";
    String tareaMsg = "No se pudo encontrar la tarea, o está desactivada.";

    String noEncontradoString(String name);

    String noEncontradoId(Long id);
}
