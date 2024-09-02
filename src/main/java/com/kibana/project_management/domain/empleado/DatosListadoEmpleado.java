package com.kibana.project_management.domain.empleado;

public record DatosListadoEmpleado(
        Long id,
        String nombre,
        String user,
        Boolean activo
) {
    public DatosListadoEmpleado(Empleado empleado){
        this(empleado.getId(), empleado.getNombre(), empleado.getUser(), empleado.getActivo());
    }

}
