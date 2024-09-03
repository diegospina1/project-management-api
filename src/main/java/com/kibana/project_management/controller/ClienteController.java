package com.kibana.project_management.controller;

import com.kibana.project_management.domain.cliente.Cliente;
import com.kibana.project_management.domain.cliente.DatosActualizarCliente;
import com.kibana.project_management.domain.cliente.DatosCreacionCliente;
import com.kibana.project_management.domain.cliente.DatosRespuestaCliente;
import com.kibana.project_management.service.ClienteService;
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
@RequestMapping("cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<Page<DatosRespuestaCliente>> listadoClientes(Pageable pages){
        return ResponseEntity.ok(clienteService.listarTodos(pages)
                .map(DatosRespuestaCliente::new));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DatosRespuestaCliente> listadoPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(new DatosRespuestaCliente(clienteService.listarPorId(id)));
    }

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaCliente> crearCliente(@RequestBody @Valid DatosCreacionCliente datosCreacionCliente,
                                                              UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.crearCliente(datosCreacionCliente);
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente);
        URI url = uriBuilder.path("/cliente/listar/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaCliente);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> actualizarCliente(@RequestBody @Valid DatosActualizarCliente datosActualizarCliente){
        Cliente cliente = clienteService.actualizarCliente(datosActualizarCliente);
        return ResponseEntity.ok(new DatosRespuestaCliente(cliente));
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Void> desactivarCliente(@PathVariable("id") Long id){
        clienteService.desactivarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activar/{id}")
    @Transactional
    public ResponseEntity<Void> activarCliente(@PathVariable("id") Long id){
        clienteService.activarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
