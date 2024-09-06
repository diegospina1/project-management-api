package com.kibana.project_management.domain.tarea.prioridad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Prioridad")
@Table(name = "prioridades")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Prioridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prioridad;

    @Override
    public String toString() {
        return "Prioridad{" +
                "id=" + id +
                ", prioridad='" + prioridad + '\'' +
                '}';
    }
}
