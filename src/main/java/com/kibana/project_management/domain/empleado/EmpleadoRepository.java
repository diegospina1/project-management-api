package com.kibana.project_management.domain.empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Query("update Empleado e set e.nombre = :#{#nuevoEmpleado.nombre()}," +
            " e.user = :#{#nuevoEmpleado.user()}," +
            " e.password = :#{#nuevoEmpleado.password()} where e.id = :#{#nuevoEmpleado.id()}")
    @Modifying
    void actualizarEmpleado(@Param("nuevoEmpleado") DatosActualizarEmpleado datos);

    //Delete logico seteando activo = false
    @Query("update Empleado e set e.activo = false where e.id = :#{#id}")
    @Modifying
    void eliminarEmpleado(@Param("id") Long id);

    //Filtrar por usuario activo
    Page<Empleado> findAllByActivoTrue(Pageable pages);

    //Activar empleado
    @Query("update Empleado e set e.activo = true where e.id = :#{#id}")
    @Modifying
    void activarEmpleado(@Param("id") Long id);
}
