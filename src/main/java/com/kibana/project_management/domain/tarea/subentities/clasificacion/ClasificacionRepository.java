package com.kibana.project_management.domain.tarea.subentities.clasificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
    Clasificacion findByClasificacion(String clasificacion);
}
