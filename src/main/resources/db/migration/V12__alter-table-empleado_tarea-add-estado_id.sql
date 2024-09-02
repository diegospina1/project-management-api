alter table empleado_tarea add estado_id bigint not null,
    add constraint fk_empleado_tarea_estado_id foreign key (estado_id) references estados(id);