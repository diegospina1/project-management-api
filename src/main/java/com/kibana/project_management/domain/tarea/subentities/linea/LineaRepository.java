package com.kibana.project_management.domain.tarea.subentities.linea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {

    Linea findByLinea(String linea);
}
