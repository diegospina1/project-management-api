package com.kibana.project_management.service;

import com.kibana.project_management.domain.asignacion.Asignacion;
import com.kibana.project_management.domain.asignacion.AsignacionRepository;
import com.kibana.project_management.domain.asignacion.dto.DatosActualizarEstado;
import com.kibana.project_management.domain.asignacion.dto.DatosCreacionAsignacion;
import com.kibana.project_management.domain.asignacion.estado.Estado;
import com.kibana.project_management.domain.asignacion.estado.EstadoEnum;
import com.kibana.project_management.domain.empleado.Empleado;
import com.kibana.project_management.domain.empleado.EmpleadoRepository;
import com.kibana.project_management.domain.tarea.Tarea;
import com.kibana.project_management.domain.tarea.TareaRespository;
import com.kibana.project_management.service.enum_service.subentities_service.EstadoService;
import com.kibana.project_management.service.errors.IExceptionMessages;
import com.kibana.project_management.service.errors.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AsignacionService implements IExceptionMessages {

    private AsignacionRepository asignacionRepository;
    private TareaRespository tareaRespository;
    private EmpleadoRepository empleadoRepository;
    private EstadoService estadoService;

    @Autowired
    public AsignacionService(AsignacionRepository asignacionRepository, TareaRespository tareaRespository,
                             EmpleadoRepository empleadoRepository, EstadoService estadoService) {
        this.asignacionRepository = asignacionRepository;
        this.tareaRespository = tareaRespository;
        this.empleadoRepository = empleadoRepository;
        this.estadoService = estadoService;
    }

    //Crear
    public Asignacion crearAsignacion(DatosCreacionAsignacion datos) {
        Optional<Tarea> tareaOptional = tareaRespository.findById(datos.tarea_id());
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(datos.empleado_id());
        if (tareaOptional.isEmpty()) {
            throw new NoEncontradoException(tareaMsg);
        } else if (empleadoOptional.isEmpty()) {
            throw new NoEncontradoException(empleadoMsg);
        }
        Asignacion asignacion = new Asignacion(null, tareaOptional.get(),
                empleadoOptional.get(), estadoService.buscarEstado(EstadoEnum.PENDIENTE),
                Instant.now().minus(5, ChronoUnit.HOURS), datos.fecha_limite());

        return asignacionRepository.save(asignacion);
    }

    //Listar
    public Page<Asignacion> listarAsignaciones(Pageable pages) {
        return asignacionRepository.findAll(pages);
    }

    //Listar por id
    public Asignacion listarPorId(Long id) {
        Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);
        if (asignacionOptional.isPresent()) {
            return asignacionOptional.get();
        } else {
            throw new NoEncontradoException(noEncontradoId(id));
        }
    }

    //Listar por cliente id
    public Page<Asignacion> listarPorEmpleado(Long id, Pageable pages) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
        if (empleadoOptional.isEmpty() || !empleadoOptional.get().getActivo()) {
            throw new NoEncontradoException(empleadoMsg);
        }
        Page<Asignacion> asignacionPage = asignacionRepository.findAllByEmpleado(empleadoOptional.get(), pages);
        if (!asignacionPage.hasContent()) {
            throw new NoEncontradoException(String.format("No se encontraron asignaciones para el empleado con el id: %d", id));
        } else {
            return asignacionPage;
        }
    }

    //Listar por tarea id
    public Page<Asignacion> listarPorTarea(Long id, Pageable pages) {
        Optional<Tarea> tareaOptional = tareaRespository.findById(id);
        if (tareaOptional.isEmpty() || !tareaOptional.get().getActivo()) {
            throw new NoEncontradoException(tareaMsg);
        }
        Page<Asignacion> asignacionPage = asignacionRepository.findAllByTarea(tareaOptional.get(), pages);
        if (!asignacionPage.hasContent()) {
            throw new NoEncontradoException(String.format("No se encontraron asignaciones para la tarea con el id: %d", id));
        } else {
            return asignacionPage;
        }
    }

    //Actualizar
    public Asignacion actualizarAsignacion(DatosActualizarEstado datos) {
        Optional<Asignacion> asignacionOptional = asignacionRepository.findById(datos.id());
        if (asignacionOptional.isEmpty()) {
            throw new NoEncontradoException(noEncontradoId(datos.id()));
        }
        Asignacion asignacion = asignacionOptional.get();
        asignacion.actualizarEstado(estadoService.buscarEstado(EstadoEnum.valueOf(datos.estado())));
        asignacionRepository.actualizarEstado(asignacion);
        return asignacion;
    }

    //Eliminar
    public void eliminarAsignacion(Long id) {
        Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);
        if (asignacionOptional.isEmpty()) {
            throw new NoEncontradoException(noEncontradoId(id));
        }
        Asignacion asignacion = asignacionOptional.get();
        asignacionRepository.delete(asignacion);
    }

    @Override
    public String noEncontradoString(String name) {
        return String.format("No se encontro la asignacion con el nombre: %s ", name);
    }

    @Override
    public String noEncontradoId(Long id) {
        return String.format("No se encontro la asignacion con el id: %s ", id);
    }
}
