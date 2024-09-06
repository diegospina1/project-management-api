package com.kibana.project_management.domain.asignacion;

import com.kibana.project_management.domain.asignacion.estado.Estado;
import com.kibana.project_management.domain.asignacion.estado.EstadoEnum;
import com.kibana.project_management.domain.empleado.Empleado;
import com.kibana.project_management.domain.tarea.Tarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Timer;

@Entity(name = "Asignacion")
@Table(name = "asignaciones")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Tarea.class)
    private Tarea tarea;
    @ManyToOne(targetEntity = Empleado.class)
    private Empleado empleado;
    @ManyToOne(targetEntity = Estado.class)
    private Estado estado;
    private Instant fecha_creacion;
    private Instant fecha_limite;

    public void actualizarEstado(Estado estado){
        this.estado = estado;
    }


}
