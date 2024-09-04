package com.kibana.project_management.domain.empleado;

import com.kibana.project_management.domain.empleado.dto.DatosActualizarEmpleado;
import com.kibana.project_management.domain.empleado.dto.DatosCreacionEmpleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Empleado")
@Table(name = "empleados")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String user;
    private String password;
    private Boolean activo;

    //Crear
    public Empleado(DatosCreacionEmpleado nuevoEmpleado){
        this.nombre = nuevoEmpleado.nombre();
        this.user = nuevoEmpleado.user();
        this.password = nuevoEmpleado.password();
        this.activo = true;
    }

    //Actualizar
    public void actualizarEmpleado(DatosActualizarEmpleado datos){
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.user() != null){
            this.user = datos.user();
        }
        if(datos.password() != null){
            //Encripta desde acá cuando implementes SS
            this.password = datos.password();
        }
    }

    //Delete lógico
    public void desactivarEmpleado(){
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }
    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
