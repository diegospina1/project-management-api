package com.kibana.project_management.domain.tarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRespository extends JpaRepository<Tarea, Long> {

}
