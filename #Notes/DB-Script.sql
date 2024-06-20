CREATE DATABASE SprintOne;
CREATE TABLE roles (
                       id_role INT AUTO_INCREMENT PRIMARY KEY,
                       nombre_role VARCHAR(40) NOT NULL,
                       descripcion VARCHAR(80)
);

CREATE TABLE usuarios (
                          usuario_id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL,
                          correo VARCHAR(60) NOT NULL,
                          password VARCHAR(60) NOT NULL,
                          activo TINYINT(1) NOT NULL
);

CREATE TABLE usuarios_roles (
                                usuario_id INT,
                                id_role INT,
                                PRIMARY KEY (usuario_id, id_role),
                                FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id),
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
                           usuario_id INT, -- Agregamos la columna de clave foránea
                           FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);

-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (usuario_id, activo, correo, password, username) VALUES (1, true, 'admin@example.com', '$2a$10$kIXyG9/mdtpGxOrx.aBDiOP7YaCC4u6Bo9E8TvD.eri9eRt/jIu22', 'admin_user');
INSERT INTO usuarios (usuario_id, activo, correo, password, username) VALUES (2, true, 'user@example.com', '$2a$10$a0s6SE5olCACXRYsp.CamukHn/qdcnl6K2OdlUsBTwJrSOqow9uBe', 'regular_user');
INSERT INTO usuarios (usuario_id, activo, correo, password, username) VALUES (3, true, 'guest@example.com', '$2a$10$DCKwEJAadl6AskWVuy6yzuDJihPI.Q0UO/m8lOcxjwhkoBvpzk.CW', 'guest_user');
INSERT INTO usuarios (usuario_id, activo, correo, password, username) VALUES (4, true, 'mod@example.com', '$2a$10$i8W/krUtRAKl0UwTGMx4ceDcOnBaJXkT6O6U.Y.oyQVN.Heacxe8y', 'mod_user');
INSERT INTO usuarios (usuario_id, activo, correo, password, username) VALUES (5, true, 'manager@example.com', '$2a$10$AhnpU9xWSBOZj4R4daF2nui5./zdyEsMnSGSDB0g/j2aQwauJqj0C', 'manager_user');

-- Insertar datos en la tabla roles
INSERT INTO roles (nombre_role, descripcion) VALUES ('Admin', 'Administrador del sistema');
INSERT INTO roles (nombre_role, descripcion) VALUES ('User', 'Usuario regular');
INSERT INTO roles (nombre_role, descripcion) VALUES ('Guest', 'Invitado con permisos limitados');
INSERT INTO roles (nombre_role, descripcion) VALUES ('Moderator', 'Moderador del contenido');
INSERT INTO roles (nombre_role, descripcion) VALUES ('Manager', 'Gestor de recursos');

-- Insertar datos en la tabla usuarios_roles
INSERT INTO usuarios_roles (usuario_id, id_role) VALUES (1, 1);  -- admin_user es Admin
INSERT INTO usuarios_roles (usuario_id, id_role) VALUES (2, 2);  -- regular_user es User
INSERT INTO usuarios_roles (usuario_id, id_role) VALUES (3, 3);  -- guest_user es Guest
INSERT INTO usuarios_roles (usuario_id, id_role) VALUES (4, 4);  -- mod_user es Moderator
INSERT INTO usuarios_roles (usuario_id, id_role) VALUES (5, 5);  -- manager_user es Manager

INSERT INTO productos (nombre, descripcion, precio, cantidad, usuario_id)
VALUES ('Producto A', 'Descripción del Producto A', 10.99, 100, 1);

INSERT INTO productos (nombre, descripcion, precio, cantidad, usuario_id)
VALUES ('Producto B', 'Descripción del Producto B', 15.50, 200, 2);

INSERT INTO productos (nombre, descripcion, precio, cantidad, usuario_id)
VALUES ('Producto C', 'Descripción del Producto C', 7.25, 150, 3);

INSERT INTO productos (nombre, descripcion, precio, cantidad, usuario_id)
VALUES ('Producto D', 'Descripción del Producto D', 20.00, 50, 4);

INSERT INTO productos (nombre, descripcion, precio, cantidad, usuario_id)
VALUES ('Producto E', 'Descripción del Producto E', 5.75, 300, 5);


DROP DATABASE SprintOne