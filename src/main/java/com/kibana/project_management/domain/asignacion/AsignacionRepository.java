package com.kibana.project_management.domain.asignacion;

import com.kibana.project_management.domain.empleado.Empleado;
import com.kibana.project_management.domain.tarea.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    Page<Asignacion> findAllByEmpleado(Empleado empleado, Pageable pages);

    Page<Asignacion> findAllByTarea(Tarea tarea, Pageable pages);

    @Query("update Asignacion a set a.estado = :#{#datos.estado} where a.tarea = :#{#datos.tarea}")
    @Modifying
    void actualizarEstado(@Param("datos") Asignacion asignacion);
}
