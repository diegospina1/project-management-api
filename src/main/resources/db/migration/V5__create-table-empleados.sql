create table empleados(
    id bigint not null auto_increment,
    nombre varchar(255) not null,
    user varchar(150) not null,
    password varchar(150) not null,

    primary key (id)
);