package com.kibana.project_management.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Actualizar cliente
    @Query("update Cliente c set c.nombres = :#{#datos.nombres} where c.id = :#{#datos.id}")
    @Modifying
    void actualizarCliente(@Param("datos") DatosActualizarCliente datos);

    //Desactivar cliente
    @Query("update Cliente c set c.activo = false where c.id = :#{#id}")
    @Modifying
    void desactivarCliente(@Param("id") Long id);

    //Activar
    @Query("update Cliente c set c.activo = true where c.id = :#{#id}")
    @Modifying
    void activarCliente(Long id);

    Page<Cliente> findAllByActivoTrue(Pageable pages);
}
