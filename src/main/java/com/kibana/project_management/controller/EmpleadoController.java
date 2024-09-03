package com.kibana.project_management.controller;

import com.kibana.project_management.domain.empleado.*;
import com.kibana.project_management.service.EmpleadoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("empleado")
public class EmpleadoController {

    private EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Page<DatosRespuestaEmpleado>> listadoEmpleados(@PageableDefault Pageable pages){
        return ResponseEntity.ok(empleadoService.listarTodos(pages)
                .map(DatosRespuestaEmpleado::new));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DatosRespuestaEmpleado> listadoPorId(@PathVariable("id") Long id){
        Empleado empleado = empleadoService.listarPorId(id);
        DatosRespuestaEmpleado datos = new DatosRespuestaEmpleado(empleado);
        return ResponseEntity.ok(datos);
    }

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaEmpleado> crearEmpleado(@RequestBody @Valid DatosCreacionEmpleado datosCreacionEmpleado,
                                                                UriComponentsBuilder uriBuilder){
        Empleado empleado = empleadoService.crearEmpleado(datosCreacionEmpleado);
        DatosRespuestaEmpleado datos = new DatosRespuestaEmpleado(empleado);

        URI url = uriBuilder.path("/empleado/listar/{id}")
                .buildAndExpand(empleado.getId())
                .toUri();

        return ResponseEntity.created(url).body(datos);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaEmpleado> actualizarEmpleado(@RequestBody @Valid DatosActualizarEmpleado datosActualizarEmpleado){
        Empleado empleado = empleadoService.actualizarEmpleado(datosActualizarEmpleado);
        return ResponseEntity.ok(new DatosRespuestaEmpleado(empleado));
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") Long id){
        empleadoService.desactivarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activar/{id}")
    @Transactional
    public ResponseEntity<Void> activarEmpleado(@PathVariable("id") Long id){
        empleadoService.activarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar/username/{user}")
    public ResponseEntity<DatosRespuestaEmpleado> listarPorUser(@PathVariable("user") String user){
        return ResponseEntity.ok(new DatosRespuestaEmpleado(empleadoService.buscarPorUser(user)));
    }

    @PutMapping("/change-password")
    @Transactional
    public ResponseEntity<Void> cambiarPassword(@RequestBody @Valid DatosActualizarPassword datosActualizarPassword){
        empleadoService.cambiarPassword(datosActualizarPassword);
        return ResponseEntity.noContent().build();
    }


}
