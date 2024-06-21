CREATE DATABASE SprintOne;

USE SprintOne;
create table if not exists roles
(
    id_role     int auto_increment
        primary key,
    descripcion varchar(255) null,
    nombre_role varchar(255) null
);

create table if not exists usuarios
(
    id_usuario            int auto_increment
        primary key,
    activo                bit                  not null,
    correo                varchar(255)         not null,
    password              varchar(255)         not null,
    username              varchar(255)         not null,
    token                 varchar(512)         null,
    token_expiration_date datetime             null,
    failed_attempt        int        default 0 null,
    account_non_locked    tinyint(1) default 1 null,
    lock_time             date                 null
);

create table if not exists productos
(
    id                  bigint auto_increment
        primary key,
    cantidad            int            null,
    descripcion         varchar(255)   null,
    fecha_actualizacion datetime(6)    null,
    fecha_creacion      datetime(6)    null,
    nombre              varchar(255)   null,
    precio              decimal(19, 2) null,
    usuario_id          int            not null,
    constraint FK2sbq0p983vqt057x5abjyopm0
        foreign key (usuario_id) references usuarios (id_usuario)
);

create table if not exists usuarios_roles
(
    id_usuario int not null,
    id_role    int not null,
    constraint FK33v8y5al18q1g8v661ewo6wla
        foreign key (id_role) references roles (id_role),
    constraint FKt5th9sao5wjukq9ij7154ktuw
        foreign key (id_usuario) references usuarios (id_usuario)
);


INSERT INTO usuarios (activo, correo, password, username, token, token_expiration_date, failed_attempt, account_non_locked, lock_time) VALUES (true, 'admin@example.com', '$2a$10$kIXyG9/mdtpGxOrx.aBDiOP7YaCC4u6Bo9E8TvD.eri9eRt/jIu22', 'admin_user', null, null, 0, 1, null);
INSERT INTO usuarios (activo, correo, password, username, token, token_expiration_date, failed_attempt, account_non_locked, lock_time) VALUES (true, 'user@example.com', '$2a$10$a0s6SE5olCACXRYsp.CamukHn/qdcnl6K2OdlUsBTwJrSOqow9uBe', 'regular_user', null, null, 0, 1, null);
INSERT INTO usuarios (activo, correo, password, username, token, token_expiration_date, failed_attempt, account_non_locked, lock_time) VALUES (true, 'guest@example.com', '$2a$10$DCKwEJAadl6AskWVuy6yzuDJihPI.Q0UO/m8lOcxjwhkoBvpzk.CW', 'guest_user', null, null, 0, 1, null);
INSERT INTO usuarios (activo, correo, password, username, token, token_expiration_date, failed_attempt, account_non_locked, lock_time) VALUES (true, 'mod@example.com', '$2a$10$i8W/krUtRAKl0UwTGMx4ceDcOnBaJXkT6O6U.Y.oyQVN.Heacxe8y', 'mod_user', null, null, 0, 1, null);
INSERT INTO usuarios (activo, correo, password, username, token, token_expiration_date, failed_attempt, account_non_locked, lock_time) VALUES (true, 'manager@example.com', '$2a$10$AhnpU9xWSBOZj4R4daF2nui5./zdyEsMnSGSDB0g/j2aQwauJqj0C', 'manager_user', null, null, 0, 1, null);
INSERT INTO usuarios (activo, correo, password, username, token, token_expiration_date, failed_attempt, account_non_locked, lock_time) VALUES (true, 'user@example.com', '$2a$10$EUswpZetR0ZI1S2vDzS7lOcwbG4PJJH1.GPssvfPU9hcadqAORHJS', 'regular_user', null, null, 0, 1, null);


INSERT INTO roles (descripcion, nombre_role) VALUES ('Administrador del sistema', 'Admin');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Usuario regular', 'User');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Invitado con permisos limitados', 'Guest');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Moderador del contenido', 'Moderator');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Gestor de recursos', 'Manager');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Administrador del sistema', 'Admin');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Usuario regular', 'User');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Invitado con permisos limitados', 'Guest');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Moderador del contenido', 'Moderator');
INSERT INTO roles (descripcion, nombre_role) VALUES ('Gestor de recursos', 'Manager');

INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (1, 3);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (2, 3);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (3, 3);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (4, 3);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (5, 3);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (1, 1);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (2, 2);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (3, 3);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (4, 4);
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (5, 5);

INSERT INTO productos (cantidad, descripcion, fecha_actualizacion, fecha_creacion, nombre, precio, usuario_id) VALUES (100, 'Descripción del Producto A', null, null, 'Producto A', 10.99, 1);
INSERT INTO productos (cantidad, descripcion, fecha_actualizacion, fecha_creacion, nombre, precio, usuario_id) VALUES (200, 'Descripción del Producto B', null, null, 'Producto B', 15.50, 2);
INSERT INTO productos (cantidad, descripcion, fecha_actualizacion, fecha_creacion, nombre, precio, usuario_id) VALUES (3, 'probando Agregar producto', '2024-06-20 17:41:59.074676', null, 'joseProducto', 120.00, 5);
INSERT INTO productos (cantidad, descripcion, fecha_actualizacion, fecha_creacion, nombre, precio, usuario_id) VALUES (50, 'Descripción del Producto D', null, null, 'Producto D', 20.00, 4);
INSERT INTO productos (cantidad, descripcion, fecha_actualizacion, fecha_creacion, nombre, precio, usuario_id) VALUES (300, 'Descripción del Producto E', null, null, 'Producto E', 5.75, 5);




DROP DATABASE SprintOne