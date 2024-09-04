package com.kibana.project_management.domain.tarea.subentities.linea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {

    Optional<Linea> findByLinea(String linea);
}
