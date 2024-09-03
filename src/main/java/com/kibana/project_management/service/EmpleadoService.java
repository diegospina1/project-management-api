package com.kibana.project_management.service;

import com.kibana.project_management.domain.empleado.*;
import com.kibana.project_management.service.errors.exceptions.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    //Listar todos
    public Page<Empleado> listarTodos(Pageable pages) {
        return empleadoRepository.findAllByActivoTrue(pages);
    }

    //Listar por ID
    public Empleado listadoPorId(Long id){
        return empleadoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    //Crear un usuario
    public Empleado crearEmpleado(DatosCreacionEmpleado datos){
        return empleadoRepository.save(new Empleado(datos));
    }

    //Actualizar
    public Empleado actualizarEmpleado(DatosActualizarEmpleado datos){
        Empleado empleado = listadoPorId(datos.id());
        empleado.actualizarEmpleado(datos);
        DatosActualizarEmpleado datosActualizacion = new DatosActualizarEmpleado(empleado);
        empleadoRepository.actualizarEmpleado(datosActualizacion);

        return empleado;
    }

    //Eliminar
    public void eliminarEmpleado(Long id){
        Empleado empleado = listadoPorId(id);
        empleado.desactivarEmpleado();
        empleadoRepository.eliminarEmpleado(empleado.getId());
    }

    //Reactivar un empleado
    public void activarEmpleado(Long id){
        Empleado empleado = listadoPorId(id);
        empleado.activar();
        empleadoRepository.activarEmpleado(empleado.getId());
    }

    //Cambiar password
    public void cambiarPassword(DatosActualizarPassword datos){
        Optional<Empleado> empleadoOptional = empleadoRepository.findByUser(datos.user());
        if(empleadoOptional.isPresent()){
            Empleado empleado = empleadoOptional.get();
            empleadoRepository.cambiarPassword(datos.password(), empleado.getId());
        } else {
            throw new ValidacionDeIntegridad(String.format("Usuario no encontrado: %s", datos.user()));
        }

    }

    //Buscar por user
    public Empleado buscarPorUser(String user){
      return empleadoRepository.findByUser(user)
              .orElseThrow(EntityNotFoundException::new);
    }

}
