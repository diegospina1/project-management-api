package com.kibana.project_management.domain.tarea;

import com.kibana.project_management.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    private Linea linea;
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
    private Integer cantidad;


}
