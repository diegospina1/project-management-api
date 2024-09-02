create table rol_empleado(
    id bigint not null auto_increment,
    empleado_id bigint not null,
    rol_id bigint not null,

    primary key (id),

    constraint fk_rol_empleado_empleado_id foreign key (empleado_id) references empleados(id),
    constraint fk_rol_empleado_rol_id foreign key (rol_id) references roles(id)
);