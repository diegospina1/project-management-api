package com.kibana.project_management.domain.empleado.dto;

import com.kibana.project_management.domain.empleado.Empleado;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarEmpleado(
        @NotNull
        Long id,
        String nombre,
        String user,
        String password
) {
    public DatosActualizarEmpleado(Empleado empleado){
        this(empleado.getId(), empleado.getNombre(), empleado.getUser(), empleado.getPassword());
    }
}
