package com.kibana.project_management.service;

import com.kibana.project_management.domain.cliente.Cliente;
import com.kibana.project_management.domain.cliente.ClienteRepository;
import com.kibana.project_management.domain.cliente.dto.DatosActualizarCliente;
import com.kibana.project_management.domain.cliente.dto.DatosCreacionCliente;
import com.kibana.project_management.service.errors.IExceptionMessages;
import com.kibana.project_management.service.errors.exceptions.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements IExceptionMessages {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //Listar todos
    public Page<Cliente> listarTodos(Pageable pages){
        return this.clienteRepository.findAllByActivoTrue(pages);
    }
    //Listar por id
    public Cliente listarPorId(Long id){
        return this.clienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    //Crear cliente
    public Cliente crearCliente(DatosCreacionCliente datos){
        return this.clienteRepository.save(new Cliente(datos));
    }
    //Actualizar cliente
    public Cliente actualizarCliente(DatosActualizarCliente datos){
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(datos.id());
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.actualizarCliente(datos);
            this.clienteRepository.actualizarCliente(datos);

            return cliente;
        } else {
            throw new ValidacionDeIntegridad(noEncontradoId(datos.id()));
        }
    }
    //Desactivar cliente
    public void desactivarCliente(Long id){
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.desactivarCliente();
            this.clienteRepository.desactivarCliente(id);
        } else {
            throw new ValidacionDeIntegridad(noEncontradoId(id));
        }
    }
    //Activar cliente
    public void activarCliente(Long id){
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.activarCliente();
            this.clienteRepository.activarCliente(id);
        } else {
            throw new ValidacionDeIntegridad(noEncontradoId(id));
        }
    }
    //Buscar cliente por nombres
    public Page<Cliente> buscarClientePorNombre(String nombre, Pageable pages){
        Page<Cliente> clientes = clienteRepository.buscarPorNombre(nombre, pages);
        if(clientes.hasContent()){
            return clientes;
        } else {
            throw new ValidacionDeIntegridad(noEncontradoString(nombre));
        }
    }

    //Mensaje cliente no encontrado para ID o Nombre
    @Override
    public String noEncontradoId(Long id) {
        return String.format("Cliente no encontrado para el id: %d", id);
    }

    @Override
    public String noEncontradoString(String name) {
        return String.format("Cliente no encontrado: %s", name);
    }







}
