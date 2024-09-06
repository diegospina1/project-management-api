package com.kibana.project_management.controller;

import com.kibana.project_management.domain.asignacion.Asignacion;
import com.kibana.project_management.domain.asignacion.dto.DatosActualizarEstado;
import com.kibana.project_management.domain.asignacion.dto.DatosCreacionAsignacion;
import com.kibana.project_management.domain.asignacion.dto.DatosRespuestaAsignacion;
import com.kibana.project_management.service.AsignacionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("asignacion")
public class AsignacionController {

    private AsignacionService asignacionService;

    @Autowired
    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaAsignacion> crearAsignacion(@RequestBody @Valid DatosCreacionAsignacion datosCreacionAsignacion,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaAsignacion datosRespuestaAsignacion = new DatosRespuestaAsignacion( asignacionService.crearAsignacion(datosCreacionAsignacion));
        URI url = uriComponentsBuilder.path("/asignacion/listar/{id}")
                .buildAndExpand(datosRespuestaAsignacion.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaAsignacion);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Page<DatosRespuestaAsignacion>> listarTodos(Pageable pages){
        return ResponseEntity.ok(asignacionService.listarAsignaciones(pages)
                .map(DatosRespuestaAsignacion::new));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DatosRespuestaAsignacion> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(new DatosRespuestaAsignacion(asignacionService.listarPorId(id)));
    }

    @GetMapping("/listar/empleado/{id}")
    public ResponseEntity<Page<DatosRespuestaAsignacion>> listarPorEmpleadoId(@PathVariable("id") Long id, Pageable pages){
        return ResponseEntity.ok(asignacionService.listarPorEmpleado(id, pages)
                .map(DatosRespuestaAsignacion::new));
    }

    @GetMapping("/listar/tarea/{id}")
    public ResponseEntity<Page<DatosRespuestaAsignacion>> listarPorTareaId(@PathVariable("id") Long id, Pageable pages){
        return ResponseEntity.ok(asignacionService.listarPorTarea(id, pages)
                .map(DatosRespuestaAsignacion::new));
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaAsignacion> actualizarEstado(@RequestBody @Valid DatosActualizarEstado datosActualizarEstado){
        return ResponseEntity.ok(new DatosRespuestaAsignacion(asignacionService.actualizarAsignacion(datosActualizarEstado)));
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable("id") Long id){
        asignacionService.eliminarAsignacion(id);
        return ResponseEntity.noContent().build();
    }
}
