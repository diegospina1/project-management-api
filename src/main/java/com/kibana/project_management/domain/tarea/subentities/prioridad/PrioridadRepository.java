package com.kibana.project_management.domain.tarea.subentities.prioridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, Long> {
    Optional<Prioridad> findByPrioridad(String prioridad);
}
