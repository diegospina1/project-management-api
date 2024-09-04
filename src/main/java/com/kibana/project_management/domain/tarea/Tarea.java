package com.kibana.project_management.domain.tarea;

import com.kibana.project_management.domain.cliente.Cliente;
import com.kibana.project_management.domain.tarea.dto.DatosCreacionTarea;
import com.kibana.project_management.domain.tarea.subentities.clasificacion.Clasificacion;
import com.kibana.project_management.domain.tarea.subentities.linea.Linea;
import com.kibana.project_management.domain.tarea.subentities.prioridad.Prioridad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

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
