package com.kibana.project_management.domain.asignacion;

import com.kibana.project_management.domain.empleado.Empleado;
import com.kibana.project_management.domain.tarea.Tarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "Asignacion")
@Table(name = "empleado_tarea")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Tarea tarea;
    @ManyToOne(fetch = FetchType.EAGER)
    private Empleado empleado;
    private LocalTime tiempo;
    @Enumerated(EnumType.STRING)
    private Estado estado;



}
