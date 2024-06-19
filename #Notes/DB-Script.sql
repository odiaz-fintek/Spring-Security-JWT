CREATE DATABASE SprintOne;
CREATE TABLE roles (
                       id_role INT AUTO_INCREMENT PRIMARY KEY,
                       nombre_role VARCHAR(40) NOT NULL,
                       descripcion VARCHAR(80)
);

CREATE TABLE usuarios (
                          id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL,
                          correo VARCHAR(60) NOT NULL,
                          password VARCHAR(60) NOT NULL,
                          activo TINYINT(1) NOT NULL
);

CREATE TABLE usuarios_roles (
                                id_usuario INT,
                                id_role INT,
                                PRIMARY KEY (id_usuario, id_role),
                                FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
                                FOREIGN KEY (id_role) REFERENCES roles(id_role)
);

CREATE TABLE productos (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           descripcion TEXT,
                           precio DECIMAL(10, 2) NOT NULL,
                           cantidad INT NOT NULL,
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           id_usuario INT, -- Agregamos la columna de clave foránea
                           FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Insertar datos en la tabla roles
INSERT INTO roles (nombre_role, descripcion) VALUES ('Admin', 'Administrador del sistema');
INSERT INTO roles (nombre_role, descripcion) VALUES ('User', 'Usuario regular');
INSERT INTO roles (nombre_role, descripcion) VALUES ('Guest', 'Invitado con permisos limitados');
INSERT INTO roles (nombre_role, descripcion) VALUES ('Moderator', 'Moderador del contenido');
INSERT INTO roles (nombre_role, descripcion) VALUES ('Manager', 'Gestor de recursos');

-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (username, correo, password, activo) VALUES ('admin_user', 'admin@example.com', 'admin_pass', 1);
INSERT INTO usuarios (username, correo, password, activo) VALUES ('regular_user', 'user@example.com', 'user_pass', 1);
INSERT INTO usuarios (username, correo, password, activo) VALUES ('guest_user', 'guest@example.com', 'guest_pass', 1);
INSERT INTO usuarios (username, correo, password, activo) VALUES ('mod_user', 'mod@example.com', 'mod_pass', 1);
INSERT INTO usuarios (username, correo, password, activo) VALUES ('manager_user', 'manager@example.com', 'manager_pass', 1);

-- Insertar datos en la tabla usuarios_roles
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (1, 1);  -- admin_user es Admin
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (2, 2);  -- regular_user es User
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (3, 3);  -- guest_user es Guest
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (4, 4);  -- mod_user es Moderator
INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (5, 5);  -- manager_user es Manager

INSERT INTO productos (nombre, descripcion, precio, cantidad, id_usuario)
VALUES ('Producto A', 'Descripción del Producto A', 10.99, 100, 1);

INSERT INTO productos (nombre, descripcion, precio, cantidad, id_usuario)
VALUES ('Producto B', 'Descripción del Producto B', 15.50, 200, 2);

INSERT INTO productos (nombre, descripcion, precio, cantidad, id_usuario)
VALUES ('Producto C', 'Descripción del Producto C', 7.25, 150, 3);

INSERT INTO productos (nombre, descripcion, precio, cantidad, id_usuario)
VALUES ('Producto D', 'Descripción del Producto D', 20.00, 50, 4);

INSERT INTO productos (nombre, descripcion, precio, cantidad, id_usuario)
VALUES ('Producto E', 'Descripción del Producto E', 5.75, 300, 5);


DROP DATABASE SprintOne