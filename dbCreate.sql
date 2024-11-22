-- 1. Drop database si ya existe (opcional)
DROP DATABASE IF EXISTS ecommercedb;


-- 2. Crear la base de datos si no existe
CREATE DATABASE ecommercedb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


-- Usar la base de datos creada
\c ecommercedb;

-- Creación de la base de datos y tablas

-- Tabla: categoria
CREATE TABLE categoria (
                           id_categoria SERIAL PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL
);

-- Tabla: producto
CREATE TABLE producto (
                          id_producto SERIAL PRIMARY KEY,
                          nombre VARCHAR(255) NOT NULL,
                          descripcion TEXT,
                          precio DECIMAL(10, 2) NOT NULL,
                          stock INT NOT NULL,
                          estado VARCHAR(50) NOT NULL,
                          id_categoria INTEGER NOT NULL,
                          CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria)
);

-- Tabla: cliente
CREATE TABLE cliente (
                         id_cliente SERIAL PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         direccion VARCHAR(255),
                         email VARCHAR(100),
                         telefono VARCHAR(20)
);

-- Tabla: orden
CREATE TABLE orden (
                       id_orden SERIAL PRIMARY KEY,
                       fecha_orden TIMESTAMP NOT NULL,
                       estado VARCHAR(50) NOT NULL,
                       id_cliente INTEGER NOT NULL,
                       total DECIMAL(10, 2) NOT NULL,
                       CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

-- Tabla: detalle_orden
CREATE TABLE detalle_orden (
                               id_detalle SERIAL PRIMARY KEY,
                               id_orden INTEGER NOT NULL,
                               id_producto INTEGER NOT NULL,
                               cantidad INT NOT NULL,
                               precio_unitario DECIMAL(10, 2) NOT NULL,
                               CONSTRAINT fk_orden FOREIGN KEY (id_orden) REFERENCES orden (id_orden),
                               CONSTRAINT fk_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);
