package com.kibana.project_management.service;

import com.kibana.project_management.domain.cliente.Cliente;
import com.kibana.project_management.domain.cliente.ClienteRepository;
import com.kibana.project_management.domain.tarea.dto.DatosActualizarTarea;
import com.kibana.project_management.domain.tarea.dto.DatosCreacionTarea;
import com.kibana.project_management.domain.tarea.Tarea;
import com.kibana.project_management.domain.tarea.TareaRespository;
import com.kibana.project_management.domain.tarea.dto.DatosRespuestaTarea;
import com.kibana.project_management.service.enum_service.IEnumService;
import com.kibana.project_management.service.errors.IExceptionMessages;
import com.kibana.project_management.service.errors.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TareaService implements IExceptionMessages {

    private final ClienteRepository clienteRepository;
    private final TareaRespository tareaRespository;
    private final IEnumService enumService;

    @Autowired
    public TareaService(TareaRespository tareaRespository, ClienteRepository clienteRepository, IEnumService enumService) {
        this.tareaRespository = tareaRespository;
        this.clienteRepository = clienteRepository;
        this.enumService = enumService;
    }

    //Crear tarea
    public Tarea crearTarea(DatosCreacionTarea datos){
        Optional<Cliente> clienteOptional = clienteRepository.findById(datos.cliente_id());
        if(clienteOptional.isPresent() && clienteOptional.get().getActivo()){
            Tarea tarea = new Tarea(clienteOptional.get(),
                    enumService.obtenerLinea(datos.linea()),
                    enumService.obtenerPrioridad(datos.prioridad()),
                    enumService.obtenerClasificacion(datos.clasificacion()),
                    datos);
            return tareaRespository.save(tarea);
        } else {
            throw new NoEncontradoException(clienteMsg);
        }
    }

    //Listar todas
    public Page<Tarea> listarTodos(Pageable pages){
        return tareaRespository.findAllByActivoTrue(pages);
    }

    //Listar por id
    public Tarea listarPorId(Long id){
        Optional<Tarea> tareaOptional = tareaRespository.findById(id);

        if(tareaOptional.isPresent()){
            return tareaOptional.get();
        } else {
            throw new NoEncontradoException(noEncontradoId(id));
        }
    }

    //Listar por cliente
    public Page<Tarea> listarPorCliente(Long id, Pageable pages){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent() && clienteOptional.get().getActivo()){
            return tareaRespository.buscarTareaPorCliente(clienteOptional.get(), pages);
        } else {
            throw new NoEncontradoException(clienteMsg);
        }
    }

    //Actualizar
    public Tarea actualizarTarea(DatosActualizarTarea datos){
        Optional<Tarea> tareaOptional = tareaRespository.findById(datos.id());
        if(tareaOptional.isPresent()){
            Tarea tarea = tareaOptional.get();
            tarea.actualizarTarea(datos,
                    enumService.obtenerClasificacion(datos.clasificacion()),
                    enumService.obtenerPrioridad(datos.prioridad()),
                    enumService.obtenerLinea(datos.linea()));

            return tareaRespository.save(tarea);
        } else {
            throw new NoEncontradoException(noEncontradoId(datos.id()));
        }
    }

    //Desactivar
    public void desactivarTarea(Long id){
        Optional<Tarea> tareaOptional = tareaRespository.findById(id);
        if(tareaOptional.isPresent()){
            Tarea tarea = tareaOptional.get();
            tarea.desactivarTarea();
            tareaRespository.save(tarea);
        } else {
            throw new NoEncontradoException(noEncontradoId(id));
        }
    }

    //Activar
    public void activarTarea(Long id) {
        Optional<Tarea> tareaOptional = tareaRespository.findById(id);
        if(tareaOptional.isPresent()){
            Tarea tarea = tareaOptional.get();
            tarea.activarTarea();
            tareaRespository.save(tarea);
        } else {
            throw new NoEncontradoException(noEncontradoId(id));
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
