package com.kibana.project_management.service;

import com.kibana.project_management.domain.cliente.Cliente;
import com.kibana.project_management.domain.cliente.ClienteRepository;
import com.kibana.project_management.domain.tarea.dto.DatosCreacionTarea;
import com.kibana.project_management.domain.tarea.Tarea;
import com.kibana.project_management.domain.tarea.TareaRespository;
import com.kibana.project_management.domain.tarea.subentities.clasificacion.Clasificacion;
import com.kibana.project_management.domain.tarea.subentities.clasificacion.ClasificacionRepository;
import com.kibana.project_management.domain.tarea.subentities.linea.Linea;
import com.kibana.project_management.domain.tarea.subentities.linea.LineaRepository;
import com.kibana.project_management.domain.tarea.subentities.prioridad.Prioridad;
import com.kibana.project_management.domain.tarea.subentities.prioridad.PrioridadRepository;
import com.kibana.project_management.service.errors.IExceptionMessages;
import com.kibana.project_management.service.errors.exceptions.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TareaService implements IExceptionMessages {

    private final ClienteRepository clienteRepository;
    private final TareaRespository tareaRespository;
    private final ClasificacionRepository clasificacionRepository;
    private final PrioridadRepository prioridadRepository;
    private final LineaRepository lineaRepository;

    @Autowired
    public TareaService(TareaRespository tareaRespository, ClienteRepository clienteRepository, ClasificacionRepository clasificacionRepository, PrioridadRepository prioridadRepository, LineaRepository lineaRepository) {
        this.tareaRespository = tareaRespository;
        this.clienteRepository = clienteRepository;
        this.clasificacionRepository = clasificacionRepository;
        this.prioridadRepository = prioridadRepository;
        this.lineaRepository = lineaRepository;
    }

    //Crear tarea
    public Tarea crearTarea(DatosCreacionTarea datos){
        Optional<Cliente> clienteOptional = clienteRepository.findById(datos.cliente_id());
        if(clienteOptional.isPresent() && clienteOptional.get().getActivo()){
            Clasificacion clasificacion = clasificacionRepository.findByClasificacion(datos.clasificacion().name());
            Prioridad prioridad = prioridadRepository.findByPrioridad(datos.prioridad().name());
            Linea linea = lineaRepository.findByLinea(datos.linea().name());

            Tarea tarea = new Tarea(clienteOptional.get(), linea, prioridad, clasificacion, datos);
            return tareaRespository.save(tarea);
        } else {
            throw new ValidacionDeIntegridad(clienteMsg);
        }
    }

    //No encontrados
    @Override
    public String noEncontradoString(String name) {
        return String.format("No se pudo encontrar tarea con el nombre: %s", name);
    }
    @Override
    public String noEncontradoId(Long id) {
        return String.format("No se pudo encontrar tarea con el id: %d", id);
    }
}
