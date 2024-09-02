package com.kibana.project_management.domain.empleado;

public record DatosRespuestaEmpleado(
        Long id,
        String nombre,
        String user,
        Boolean activo
) {
    public DatosRespuestaEmpleado(Empleado empleado){
        this(empleado.getId(), empleado.getNombre(), empleado.getUser(), empleado.getActivo());
    }
}
