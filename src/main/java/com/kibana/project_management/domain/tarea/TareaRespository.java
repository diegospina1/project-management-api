package com.kibana.project_management.domain.tarea;

import com.kibana.project_management.domain.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRespository extends JpaRepository<Tarea, Long> {

    Page<Tarea> findAllByActivoTrue(Pageable pages);

    @Query("select t from Tarea t where t.cliente = :#{#cliente}")
    Page<Tarea> buscarTareaPorCliente(@Param("cliente") Cliente cliente, Pageable pages);
}
