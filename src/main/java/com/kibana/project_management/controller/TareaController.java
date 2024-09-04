package com.kibana.project_management.controller;

import com.kibana.project_management.domain.tarea.Tarea;
import com.kibana.project_management.domain.tarea.dto.DatosCreacionTarea;
import com.kibana.project_management.domain.tarea.dto.DatosRespuestaTarea;
import com.kibana.project_management.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarea")
public class TareaController {

    private TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaTarea> crearTarea(@RequestBody @Valid DatosCreacionTarea datosCreacionTarea){
        DatosRespuestaTarea datosRespuestaTarea = new DatosRespuestaTarea(tareaService.crearTarea(datosCreacionTarea));
        return ResponseEntity.ok(datosRespuestaTarea);
    }
}
