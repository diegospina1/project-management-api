package com.kibana.project_management.domain.tarea.subentities.linea;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Linea")
@Table(name = "lineas")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Linea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linea;

    @Override
    public String toString() {
        return "Linea{" +
                "id=" + id +
                ", linea='" + linea + '\'' +
                '}';
    }
}
