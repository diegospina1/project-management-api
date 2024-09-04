package com.kibana.project_management.controller;

import com.kibana.project_management.domain.tarea.Tarea;
import com.kibana.project_management.domain.tarea.dto.DatosActualizarTarea;
import com.kibana.project_management.domain.tarea.dto.DatosCreacionTarea;
import com.kibana.project_management.domain.tarea.dto.DatosRespuestaTarea;
import com.kibana.project_management.service.TareaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("tarea")
public class TareaController {

    private TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaTarea> crearTarea(@RequestBody @Valid DatosCreacionTarea datosCreacionTarea,
                                                          UriComponentsBuilder uriBuilder){
        DatosRespuestaTarea datosRespuestaTarea = new DatosRespuestaTarea(tareaService.crearTarea(datosCreacionTarea));
        return ResponseEntity.ok(datosRespuestaTarea);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Page<DatosRespuestaTarea>> buscarTodos(Pageable pages){
        return ResponseEntity.ok(tareaService.listarTodos(pages)
                .map(DatosRespuestaTarea::new));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DatosRespuestaTarea> buscarPorId(@PathVariable("id") Long id){
        DatosRespuestaTarea datosRespuestaTarea = new DatosRespuestaTarea(tareaService.listarPorId(id));
        return ResponseEntity.ok(datosRespuestaTarea);
    }

    @GetMapping("/listar/cliente/{id}")
    public ResponseEntity<Page<DatosRespuestaTarea>> buscarPorClienteId(@PathVariable("id") Long id, Pageable pages){
        return ResponseEntity.ok(tareaService.listarPorCliente(id, pages)
                .map(DatosRespuestaTarea::new));
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaTarea> actualizarTarea(@RequestBody @Valid DatosActualizarTarea datosActualizarTarea){
        return ResponseEntity.ok(new DatosRespuestaTarea(tareaService.actualizarTarea(datosActualizarTarea)));
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Void> desactivarTarea(@PathVariable("id") Long id){
        tareaService.desactivarTarea(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activar/{id}")
    @Transactional
    public ResponseEntity<Void> activarTarea(@PathVariable("id") Long id){
        tareaService.activarTarea(id);
        return ResponseEntity.noContent().build();
    }
}
