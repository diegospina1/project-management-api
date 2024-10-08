package com.kibana.project_management.domain.tarea;

import com.kibana.project_management.domain.cliente.Cliente;
import com.kibana.project_management.domain.tarea.dto.DatosActualizarTarea;
import com.kibana.project_management.domain.tarea.dto.DatosCreacionTarea;
import com.kibana.project_management.domain.tarea.clasificacion.Clasificacion;
import com.kibana.project_management.domain.tarea.linea.Linea;
import com.kibana.project_management.domain.tarea.prioridad.Prioridad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity(name = "Tarea")
@Table(name = "tareas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Clasificacion.class)
    private Clasificacion clasificacion;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    private Linea linea;
    @ManyToOne(fetch = FetchType.EAGER)
    private Prioridad prioridad;
    private Integer cantidad;
    private Instant fecha_creacion;
    private Long archivo_id;
    private Boolean activo;

    public Tarea(Cliente cliente, Linea linea, Prioridad prioridad,
                 Clasificacion clasificacion, DatosCreacionTarea datosCreacionTarea) {
        this.clasificacion = clasificacion;
        this.cliente = cliente;
        this.linea = linea;
        this.prioridad = prioridad;
        this.cantidad = datosCreacionTarea.cantidad();
        this.fecha_creacion = Instant.now().minus(5, ChronoUnit.HOURS);
        this.archivo_id = 1L;
        this.activo = true;
    }

    public void actualizarTarea(DatosActualizarTarea datosActualizarTarea, Clasificacion clasificacion, Prioridad prioridad,
                                Linea linea){
        if(datosActualizarTarea.cantidad() != null){
            this.cantidad = datosActualizarTarea.cantidad();
        }
        if ((datosActualizarTarea.clasificacion() != null)){
            this.clasificacion = clasificacion;
        }
        if(datosActualizarTarea.linea() != null){
            this.linea = linea;
        }
        if(datosActualizarTarea.prioridad() != null){
            this.prioridad = prioridad;
        }
    }

    public void desactivarTarea() {
        this.activo = false;
    }

    public void activarTarea() {
        this.activo = true;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", clasificacion=" + clasificacion +
                ", cliente=" + cliente +
                ", linea=" + linea +
                ", prioridad=" + prioridad +
                ", cantidad=" + cantidad +
                ", fecha_creacion=" + fecha_creacion +
                ", activo=" + activo +
                '}';
    }
}
