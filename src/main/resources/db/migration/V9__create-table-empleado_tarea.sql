create table empleado_tarea(
    id bigint not null auto_increment,
    tarea_id bigint not null,
    empleado_id bigint not null,
    tiempo time not null,

    primary key (id),

    constraint fk_empleado_tarea_empleado_id foreign key (empleado_id) references empleados(id),
    constraint fk_empleado_tarea_tarea_id foreign key (tarea_id) references tareas(id)
);