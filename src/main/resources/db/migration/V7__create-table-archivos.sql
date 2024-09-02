create table archivos(
    id bigint not null auto_increment,
    nombre VARCHAR(255) NOT NULL,
    ruta VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    size int(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    empleado_id bigint not null,

    primary key (id),

    constraint fk_archivos_empleado_id foreign key (empleado_id) references empleados(id)
);