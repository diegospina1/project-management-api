package com.kibana.project_management.domain.empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    //Cambiar password
    @Query("update Empleado e set e.password = :#{#password} where e.id= :#{#id}")
    @Modifying
    void cambiarPassword(@Param("password") String password, @Param("id") Long id);

    //Empleado por username
    @Query("select e from Empleado e where e.user = :#{#user}")
    Optional<Empleado> findByUser(String user);
}
