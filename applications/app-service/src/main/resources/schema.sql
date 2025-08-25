 CREATE TABLE IF NOT EXISTS tbls_rol(
 id_rol SERIAL PRIMARY KEY,
 nombre VARCHAR(100) NOT NULL UNIQUE,
 descripcion VARCHAR(255)
 );


CREATE TABLE IF NOT EXISTS tbls_usuario(
id_usuario SERIAL PRIMARY KEY,
nombres VARCHAR(255) NOT NULL,
apellidos VARCHAR(255) NOT NULL,
fecha_nacimiento DATE,
direccion VARCHAR(255),
telefono VARCHAR(50),
correo_electronico VARCHAR(255) NOT NULL UNIQUE,
salario_base NUMERIC(12,2),
password VARCHAR(255) NOT NULL,
fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
id_rol INT,

CONSTRAINT fk_rol FOREIGN KEY(id_rol) REFERENCES tbls_rol(id_rol)
);
