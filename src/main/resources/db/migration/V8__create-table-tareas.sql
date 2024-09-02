create table tareas(
    id bigint not null auto_increment,
    clasificacion_id bigint not null,
    cliente_id bigint not null,
    linea_id bigint not null,
    prioridad_id bigint not null,
    archivo_id bigint not null,
    cantidad int(10) not null,

    primary key (id),

    constraint fk_tareas_clasificacion_id foreign key (clasificacion_id) references clasificaciones(id),
    constraint fk_tareas_cliente_id foreign key (cliente_id) references clientes(id),
    constraint fk_tareas_linea_id foreign key (linea_id) references lineas(id),
    constraint fk_tareas_prioridad_id foreign key (prioridad_id) references prioridades(id),
    constraint fk_tareas_archivo_id foreign key (archivo_id) references archivos(id)
);