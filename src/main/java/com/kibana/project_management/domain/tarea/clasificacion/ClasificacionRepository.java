package com.kibana.project_management.domain.tarea.clasificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
    Optional<Clasificacion> findByClasificacion(String clasificacion);
}
