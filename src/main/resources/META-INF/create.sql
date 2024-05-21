DROP TABLE IF EXISTS empleados;
DROP TABLE IF EXISTS departamentos;

CREATE TABLE departamentos (id INT AUTO_INCREMENT PRIMARY KEY, codigo VARCHAR(255) NOT NULL UNIQUE, nombre VARCHAR(255) NOT NULL, fecha_hora_creacion TIMESTAMP, fecha_hora_actualizacion TIMESTAMP);

CREATE TABLE empleados (id INT AUTO_INCREMENT PRIMARY KEY, documento_tipo VARCHAR(255) NOT NULL, documento_numero BIGINT NOT NULL UNIQUE, nombres VARCHAR(255) NOT NULL, apellidos VARCHAR(255) NOT NULL, departamentos_id INT, ciudad VARCHAR(255), direccion VARCHAR(255), correo_electronico VARCHAR(255) NOT NULL, telefono VARCHAR(255) NOT NULL, fecha_hora_creacion TIMESTAMP, fecha_hora_actualizacion TIMESTAMP, CONSTRAINT fk_departamentos FOREIGN KEY (departamentos_id) REFERENCES departamentos(id) ON DELETE SET NULL);