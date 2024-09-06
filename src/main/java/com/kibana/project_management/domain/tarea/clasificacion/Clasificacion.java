package com.kibana.project_management.domain.tarea.clasificacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Clasificacion")
@Table(name = "clasificaciones")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Clasificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clasificacion;

    @Override
    public String toString() {
        return "Clasificacion{" +
                "id=" + id +
                ", clasificacion='" + clasificacion + '\'' +
                '}';
    }
}
