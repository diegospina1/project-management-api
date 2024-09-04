package com.kibana.project_management.domain.cliente;

import com.kibana.project_management.domain.cliente.dto.DatosActualizarCliente;
import com.kibana.project_management.domain.cliente.dto.DatosCreacionCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private Boolean activo;

    public Cliente(DatosCreacionCliente datos) {
        this.nombres = datos.nombres();
        this.activo = true;
    }

    public void actualizarCliente(DatosActualizarCliente datos){
        if(datos.nombres() != null){
            this.nombres = datos.nombres();
        }
    }
    //Delete lógico
    public void desactivarCliente(){
        this.activo = false;
    }

    //Activar
    public void activarCliente() {
        this.activo = true;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", activo=" + activo +
                '}';
    }
}
